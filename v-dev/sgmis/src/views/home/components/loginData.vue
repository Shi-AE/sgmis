<script>
import axiosx from "../../../assets/js/axiosx.js";
import {Notification} from "@arco-design/web-vue";
import * as echarts from "echarts";
import {toDebounceFunction} from "../../../assets/js/debounce.js";
import dayjs from "dayjs";

export default {
    data() {
        return {
            loginMsg: [],
            color: [
                "#F08EE6",
                "#E865DF",
                "#E13EDB",
                "#D91AD9",
                "#B010B6",
                "#8A0993",
                "#650370"
            ],
            lineColor: [
                "#722ED1",
                "#9FDB1D",
                "#F53F3F",
                "#F77234",
                "#FADC19",
                "#14C9C9",
                "#F7BA1E",
                "#F5319D",
                "#165DFF"
            ]
        }
    },
    methods: {
        getLoginMsgData() {
            axiosx({
                method: "GET",
                url: "data/loginMsg/5"
            }).then(res => {
                if (res.data.code === 200) {
                    this.loginMsg = res.data.data
                } else {
                    Notification.error(res.data.message)
                }
            })
        },
        async createLoginLine() {
            //获取登录数据
            const legendData = []
            const seriesList = []
            //临时计数map
            const countMap = {}
            await axiosx({
                method: "GET",
                url: "data/login/count"
            }).then(res => {
                if (res.data.code === 200) {
                    //数据转化为map
                    const dataMap = {}
                    res.data.data.forEach(item => {
                        const account = item.account
                        //出现新用户，设置图例信息
                        if (!countMap[account]) {
                            //设置图例
                            legendData.push(account)
                            //设置线条
                            countMap[account] = {
                                name: account,
                                type: 'line',
                                itemStyle: {
                                    //调色
                                    color: legendData.length <= this.lineColor.length ? this.lineColor[legendData.length - 1] : null
                                },
                                data: []
                            }
                            //设置数据map
                            dataMap[account] = {}
                        }
                        dataMap[account][item.time] = item.count
                    })

                    //从当前日期开始向前设定（30）天数创建时间表
                    const recent = this.$store.state.recent
                    let time = dayjs()
                    for (let i = 0; i < recent; i++) {
                        const format = time.format("YYYY-MM-DD");
                        for (const j in legendData) {
                            const account = legendData[j];
                            countMap[account].data.push([format, dataMap[account][format] || 0])
                        }
                        time = time.subtract(1, 'day')
                    }

                    for (const key in countMap) {
                        seriesList.push(countMap[key])
                    }
                } else {
                    Notification.error(res.data.message)
                }
            })

            const opts = {
                renderer: "svg",
                locale: "ZH"
            }

            const oplogTotalBar = echarts.init(this.$refs.loginLine, "dark", opts)

            const option = {
                title: {
                    text: "登录信息"
                },
                legend: {
                    data: legendData
                },
                tooltip: {
                    trigger: 'axis'
                },
                grid: {
                    left: '3%',
                    right: '10%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: {
                    type: 'time',
                    name: '日期',
                    boundaryGap: false,
                    axisLabel: {
                        formatter: '{MM}/{dd}'
                    }
                },
                yAxis: {
                    name: '操作',
                    type: 'value'
                },
                series: seriesList
            }

            oplogTotalBar.setOption(option)

            //绑定重置图标大小
            window.addEventListener("resize", toDebounceFunction(() => {
                oplogTotalBar.resize()
            }))
        }
    },
    mounted() {
        this.$nextTick(() => {
            this.getLoginMsgData()
            this.createLoginLine()
        })
    }
}
</script>

<template>
    <a-grid :cols="{xs: 1, sm: 1, xl: 2}" :col-gap="2">
        <a-grid-item>
            <div ref="loginLine" class="table"></div>
        </a-grid-item>
        <a-grid-item>
            <div class="longinData">
                <a-timeline label-position="relative">
                    <div v-for="(item, index) in loginMsg" :key="index">
                        <a-timeline-item dot-type="hollow" :dot-color="color[index]" :label="item.time">
                            <div>{{ item.account }}</div>
                            <div>{{ item.ip }}</div>
                            <div>{{ item.os }}</div>
                        </a-timeline-item>
                    </div>
                </a-timeline>
                <a-button size="mini" shape="round" type="outline" @click="$router.push({name: 'loginMsg'})">更多</a-button>
            </div>
        </a-grid-item>
    </a-grid>
</template>

<style scoped>
.longinData {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    height: 100%;
    padding: 20px;
}

.table {
    width: 100%;
    height: 500px;
}
</style>
