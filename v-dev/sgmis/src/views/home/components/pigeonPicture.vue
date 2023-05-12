<script>
import axiosx from "../../../assets/js/axiosx.js";
import {Notification} from '@arco-design/web-vue'

export default {
    data() {
        return {
            images: [
                "/img/5f5e6a3fa9074d618e43dde7b634b537.jpg",
                "/img/6bb8bed978084d0190bdf9d1d9ac4684.jpg",
                "/img/8f1f44a46c8a413289f34c9d7fbc7db4.jpg"
            ]
        }
    },
    mounted() {
        //获取鸽子图片
        axiosx({
            method: "GET",
            url: "data/pigeon/picture"
        }).then(res => {
            if (res.data.code === 200) {
                const size = res.data.data.length
                this.images.splice(0, size)
                this.images.push(...res.data.data.map(item => {
                    return `${this.$store.state.pigeonResourcePath}/${item.pictureUrl}`
                }))
            } else {
                Notification.error(res.data.message)
            }
        })
    }
}
</script>

<template>
    <a-carousel :auto-play="{interval: 5000, hoverToPause:true}" :move-speed="700" animation-name="card"
                show-arrow="hover" indicator-type="dot"
                indicator-position="outer"
                :style="{height: '360px',}">
        <a-carousel-item v-for="image in images">
            <a-image :src="image" fit="contain" width="100%" height="100%"/>
        </a-carousel-item>
    </a-carousel>
</template>