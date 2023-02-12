ready(() => {
    Vue.createApp({
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
                axios({
                    method: "post",
                    url: "/login",
                    data: this.formMess
                }).then((res) => {
                    console.log(res);
                })
            }
        }
    }).mount("#login");
})