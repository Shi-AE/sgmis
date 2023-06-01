import {createApp} from 'vue'
import ArcoVue from '@arco-design/web-vue'
import App from './App.vue'
import '@arco-design/web-vue/dist/arco.css'
import Router from "./router"
import '@/assets/css/style.css'
import store from './store'

createApp(App).use(ArcoVue).use(Router).use(store).mount('#app')
