<script>
import axiosx from "../../../assets/js/axiosx.js";

export default {
    data() {
        return {
            log: [],
            color: [
                "#F08EE6",
                "#E865DF",
                "#E13EDB",
                "#D91AD9",
                "#B010B6",
                "#8A0993",
                "#650370"
            ]
        }
    },
    mounted() {
        axiosx({
            method: "GET",
            url: `oplog/${this.$route.params.id}/5`,
            message: "正在查询日志"
        }).then(res => {
            if (res.data.code) {
                this.log = res.data.data
            } else {
                Notification.error(res.data.message)
            }
        })
    }
}
</script>

<template>
    <div class="log">
        <a-timeline label-position="relative">
            <div v-for="(item, index) in log" :key="index">
                <a-timeline-item dot-type="hollow" :dot-color="color[index]" :label="item.time">
                    {{ item.tip }}
                </a-timeline-item>
            </div>
        </a-timeline>
        <a-button size="mini" shape="round" type="outline" @click="$router.push({name: 'log'})">更多</a-button>
    </div>
</template>

<style>
.log {
    margin: 20px;
}
</style>
