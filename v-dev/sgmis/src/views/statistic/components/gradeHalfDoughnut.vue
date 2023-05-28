<script>
import * as echarts from "echarts";
import {toDebounceFunction} from "../../../assets/js/debounce.js"
import axiosx from "../../../assets/js/axiosx.js"
import {Notification} from '@arco-design/web-vue'

export default {
    data() {
        return {
            color: [
                "#722ED1",
                "#F77234",
                "#14C9C9",
                "#F5319D",
                "#9FDB1D",
                "#165DFF",
                "#F53F3F",
                "#FADC19",
                "#F7BA1E"
            ]
        }
    },
    mounted() {
        this.$nextTick(async () => {
            //获取数据
            const gradeData = []
            await axiosx({
                method: "GET",
                url: "statistic/grade"
            }).then(res => {
                if (res.data.code === 200) {
                    res.data.data.forEach((item, i) => {
                        gradeData.push({
                            value: item.count,
                            name: item.isGrade,
                            itemStyle: {
                                color: this.color[i]
                            },
                        })
                    })
                    //添加下半圆
                    gradeData.push({
                        value: gradeData.reduce((p, c) => {
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
                        data: gradeData
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