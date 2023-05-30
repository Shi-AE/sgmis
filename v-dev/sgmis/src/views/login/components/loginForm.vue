<script>
import axiosx from "@/assets/js/axiosx.js"
import store from "@/store"

export default {
    data() {
        return {
            formMess: {
                account: "",
                password: ""
            }
        }
    },
    methods: {
        submit() {
            axiosx({
                method: "POST",
                url: "/login/authority",
                data: this.formMess,
                message: "正在验证登录信息"
            }).then((res) => {
                if (res.data.code === 201) {
                    this.$notification.success(res.data.message)
                    store.commit("setToken", res.data.data.token)
                    store.commit("setAccount", this.formMess.account)
                    store.commit("setAdmin", res.data.data.admin)
                    this.$router.push({ name: "home" })
                } else {
                    this.$notification.error(res.data.message)
                }
                this.formMess.account = ""
                this.formMess.password = ""
            })
        }
    }
}
</script>
<template>
    <div class="login-form">
        <form @submit.prevent="submit">
            <div class="form-floating mb-3">
                <input type="text" class="form-control" placeholder="name@example.com" v-model.lazy="formMess.account"
                    required>
                <label for="account">账号</label>
            </div>
            <div class="form-floating mb-3">
                <input type="password" class="form-control" placeholder="name@example.com" v-model.lazy="formMess.password"
                    required>
                <label for="password">密码</label>
            </div>
            <input type="submit" class="btn btn-primary" value="登录">
        </form>
    </div>
</template>
<style scoped lang="scss">
.login-form {
    flex: 1;
    display: flex;
    align-items: start;
    width: 100%;
    max-width: 480px;
}

.login-form form {
    width: 100%;
    background-color: #ffffff90;
    padding: 50px 30px;
    border-radius: 20px;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    box-shadow: 0 0 10px #00000050;
    backdrop-filter: blur(10px)
}

.btn-primary {
    background-color: #F754A8 !important;
    border: #F979B7 solid var(--bs-danger-border-color) !important
}

.btn-primary:active {
    background-color: #D91AD9 !important;
}

.form-control:focus {
    border-color: #F979B7 !important;
    box-shadow: 0 0 0 0.25rem #f754a835 !important;
}

.login-form form * {
    margin: 5px 0;
}

input[type="text"],
[type="password"] {
    background-color: #ffffff50;
}
</style>