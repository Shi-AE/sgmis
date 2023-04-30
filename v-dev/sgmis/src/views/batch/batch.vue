<script>
import axiosx from "@/assets/js/axiosx.js"
import {Notification} from '@arco-design/web-vue'
import {verifyData} from "../../assets/js/loading.js";

export default {
    data() {
        return {
            data: [],
            value: [],
            selected: [],
            form: {
                type: null,
                value: null
            },
            fieldOptions: [
                {
                    label: "羽色",
                    value: "yspz"
                },
                {
                    label: "眼砂",
                    value: "yanpz"
                },
                {
                    label: "类型",
                    value: "lxpz"
                },
                {
                    label: "状态",
                    value: "state"
                },
                {
                    label: "级别",
                    value: "jbpz"
                },
                {
                    label: "医疗护理",
                    value: "ylhl"
                },
            ],
            options: {
                yspzOptions: [],
                yanpzOptions: [],
                lxpzOptions: [],
                stateOptions: [],
                jbpzOptions: [],
                ylhlOptions: []
            },
            type: {
                yspz: {
                    toName: "羽色",
                    toField: "ys"
                },
                yanpz: {
                    toName: "眼砂",
                    toField: "yan"
                },
                lxpz: {
                    toName: "类型",
                    toField: "lx"
                },
                state: {
                    toName: "状态",
                    toField: "state"
                },
                jbpz: {
                    toName: "级别",
                    toField: "jb"
                },
                ylhl: {
                    toName: "医疗护理",
                    toField: "ylhl"
                },
            },
        }
    },
    mounted() {
        //获取鸽子信息
        axiosx({
            method: "GET",
            url: "pigeon",
            message: "正在获取鸽子信息"
        }).then(res => {
            if (res.data.code === 200) {
                this.data = res.data.data.map((item) => {
                    return {
                        label: item.ringNumber,
                        value: String(item.id)
                    }
                })
            }
        })
    },
    methods: {
        handleOptions(type) {
            //清空选择的value
            this.form.value = null
            //获取配置
            if (this.options[`${type}Options`]?.length === 0) {
                axiosx({
                    method: "GET",
                    url: `xxpz/${type}`,
                    message: "正在获取系统信息"
                }).then(res => {
                    if (res.data.code === 200) {
                        this.options[`${type}Options`] = res.data.data.map(item => {
                            return item.name
                        })
                    }
                })
            }
        },
        //表单提交
        commit() {
            const ids = this.value
            const field = this.type[this.form.type]?.toField
            const value = this.form.value
            if (!verifyData(() => {
                if (ids.length === 0) {
                    Notification.error("请选择至少一只鸽子")
                    return false
                }
                if (!field) {
                    Notification.error("属性有误")
                    return false
                }
                if (!value) {
                    Notification.error("属性设置值错误")
                    return false
                }
            })) {
                return
            }
            axiosx({
                method: "PUT",
                url: `pigeon/${field}/${value}`,
                data: ids
            }).then(res => {
                if (res.data.code === 200) {
                    Notification.success(res.data.message)

                    //数据还原
                    this.value = []
                    this.selected = []
                    this.form = {
                        type: null,
                        value: null
                    }
                } else {
                    Notification.error(res.data.message)
                }
            })
        }
    }
}
</script>

<template>
    <a-divider :size="2" style="border-bottom-style: dotted" orientation="left">选择数据</a-divider>
    <div class="transfer">
        <a-transfer v-model:data="data" v-model:model-value="value" v-model:selected="selected" one-way show-search
                    :title="['搜索鸽子信息','目标鸽子']">
        </a-transfer>
    </div>
    <a-divider :size="2" style="border-bottom-style: dotted" orientation="left">设置属性</a-divider>
    <a-form :model="form" :auto-label-width="true" :style="{ margin: 'auto', maxWidth: '600px' }">
        <a-form-item label="要进行设置的属性">
            <a-select v-model.lazy="form.type" :options="fieldOptions" placeholder="属性" @change="handleOptions"/>
        </a-form-item>
        <a-form-item v-if="!!type[form.type]?.toName" :label="`要设置的${type[form.type]?.toName}值`">
            <a-select v-model.lazy="form.value" :options="options[`${form.type}Options`]"
                      :placeholder="`选择${type[form.type]?.toName}`" allow-search/>
        </a-form-item>
        <a-button type="primary" size="large" @click="commit()">设置属性</a-button>
    </a-form>
</template>

<style scoped lang="scss">
.transfer {
  :deep(.arco-transfer) {
    justify-content: center;
  }

  :deep(.arco-transfer-view) {
    width: calc(50px + 0.3 * 100vw);
    height: 500px;
  }
}
</style>