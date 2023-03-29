<script>
import axiosx from "@/assets/js/axiosx.js"
import { Notification } from '@arco-design/web-vue'

const levelMap = {
    provincial: {
        value: "provincial",
        isLeaf: false,
        next: "urban",
    },
    urban: {
        value: "urban",
        isLeaf: false,
        next: "areas",
    },
    areas: {
        value: "areas",
        isLeaf: true
    }
}

export default {
    data() {
        return {
            info: {
                name: "",
                years: undefined,
                location: [],
                address: "",
                bloodline: "",
                performance: ""
            },
            options: []
        }
    },
    methods: {
        submit() {
            const { location, ...form } = this.info
            axiosx({
                method: "POST",
                url: "gsxx",
                data: {
                    ...form,
                    location: location.length === 0 ? null : location.join(",")
                },
                message: "正在上传信息"
            }).then(res => {
                console.log(res);
                if (res.data.code === 200) {
                    Notification.success(res.data.message)
                } else {
                    Notification.error(res.data.message)
                }
            })
        },
        loadMore(option, done) {
            let level = levelMap[option.level].next
            axiosx({
                method: "GET",
                url: `${levelMap[level].value}/${option.id}`,
                message: "正在获取地址信息"
            }).then(res => {
                if (res.data.code === 200) {
                    done(res.data.data.map(item => {
                        return {
                            ...item,
                            level: levelMap[level].value,
                            isLeaf: levelMap[level].isLeaf
                        }
                    }))
                }
            })
        }
    },
    created() {
        //查询原有信息
        axiosx({
            method: "GET",
            url: "gsxx"
        }).then(res => {
            if (res.data.code === 200) {
                const { location, ...from } = res.data.data
                this.info = {
                    ...from,
                    location: location === null ? [] : location.split(",")
                }
            }
        })
        //查询一级地址信息
        const level = "provincial"
        axiosx({
            method: "GET",
            url: `${levelMap[level].value}`,
            message: "正在获取地址信息"
        }).then(res => {
            if (res.data.code === 200) {
                this.options = res.data.data.map(item => {
                    return {
                        ...item,
                        level: levelMap[level].value,
                        isLeaf: levelMap[level].isLeaf
                    }
                })
            }
        })
    }
}
</script>
<template>
    <div class="full-screen d-flex align-items-center flex-column">
        <div class="title fs-1">鸽舍信息</div>
        <a-form ref="formRef" :model="info" @submit="submit" size="large" layout="horizontal" :auto-label-width="true">
            <a-form-item field="name" tooltip="你的鸽舍名称" label="鸽舍名称">
                <a-input v-model.lazy="info.name" placeholder="鸽舍名称" />
            </a-form-item>
            <a-form-item field="location" label="地址">
                <a-cascader v-model.lazy="info.location" :options="options" placeholder="地址" allow-clear path-mode
                    :load-more="loadMore" />
            </a-form-item>
            <a-form-item field="address" tooltip="具体地址信息，方便他人联系你" label="具体地址">
                <a-input v-model.lazy="info.address" placeholder="具体地址" />
            </a-form-item>
            <a-form-item field="years" label="养鸽年数">
                <a-input-number v-model.lazy.number="info.years" placeholder="养鸽年数" />
            </a-form-item>
            <a-form-item field="bloodLine" label="主养血系">
                <a-textarea v-model.lazy="info.bloodline" placeholder="主养血系" />
            </a-form-item>
            <a-form-item field="performance" label="优秀赛绩">
                <a-textarea v-model.lazy="info.performance" placeholder="优秀赛绩" />
            </a-form-item>
            <a-form-item>
                <a-space>
                    <a-button type="primary" html-type="submit">提交</a-button>
                    <a-button @click="$refs.formRef.resetFields()">重置</a-button>
                </a-space>
            </a-form-item>
        </a-form>
    </div>
</template>

<style scoped lang="scss">
.full-screen {
    width: 100%;
    height: 100%;

    .title {
        text-align: start;
        padding: 30px 2px;
        white-space: nowrap;
        text-overflow: ellipsis;
        font-weight: 600;
        color: var(--bs-blue);
    }

    form,
    .title {
        width: 100%;
        max-width: 720px;
    }
}
</style>