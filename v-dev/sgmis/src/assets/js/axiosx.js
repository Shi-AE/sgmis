import axios from "axios"
import {addLoading, clearLoading} from "@/assets/js/loading.js"
import router from "@/router"
import store from "@/store"
import {Notification} from '@arco-design/web-vue'
import JSONbigint from "json-bigint"

//定义键盘事件
function preventKeyDown(event) {
    event.preventDefault();
    event.stopPropagation();
}

const axiosx = axios.create({
    baseURL: "/api",
    timeout: 10000,
    transformResponse: [function (data) {
        // 使用json-bigint解析Long型响应数据
        try {
            return JSONbigint.parse(data)
        } catch (error) {
            return data
        }
    }]
})

//请求拦截
axiosx.interceptors.request.use(config => {
    //禁止键盘事件
    document.addEventListener('keydown', preventKeyDown, false);
    //添加加载图标
    addLoading(config.message)
    //设置为正在发送请求
    store.commit("setPending", true)
    return {
        ...config,
        //添加请求头
        headers: {
            ...config.headers,

            Authorization: store.getters.doneToken
        }
    }
}, error => {
    //解除键盘事件
    document.removeEventListener('keydown', preventKeyDown, false);
    store.commit("setPending", false)
    return Promise.reject(error);
})

//响应拦截
axiosx.interceptors.response.use(response => {
    clearLoading()
    //解除键盘事件
    document.removeEventListener('keydown', preventKeyDown, false);
    //恶意sql注入
    if (response.data.code === 411) {
        Notification.error(response.data.message)
        store.commit("setToken", "")
        router.push({name: "403"})
    }
    //访问权限异常
    if (response.data.code === 403) {
        Notification.error(response.data.message)
        router.push({name: "403"})
    }
    //更新token防止活跃用户过期
    if (response.headers.authorization) {
        store.commit("setToken", response.headers.authorization)
    }
    store.commit("setPending", false)
    return response;
}, error => {
    clearLoading()
    //解除键盘事件
    document.removeEventListener('keydown', preventKeyDown, false);
    // router.push({ name: "500" })
    Notification.error(`${error.message} ${error.code}`)
    store.commit("setPending", false)
    return Promise.reject(error)
});

export default axiosx
