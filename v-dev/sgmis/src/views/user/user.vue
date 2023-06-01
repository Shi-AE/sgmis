<script>
import {verifyData} from "@/assets/js/loading.js"
import axiosx from "@/assets/js/axiosx.js"

export default {
    data() {
        return {
            form: {
                oldPassword: "",
                newPassword: "",
                confirmPassword: ""
            },
            account: this.$store.state.account,
            admin: this.$store.state.admin
        }
    },
    methods: {
        submit() {
            let verifyRes = verifyData(() => {
                // 比较新密码与确认密码
                let oldPassword = this.form.oldPassword
                let newPassword = this.form.newPassword
                let confirmPassword = this.form.confirmPassword
                if (newPassword.length < 6) {
                    this.$notification.error("新密码过短")
                    return false
                }
                if (newPassword !== confirmPassword) {
                    this.$notification.error("确认密码失败")
                    return false
                }
                if (oldPassword === newPassword) {
                    this.$notification.error("密码未变更")
                    return false
                }
            })
            if (verifyRes) {
                axiosx({
                    method: "PUT",
                    url: "/login",
                    data: {
                        account: this.$store.state.account,
                        ...this.form
                    }
                }).then((res) => {
                    this.form.oldPassword = ""
                    this.form.newPassword = ""
                    this.form.confirmPassword = ""
                    if (res.data.code === 204) {
                        this.$store.commit("setToken", "")
                        this.$store.commit("setAccount", "")
                        this.$router.push({name: "login"})
                        this.$notification.success("密码修改成功")
                    } else {
                        this.$notification.error(res.data.message)
                    }
                })
            }
        }
    }
}
</script>
<template>
    <div class="full-screen">
        <a-divider :size="2" style="border-bottom-style: dotted" orientation="left">修改当前密码</a-divider>
        <div class="form">
            <a-form size="large" :model="form">
                <a-form-item label="账号" disabled>
                    <a-input v-model.lazy="account"></a-input>
                </a-form-item>
                <a-form-item label="管理员" disabled tooltip="如需管理员身份请前往反馈中心联系系统管理员">
                    <a-radio-group v-model.lazy="admin">
                        <a-radio :value="true">管理员</a-radio>
                        <a-radio :value="false">成员</a-radio>
                    </a-radio-group>
                </a-form-item>
                <a-form-item label="原密码">
                    <a-input-password v-model.lazy="form.oldPassword"></a-input-password>
                </a-form-item>
                <a-form-item label="新密码">
                    <a-input-password v-model.lazy="form.newPassword"></a-input-password>
                </a-form-item>
                <a-form-item label="确认密码">
                    <a-input-password v-model.lazy="form.confirmPassword"></a-input-password>
                </a-form-item>
                <a-form-item>
                    <a-button type="primary" @click="submit()">修改密码</a-button>
                </a-form-item>
            </a-form>
        </div>
    </div>
</template>

<style scoped lang="scss">
.full-screen {
    width: 100%;

    form {
        width: 100%;
        max-width: 720px;
    }
}
</style>