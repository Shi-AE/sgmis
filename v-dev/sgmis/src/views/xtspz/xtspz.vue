<script>
import { IconEdit, IconPlus } from '@arco-design/web-vue/es/icon'
import axiosx from "@/assets/js/axiosx.js"
import { Notification } from '@arco-design/web-vue'
import store from "@/store"

export default {
    components: { IconPlus, IconEdit },
    data() {
        return {
            info: {
                name: "",
                shortName: "",
                phone: "",
                mail: "",
                logoUrl: "",
                address: ""
            },
            file: {}
        }
    },
    methods: {
        submit() {
            axiosx({
                method: "POST",
                url: "xtspz",
                data: this.info,
                message: "正在保存配置"
            }).then(res => {
                if (res.data.code === 200) {
                    Notification.success(res.data.message)
                } else {
                    Notification.error(res.data.message)
                }
            })
        },
        onChange(_, currentFile) {
            this.file = currentFile
        },
        customRequest(option) {
            const { onProgress, onError, onSuccess, fileItem, name } = option
            const formData = new FormData();
            formData.append(name || 'file', fileItem.file);
            axiosx({
                method: "POST",
                url: "xtspz/logo",
                data: formData,
                message: "正在上传图片",
                onUploadProgress: progressEvent => {
                    const percent = progressEvent.loaded / progressEvent.total
                    onProgress(percent, progressEvent)
                }
            }).then(res => {
                if (res.data.code === 200) {
                    onSuccess(res)
                    Notification.success(res.data.message)
                    this.info.logoUrl = res.data.data
                    store.commit("setLogoName", res.data.data)
                } else {
                    onError(res)
                    this.resetLogo(this)
                    Notification.error(res.data.message)
                }
            }).catch(res => {
                onError(res)
                this.resetLogo(this)
                Notification.error("服务器发生错误，上传超时")
            })
        },
        resetLogo() {
            if (this.$store.state.logoName.length > 0) {
                this.file = {
                    url: `${logoResourcePath}/${this.$store.state.logoName}`,
                    name: this.$store.state.logoName
                }
            } else {
                this.file = {}
            }
        }
    },
    mounted() {
        axiosx({
            method: "GET",
            url: "xtspz",
            message: "正在获取用户信息"
        }).then(res => {
            if (res.data.code === 200) {
                const data = res.data.data
                //显示图片
                if (data.logoUrl !== null) {
                    store.commit("setLogoName", data.logoUrl)
                    this.file = {
                        url: `${this.$store.state.logoResourcePath}/${this.$store.state.logoName}`,
                        name: this.$store.state.logoName
                    }
                } else {
                    this.file = {}
                }
                //显示信息
                this.info = data
            }
        })
    }
}
</script>

<template>
    <div class="full-screen d-flex align-items-center flex-column">
        <div class="title fs-1">血统书配置</div>
        <a-divider :size="2" style="border-bottom-style: dotted" orientation="left">信息</a-divider>
        <a-form ref="formRef" :model="info" @submit="submit" size="large" layout="horizontal" :auto-label-width="true">
            <!-- 图片上传 -->
            <a-form-item>
                <a-space size="large">
                    <div>LOGO</div>
                    <a-upload action="/xtspz/logo" :fileList="file ? [file] : []" :show-file-list="false" @change="onChange"
                        :custom-request="customRequest" accept="image/jpeg, image/png, image/gif, image/tiff">
                        <template #upload-button>
                            <div :class="`arco-upload-list-item${file && file.status === 'error' ? ' arco-upload-list-item-error' : ''
                                }`">
                                <div class="arco-upload-list-picture custom-upload-avatar" v-if="file && file.url">
                                    <img :src="file.url"  alt=""/>
                                    <div class="arco-upload-list-picture-mask">
                                        <IconEdit />
                                    </div>
                                    <a-progress v-if="file.status === 'uploading' && file.percent < 100"
                                        :percent="file.percent" type="circle" size="mini" :style="{
                                            position: 'absolute',
                                            left: '50%',
                                            top: '50%',
                                            transform: 'translateX(-50%) translateY(-50%)',
                                        }" />
                                </div>
                                <div class="arco-upload-picture-card" v-else>
                                    <div class="arco-upload-picture-card-text">
                                        <IconPlus />
                                        <div style="margin-top: 10px; font-weight: 600">上传logo</div>
                                    </div>
                                </div>
                            </div>
                        </template>
                    </a-upload>
                </a-space>
            </a-form-item>
            <a-form-item field="name" tooltip="你的鸽舍名称" label="鸽舍名称">
                <a-input v-model.lazy="info.name" placeholder="位于联系方式上方" />
            </a-form-item>
            <a-form-item field="shortName" tooltip="给鸽舍一个简称吧" label="鸽舍简称">
                <a-input v-model.lazy="info.shortName" placeholder="位于logo旁边" />
            </a-form-item>
            <a-form-item field="phone" tooltip="让别人能够通过移动设备联系你" label="联系电话">
                <a-input v-model.lazy="info.phone" placeholder="例如：13888888888" />
            </a-form-item>
            <a-form-item field="mail" tooltip="让别人能够通过移动邮箱联系你" label="联系邮箱">
                <a-input v-model.lazy="info.mail" placeholder="例如：aaa@163.com" />
            </a-form-item>
            <a-form-item field="url" tooltip="让别人能够通过移动网站发现你" label="鸽舍网址">
                <a-input v-model.lazy="info.url" placeholder="例如：www.sgmis.com" />
            </a-form-item>
            <a-form-item field="address" tooltip="具体地址" label="地址">
                <a-textarea v-model.lazy="info.address" placeholder="鸽舍地址" />
            </a-form-item>
            <!-- 提交 -->
            <a-divider :size="2" style="border-bottom-style: dotted" orientation="left">操作</a-divider>
            <a-form-item>
                <a-space>
                    <a-button type="primary" html-type="submit">保存</a-button>
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
        padding: 30px 90px;
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