import axios from "axios"
import { addLoading, clearLoading } from "@/assets/js/loading.js"
import router from "@/router"
import store from "@/store"
import { Notification } from '@arco-design/web-vue'
import JSONbigint from "json-bigint"

const axiosx = axios.create({
    baseURL: "/api",
    timeout: 10000,
    transformResponse: [function (data) {
        // 使用json-bigint解析响应数据
        try {
            return JSONbigint.parse(data)
        } catch (error) {
            return data
        }
    }]
})

//请求拦截
axiosx.interceptors.request.use(config => {
    addLoading(config.message)
    store.commit("setPending", true)
    return {
        ...config,
        headers: {
            Authorization: store.getters.doneToken
        }
    }
}, error => {
    store.commit("setPending", false)
    return Promise.reject(error);
})

//响应拦截
axiosx.interceptors.response.use(response => {
    clearLoading()
    //恶意sql注入
    if (response.data.code === 410) {
        Notification.error(response.data.message)
        store.commit("setToken", "")
        router.push({ name: "403" })
    }
    //访问权限异常
    if (response.data.code === 403) {
        Notification.error(response.data.message)
        router.push({ name: "403" })
    }
    store.commit("setPending", false)
    return response;
}, error => {
    clearLoading()
    // router.push({ name: "500" })
    store.commit("setPending", false)
    return Promise.reject(error)
});

export default axiosx
