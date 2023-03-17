import axios from "axios"
import { addLoading, clearLoading } from "@/assets/js/loading.js"
import router from "@/router"
import store from "@/store"

const axiosx = axios.create({
    baseURL: "/api",
    timeout: 10000,
})

//请求拦截
axiosx.interceptors.request.use(config => {
    addLoading(config.message)
    return {
        ...config,
        headers: {
            Authorization: store.getters.doneToken
        }
    }
}, error => {
    return Promise.reject(error);
})

//响应拦截
axiosx.interceptors.response.use(response => {
    clearLoading()
    return response;
}, error => {
    clearLoading()
    router.push({ name: "500" })
    return Promise.reject(error)
});

export default axiosx
