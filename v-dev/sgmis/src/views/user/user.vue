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
            }
        }
    },
    methods: {
        submit() {
            let verifyRes = verifyData(() => {
                // 比较新密码与确认密码
                let oldPassword = this.form.oldPassword
                let newPassword = this.form.newPassword
                let confirmPassword = this.form.confirmPassword
                if (newPassword.length < 8) {
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
                        this.$router.push({ name: "login" })
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
    <div class="full-screen d-flex align-items-center flex-column">
        <div class="title fs-1">修改密码</div>
        <form class="d-flex flex-column" @submit.prevent="submit">
            <div class="form-floating mb-3">
                <input type="password" class="form-control" placeholder="name@example.com" v-model.lazy="form.oldPassword"
                    required>
                <label for="oldPassword">当前密码</label>
            </div>
            <div class="form-floating mb-3">
                <input type="password" class="form-control" placeholder="name@example.com"
                    v-model.lazy="form.newPassword" required>
                <label for="newPassword">新密码（不少于8个字符）</label>
            </div>
            <div class="form-floating mb-3">
                <input type="password" class="form-control" placeholder="name@example.com"
                    v-model.lazy="form.confirmPassword" required>
                <label for="confirmPassword">确认密码</label>
            </div>
            <input type="submit" class="btn btn-primary" value="确认修改密码">
        </form>
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