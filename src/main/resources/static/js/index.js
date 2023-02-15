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
                let spinner = Spinner(color.green);
                axios({
                    method: "post",
                    url: "/login",
                    data: this.formMess
                }).then((res) => {
                    spinner.remove();
                    console.log(res);
                    switch(res.data.code) {
                        case 5001:
                        case 5002: showMessagePopup({
                            type: popupType.error,
                            message: res.data.message
                        });break;
                        case 2000: showMessagePopup({
                            type: popupType.success,
                            message: res.data.message
                        });break;
                    }
                    if (res.data.code === 2000) {
                        location.href = "main/home.html";
                    }
                }).catch((res) => {
                    spinner.remove();
                    location.href = `error/systemError.html?message=${res}`;
                })
            }
        }
    }).mount("#login");
});