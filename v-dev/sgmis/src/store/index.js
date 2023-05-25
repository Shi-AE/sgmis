import {createStore} from 'vuex'
import createPersistedState from "vuex-persistedstate";

const store = createStore({
    plugins: [createPersistedState({
        reducer: state => {
            return {
                token: state.token,
                account: state.account,
                admin: state.admin
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
            account: "root",
            admin: false,
            isPending: false,
            logoName: "",
            pigeonResourcePath: `/pigeon`,
            logoResourcePath: `/logo`
        }
    },
    mutations: {
        setLoading(state, payload) {
            state.spin = payload
        },
        setToken(state, payload) {
            state.token = payload
        },
        setAccount(state, payload) {
            state.account = payload
        },
        setAdmin(state, payload) {
            state.admin = payload
        },
        setPending(state, payload) {
            state.isPending = payload
        },
        setLogoName(state, payload) {
            state.logoName = payload
        }
    },
    getters: {
        doneToken(state) {
            return state.token
        }
    }
})

export default store