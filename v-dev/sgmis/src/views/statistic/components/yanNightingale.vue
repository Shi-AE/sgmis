<script>
import * as echarts from "echarts";
import {toDebounceFunction} from "../../../assets/js/debounce.js";
import axiosx from "../../../assets/js/axiosx.js";
import {Notification} from "@arco-design/web-vue";

export default {
    mounted() {
        this.$nextTick(async () => {
            //获取数据
            let yanData = []
            await axiosx({
                method: "GET",
                url: "statistic/yan"
            }).then(res => {
                if (res.data.code === 200) {
                    yanData = res.data.data.map(item => {
                        return {
                            value: item.count,
                            name: item.yan
                        }
                    })
                } else {
                    Notification.error(res.data.message)
                }
            })

            const opts = {
                renderer: "svg",
                locale: "ZH"
            }

            const option = {
                legend: {
                    top: 'bottom'
                },
                toolbox: {
                    show: true
                },
                tooltip: {
                    trigger: 'item'
                },
                series: [
                    {
                        type: 'pie',
                        radius: [50, 250],
                        center: ['50%', '50%'],
                        roseType: 'area',
                        itemStyle: {
                            borderRadius: 8
                        },
                        data: yanData
                    }
                ]
            }

            const chart = echarts.init(this.$refs.chartRef, "dark", opts)

            chart.setOption(option)

            //绑定重置图标大小
            window.addEventListener("resize", toDebounceFunction(() => {
                chart.resize()
            }))
        })
    }
}
</script>

<template>
    <div ref="chartRef" class="table"></div>
</template>

<style scoped>
.table {
    height: 100%;
    width: 100%;
}
</style>