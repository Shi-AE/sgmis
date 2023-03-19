import {createStore} from 'vuex'
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
            token: "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2ODkzODY3MTEsImFjY291bnQiOiJyb290In0.Aase1fm80R-A7Efjhy1bExtXsuSjPRudUWMaUikB4T0",
            account: "root",
            isPending: false
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
        },
        setPending(state, payload) {
            state.isPending = payload
        }
    },
    getters: {
        doneToken(state) {
            return state.token
        }
    }
})

export default store