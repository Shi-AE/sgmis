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

export {
    addLoading,
    clearLoading
}