import { createStore } from 'vuex'
import createPersistedState from "vuex-persistedstate";

const store = createStore({
    plugins: [createPersistedState({
        reducer: state => {
            return {
                token: state.token,
                account: state.account
            }
        }
    })],
    state() {
        return {
            spin: {
                loading: false,
                tip: ""
            },
            token: "",
            account: "root"
        }
    },
    mutations: {
        setLoding(state, payload) {
            state.spin = payload
        },
        setToken(state, payload) {
            state.token = payload
        },
        setAccount(state, payload) {
            state.account = payload
        }
    },
    getters: {
        doneToken(state) {
            return state.token
        }
    }
})

export default store