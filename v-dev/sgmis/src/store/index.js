import { createStore } from 'vuex'
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
            token: "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3MTE2ODc3NjAsImluZm8iOnsiZ2lkIjoxNjQwNTQ2MjE0ODg3NjQ1MTg1LCJpcCI6IjEyNy4wLjAuMSIsImFkbWluIjp0cnVlLCJpZCI6MTYzOTI0NzcwMzIyNzM0Njk0NiwiYWNjb3VudCI6InJvb3QifX0.qY5KJrhuU_NwOwyRUDtacgesAjQGSSxXPR-9HWSfFD4",
            account: "root",
            admin: false,
            isPending: false,
            logoName: ""
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