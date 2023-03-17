import axios from "axios"
import { addLoading, clearLoading } from "@/assets/js/loading.js"
import router from "@/router"
import store from "@/store"
import {Notification} from '@arco-design/web-vue'

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
    if (response.data.code === 403) {
        Notification.error(response.data.message)
        router.push({ name: "403" })
    }
    return response;
}, error => {
    clearLoading()
    router.push({ name: "500" })
    return Promise.reject(error)
});

export default axiosx
