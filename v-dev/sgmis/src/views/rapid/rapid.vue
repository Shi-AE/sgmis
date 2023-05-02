<script>

import axiosx from "@/assets/js/axiosx.js"
import {verifyData} from "@/assets/js/loading.js"
import {Notification} from '@arco-design/web-vue'

export default {
    data() {
        return {
            form: {
                fid: null,
                mid: null,
                pigeon: [
                    {
                        country: null,
                        year: null,
                        province: null,
                        code: null,
                        sex: null
                    }
                ]
            },
            options: {
                fatherOptions: [],
                motherOptions: [],
                countryOptions: [],
                provinceOptions: []
            }
        }
    },
    mounted() {
        axiosx({
            method: "GET",
            url: `xxpz/country`,
            message: "正在获取系统信息"
        }).then(res => {
            if (res.data.code === 200) {
                this.options.countryOptions = res.data.data.map(item => {
                    const option = item.name.split("/");
                    return {
                        label: `${option[0]}${option[1]}`,
                        value: option[1]
                    }
                })
            }
        })
        axiosx({
            method: "GET",
            url: `xxpz/province`,
            message: "正在获取系统信息"
        }).then(res => {
            if (res.data.code === 200) {
                this.options.provinceOptions = res.data.data.map(item => {
                    const option = item.name.split("/");
                    return {
                        label: `${option[0]}${option[1]}`,
                        value: option[1]
                    }
                })
            }
        })
    },
    methods: {
        //抽离相同代码
        search(inputValue, sex) {
            if (inputValue) {
                axiosx({
                    method: "GET",
                    url: `rapid/${sex}/${inputValue}`,
                }).then(res => {
                    if (res.data.code === 200) {
                        const data = res.data.data.map(item => {
                            return {
                                label: item.ringNumber,
                                value: String(item.id)
                            }
                        })
                        if (sex === "father") {
                            this.options.fatherOptions = data
                        } else if (sex === "mother") {
                            this.options.motherOptions = data
                        }
                    }
                })
            }
        },
        //处理选择框搜索
        handleFatherSearch(inputValue) {
            this.search(inputValue, "father")
        },
        handleMotherSearch(inputValue) {
            this.search(inputValue, "mother")
        },
        //添加子代表单
        addForm() {
            this.form.pigeon.push({
                country: null,
                year: null,
                province: null,
                code: null,
                sex: null
            })
        },
        addSame() {
            //深拷贝复制
            const item = JSON.parse(JSON.stringify(this.form.pigeon[this.form.pigeon.length - 1]))
            this.form.pigeon.push(item)
        },
        deleteItem(index) {
            this.form.pigeon.splice(index, 1)
        },
        copyItem(index) {
            const item = JSON.parse(JSON.stringify(this.form.pigeon[index]))
            this.form.pigeon.splice(index, 0, item)
        },
        commit() {
            const pigeons = []
            if (!verifyData(() => {
                const form = this.form
                if (!form.mid && !form.fid) {
                    Notification.warning("至少输入一个父代信息")
                    return false
                }
                if (form.pigeon.length === 0) {
                    Notification.warning("至少输入一个子代信息")
                    return false
                }
                let f = true
                form.pigeon.forEach(item => {
                    if (!item.code || !item.year || !item.province || !item.country || !item.sex) {
                        f = false
                        return
                    }
                    pigeons.push({
                        ringNumber: [
                            item.country,
                            item.year,
                            item.province,
                            item.code
                        ].join("-"),
                        sex: item.sex
                    })
                })
                if (!f) {
                    Notification.warning("子代信息不完整")
                    return false
                }
            }, "验证数据")) {
                return
            }
            axiosx({
                method: "POST",
                url: "rapid",
                data: {
                    fid: this.form.fid,
                    mid: this.form.mid,
                    pigeons
                },
                message: "正在快速入库"
            }).then(res => {
                if (res.data.code === 200) {
                    Notification.success(res.data.message)
                } else {
                    Notification.error(`${res.data.message}，如需添加血亲关系，请移步血统编辑`)
                }
            })
        }
    }
}
</script>

<template>
    <a-divider :size="2" style="border-bottom-style: dotted" orientation="left">父母信息</a-divider>
    <a-form :model="form" layout="horizontal" :auto-label-width="true">
        <a-grid :cols="{md: 2, sm: 1, xs: 1}" :col-gap="10" :style="{margin: '0 30px'}">
            <a-grid-item>
                <a-form-item label="父亲环号">
                    <a-select v-model.lazy="form.fid" :options="options.fatherOptions" placeholder="父亲环号"
                              allow-search @search="handleFatherSearch"/>
                </a-form-item>
            </a-grid-item>
            <a-grid-item>
                <a-form-item label="母亲环号">
                    <a-select v-model.lazy="form.mid" :options="options.motherOptions" placeholder="母亲环号"
                              allow-search @search="handleMotherSearch"/>
                </a-form-item>
            </a-grid-item>
        </a-grid>
    </a-form>
    <a-divider :size="2" style="border-bottom-style: dotted" orientation="left">新建鸽子信息</a-divider>
    <div class="button-group">
        <a-space wrap>
            <a-button type="primary" status="success" @click="addForm()">增加</a-button>
        </a-space>
    </div>
    <a-list :bordered="true" size="large">
        <a-list-item class="list-group-item" v-for="(item, index) in form.pigeon" :key="index">
            <a-form :model="item" layout="horizontal" :auto-label-width="true">
                <a-grid :cols="{xxl: 4,md: 3, sm: 2, xs: 1}">
                    <a-grid-item>
                        <a-form-item label="国家" extra="由“-”组合成足环" validate-trigger="blur" required>
                            <a-select v-model.lazy="item.country" :options="options.countryOptions" placeholder="国家"
                                      allow-search/>
                        </a-form-item>
                    </a-grid-item>
                    <a-grid-item>
                        <a-form-item label="出生年份" validate-trigger="blur" required>
                            <a-year-picker v-model.lazy="item.year"/>
                        </a-form-item>
                    </a-grid-item>
                    <a-grid-item>
                        <a-form-item label="省份代号" validate-trigger="blur" required>
                            <a-select v-model.lazy="item.province" :options="options.provinceOptions"
                                      placeholder="省份代号"
                                      allow-search/>
                        </a-form-item>
                    </a-grid-item>
                    <a-grid-item>
                        <a-form-item label="编号" validate-trigger="blur" required>
                            <a-input v-model.lazy="item.code" placeholder="编号"/>
                        </a-form-item>
                    </a-grid-item>
                    <a-form-item label="性别" required>
                        <a-select v-model.lazy="item.sex" :options="['雄', '雌']" placeholder="性别"/>
                    </a-form-item>
                </a-grid>
            </a-form>
            <div class="button-group">
                <a-space wrap>
                    <a-button type="outline" status="danger" @click="deleteItem(index)">删除</a-button>
                    <a-button type="outline" status="normal" @click="copyItem(index)">复制</a-button>
                </a-space>
            </div>
        </a-list-item>
    </a-list>
    <div class="button-group">
        <a-space wrap>
            <a-button type="primary" status="normal" @click="addSame()">同上</a-button>
        </a-space>
    </div>
    <a-divider :size="2" style="border-bottom-style: dotted" orientation="left"></a-divider>
    <a-button type="primary" @click="commit()" size="large" long>保存</a-button>
</template>

<style scoped>
.button-group {
    margin: 10px 50px;
    display: flex;
    justify-content: end;
}
</style>