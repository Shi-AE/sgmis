<script>
import * as echarts from "echarts";
import {toDebounceFunction} from "../../../assets/js/debounce.js";
import axiosx from "../../../assets/js/axiosx.js";
import {Notification} from "@arco-design/web-vue";

export default {
    mounted() {
        this.$nextTick(async () => {
            //获取数据
            let jbData = []
            await axiosx({
                method: "GET",
                url: "statistic/jb"
            }).then(res => {
                if (res.data.code === 200) {
                    jbData = res.data.data.map(item => {
                        return {
                            value: item.count,
                            name: item.jb
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
                tooltip: {
                    trigger: 'item'
                },
                legend: {
                    top: '5%',
                    left: 'center'
                },
                series: [
                    {
                        name: 'Access From',
                        type: 'pie',
                        radius: ['40%', '70%'],
                        avoidLabelOverlap: false,
                        itemStyle: {
                            borderRadius: 10,
                            borderWidth: 2
                        },
                        label: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            label: {
                                show: true,
                                fontSize: 40,
                                fontWeight: 'bold'
                            }
                        },
                        labelLine: {
                            show: false
                        },
                        data: jbData
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