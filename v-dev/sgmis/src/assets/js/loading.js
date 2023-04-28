import store from "@/store"

const addLoading = message => {
    store.commit("setLoading", {
        loading: true,
        tip: message
    })
}

const clearLoading = () => {
    store.commit("setLoading", {
        loading: false
    })
}

const verifyData = (fun, message) => {
    message = message || "正在验证数据"
    addLoading(message)
    if (typeof fun !== "function") {
        clearLoading()
        throw new Error("第一个参数类型不是函数")
    }
    const res = fun()
    clearLoading()
    return res === undefined ? true : res
}

export {
    addLoading,
    clearLoading,
    verifyData
}