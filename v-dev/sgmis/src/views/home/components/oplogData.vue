<script>
import * as echarts from "echarts";
import axiosx from "../../../assets/js/axiosx.js";
import {Notification} from "@arco-design/web-vue";
import {toDebounceFunction} from "../../../assets/js/debounce.js";

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
    methods: {
        //创建oplogLine图
        async createOplogLine() {
            //获取数据
            const contentMap = {
                0: [], 1: [], 2: [], 3: [], 4: [], 5: [], 6: [], 7: [], 8: []
            }
            await axiosx({
                method: "GET",
                url: "data/oplog/line"
            }).then(res => {
                if (res.data.code === 200) {
                    res.data.data.forEach(item => {
                        contentMap[item.content].push([
                            item.time, item.count
                        ])
                    })
                } else {
                    Notification.error(res.data.message)
                }
            })

            const opts = {
                renderer: "svg",
                locale: "ZH"
            }

            const oplogLine = echarts.init(this.$refs.oplogLine, "dark", opts)

            const option = {
                title: {
                    text: "操作数据变化"
                },
                legend: {
                    data: ['新增', '修改', '删除', '共享血统', '接收血统', '关联血亲', '解除血亲', '转移鸽棚巢箱', '其他']
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
                series: [
                    {
                        name: '新增',
                        type: 'line',
                        itemStyle: {
                            color: "#722ED1"
                        },
                        data: contentMap[0]
                    },
                    {
                        name: '修改',
                        type: 'line',
                        itemStyle: {
                            color: "#9FDB1D"
                        },
                        data: contentMap[1]
                    },
                    {
                        name: '删除',
                        type: 'line',
                        itemStyle: {
                            color: "#F53F3F"
                        },
                        data: contentMap[2]
                    },
                    {
                        name: '共享血统',
                        type: 'line',
                        itemStyle: {
                            color: "#F77234"
                        },
                        data: contentMap[3]
                    },
                    {
                        name: '接收血统',
                        type: 'line',
                        itemStyle: {
                            color: "#FADC19"
                        },
                        data: contentMap[4]
                    },
                    {
                        name: '关联血亲',
                        type: 'line',
                        itemStyle: {
                            color: "#14C9C9"
                        },
                        data: contentMap[5]
                    },
                    {
                        name: '解除血亲',
                        type: 'line',
                        itemStyle: {
                            color: "#F7BA1E"
                        },
                        data: contentMap[6]
                    },
                    {
                        name: '转移鸽棚巢箱',
                        type: 'line',
                        itemStyle: {
                            color: "#F5319D"
                        },
                        data: contentMap[7]
                    },
                    {
                        name: '其他',
                        type: 'line',
                        itemStyle: {
                            color: "#165DFF"
                        },
                        data: contentMap[8]
                    }
                ]
            }

            oplogLine.setOption(option)

            //绑定重置图标大小
            window.addEventListener("resize", toDebounceFunction(() => {
                oplogLine.resize()
            }))
        },
        //创建oplogTotal图
        async createOplogTotal() {

            //获取分组统计数据
            const countDataBar = Array(9).fill(0)
            const countDataPie = [
                {name: "新增", value: 0},
                {name: "修改", value: 0},
                {name: "删除", value: 0},
                {name: "共享血统", value: 0},
                {name: "接收血统", value: 0},
                {name: "关联血亲", value: 0},
                {name: "解除血亲", value: 0},
                {name: "转移鸽棚巢", value: 0},
                {name: "其他", value: 0},
            ]
            await axiosx({
                method: "GET",
                url: "data/oplog/content/total"
            }).then(res => {
                if (res.data.code === 200) {
                    res.data.data.forEach(item => {
                        countDataBar[item.content] = item.count
                        countDataPie[item.content].value = item.count
                    })
                } else {
                    Notification.error(res.data.message)
                }
            })

            const opts = {
                renderer: "svg",
                locale: "ZH"
            }

            const oplogTotalBar = echarts.init(this.$refs.oplogTotalBar, "dark", opts)

            const option = {
                title: {
                    text: "操作总数统计"
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
                    type: 'category',
                    name: '操作类型',
                    data: ['新增', '修改', '删除', '共享血统', '接收血统', '关联血亲', '解除血亲', '转移鸽棚巢箱', '其他']
                },
                yAxis: {
                    name: '操作',
                    type: 'value'
                },
                series: [
                    {
                        type: "bar",
                        data: countDataBar
                    }
                ]
            }

            oplogTotalBar.setOption(option)

            //绑定重置图标大小
            window.addEventListener("resize", toDebounceFunction(() => {
                oplogTotalBar.resize()
            }))


            const oplogTotalPie = await echarts.init(this.$refs.oplogTotalPie, "light", opts)

            const optionPie = {
                title: {
                    text: "操作总数统计"
                },
                tooltip: {
                    trigger: 'item'
                },
                legend: {
                    top: '5%',
                    left: 'center'
                },
                series: [
                    {
                        type: 'pie',
                        radius: ['40%', '70%'],
                        avoidLabelOverlap: false,
                        itemStyle: {
                            borderRadius: 10,
                            borderColor: '#fff',
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
                        data: countDataPie
                    }
                ]
            }

            oplogTotalPie.setOption(optionPie)

            //绑定重置图标大小
            window.addEventListener("resize", toDebounceFunction(() => {
                oplogTotalPie.resize()
            }))
        },
        createOplogData() {
            axiosx({
                method: "GET",
                url: "data/oplog/data/5"
            }).then(res => {
                if (res.data.code === 200) {
                    this.log = res.data.data
                } else {
                    Notification.error(res.data.message)
                }
            })
        }
    },
    mounted() {
        this.$nextTick(() => {
            this.createOplogLine()
            this.createOplogTotal()
            this.createOplogData()
        })
    }
}
</script>

<template>
    <a-grid :cols="{xs: 1, sm: 1, xl: 2}" :col-gap="2">
        <a-grid-item>
            <div ref="oplogLine" class="table"></div>
        </a-grid-item>
        <a-grid-item>
            <div class="oplogData">
                <a-timeline label-position="relative">
                    <div v-for="(item, index) in log" :key="index">
                        <a-timeline-item dot-type="hollow" :dot-color="color[index]" :label="item.time">
                            {{ item.tip }}
                        </a-timeline-item>
                    </div>
                </a-timeline>
                <a-button size="mini" shape="round" type="outline" @click="$router.push({name: 'log'})">更多</a-button>
            </div>
        </a-grid-item>
        <a-grid-item>
            <div ref="oplogTotalPie" class="table"></div>
        </a-grid-item>
        <a-grid-item>
            <div ref="oplogTotalBar" class="table"></div>
        </a-grid-item>
    </a-grid>
</template>

<style scoped>
.table {
    width: 100%;
    height: 500px;
}

.oplogData {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    height: 100%;
    padding: 20px;
}
</style>
