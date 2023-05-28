<script>
import * as echarts from "echarts";
import {toDebounceFunction} from "../../../assets/js/debounce.js";
import axiosx from "../../../assets/js/axiosx.js";

export default {
    mounted() {
        this.$nextTick(async () => {
            //获取数据
            const sourceData = []
            const sourceLabel = []
            await axiosx({
                method: "GET",
                url: "statistic/source"
            }).then(res => {
                if (res.data.code === 200) {
                    const data = res.data.data
                    for (const key in data) {
                        sourceLabel.push(key)
                        sourceData.push(data[key])
                    }
                } else {
                    Notification.error(res.data.message)
                }
            })

            const opts = {
                renderer: "svg",
                locale: "ZH"
            }

            const option = {
                xAxis: {
                    type: 'category',
                    data: sourceLabel
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        data: sourceData,
                        type: 'bar'
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