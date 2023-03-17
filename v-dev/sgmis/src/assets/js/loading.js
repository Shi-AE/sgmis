import store from "@/store"

const addLoading = message => {
    store.commit("setLoding", {
        loading: true,
        tip: message
    })
}

const clearLoading = () => {
    store.commit("setLoding", {
        loading: false
    })
}

const verifyData = (fun, message) => {
    message = message || "验证数据"
    addLoading(message)
    if (typeof fun !== "function") {
        clearLoading()
        throw new Error("第二个参数类型不是函数")
    }
    let res = fun()
    clearLoading()
    return res === undefined ? true : res
}

export {
    addLoading,
    clearLoading,
    verifyData
}