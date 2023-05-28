<script>
import * as echarts from "echarts";
import {toDebounceFunction} from "../../../assets/js/debounce.js";
import axiosx from "../../../assets/js/axiosx.js";
import {Notification} from '@arco-design/web-vue'

export default {
    data() {
        return {
            color: [
                "#14C9C9",
                "#F5319D",
                "#9FDB1D",
                "#165DFF",
                "#722ED1",
                "#F53F3F",
                "#F77234",
                "#FADC19",
                "#F7BA1E"
            ]
        }
    },
    mounted() {
        this.$nextTick(async () => {
            //获取数据
            const sexData = []
            await axiosx({
                method: "GET",
                url: "statistic/sex"
            }).then(res => {
                if (res.data.code === 200) {
                    res.data.data.forEach((item, i) => {
                        sexData.push({
                            value: item.count,
                            name: item.sex,
                            itemStyle: {
                                color: this.color[i]
                            },
                        })
                    })
                    //添加下半圆
                    sexData.push({
                        value: sexData.reduce((p, c) => {
                            return p + c.value
                        }, 0),
                        itemStyle: {
                            //需要半圆显示，停止图表渲染这个片段
                            color: 'none',
                            decal: {
                                symbol: 'none'
                            }
                        },
                        label: {
                            show: false
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
                    left: 'center',
                    selectedMode: false
                },
                series: [
                    {
                        type: 'pie',
                        radius: ['40%', '70%'],
                        center: ['50%', '70%'],
                        //调整开始角度
                        startAngle: 180,
                        label: {
                            show: true,
                            formatter(param) {
                                // 修正百分比
                                return param.name + ' (' + param.percent * 2 + '%)';
                            }
                        },
                        data: sexData
                    }
                ]
            }

            const sexHalfDoughnutChart = echarts.init(this.$refs.sexHalfDoughnut, "dark", opts)

            sexHalfDoughnutChart.setOption(option)

            //绑定重置图标大小
            window.addEventListener("resize", toDebounceFunction(() => {
                sexHalfDoughnutChart.resize()
            }))
        })
    }
}
</script>

<template>
    <div ref="sexHalfDoughnut" class="table"></div>
</template>

<style scoped>
.table {
    height: 100%;
    width: 100%;
}
</style>