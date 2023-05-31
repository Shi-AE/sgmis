<script>
import * as echarts from "echarts"
import axiosx from "../../../assets/js/axiosx.js";
import {Notification} from "@arco-design/web-vue";
import {toDebounceFunction} from "../../../assets/js/debounce.js";

export default {
    data() {
        return {
            totalPigeon: 0,
            totalOplog: 0,
            online: 0,
            onlineGroup: 0,
        }
    },
    async mounted() {
        //获取新建量
        const createData = [];
        await axiosx({
            method: "GET",
            url: "data/create"
        }).then(res => {
            if (res.data.code === 200) {
                const data = res.data.data
                for (const key in data) {
                    createData.push([key, data[key]])
                }
                createData.sort((a, b) => {
                    return a[0] > b[0] ? 1 : -1
                })
            } else {
                Notification.error(res.data.message)
            }
        })
        //获取删除量
        const deleteData = await axiosx({
            method: "GET",
            url: "data/delete"
        }).then(res => {
            if (res.data.code === 200) {
                return res.data.data.map(item => {
                    return [item.time, item.count]
                })
            } else {
                Notification.error(res.data.message)
            }
        })

        const opts = {
            renderer: "svg",
            locale: "ZH"
        }

        const pigeonLine = echarts.init(this.$refs.pigeonLine, "dark", opts)

        const option = {
            title: {
                text: "库中鸽子数据"
            },
            legend: {
                data: ['创建', '删除']
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
                    name: '创建',
                    type: 'line',
                    itemStyle: {
                        color: "#14C9C9"
                    },
                    data: createData
                },
                {
                    name: '删除',
                    type: 'line',
                    itemStyle: {
                        color: "#D91AD9"
                    },
                    data: deleteData
                }
            ]
        }

        pigeonLine.setOption(option)

        //绑定重置图标大小
        window.addEventListener("resize", toDebounceFunction(() => {
            pigeonLine.resize()
        }))

        //获取鸽子总数
        axiosx({
            method: "GET",
            url: "data/pigeon/total"
        }).then(res => {
            if (res.data.code === 200) {
                this.totalPigeon = res.data.data.total
            } else {
                Notification.error(res.data.message)
            }
        })
        //获取操作总数
        axiosx({
            method: "GET",
            url: "data/oplog/total"
        }).then(res => {
            if (res.data.code === 200) {
                this.totalOplog = res.data.data.total
            } else {
                Notification.error(res.data.message)
            }
        })
        //获取当前系统在线人数
        axiosx({
            method: "GET",
            url: "data/online"
        }).then(res => {
            if (res.data.code === 200) {
                this.online = res.data.data
            } else {
                Notification.error(res.data.message)
            }
        })
        //获取团队在线人数
        axiosx({
            method: "GET",
            url: "data/online/group"
        }).then(res => {
            if (res.data.code === 200) {
                this.onlineGroup = res.data.data
            } else {
                Notification.error(res.data.message)
            }
        })
    }
}
</script>

<template>
    <a-grid :cols="{xs: 1, sm: 1, md: 2}">
        <a-grid-item>
            <div class="statistic">
                <a-space size="large">
                    <!--在线总数-->
                    <a-statistic title="在线设备总数" :value="online" :value-style="{ color: '#0fbf60' }" animation/>
                    <!--组员在线数-->
                    <a-statistic extra="组员在线设备数" :value="onlineGroup" :value-style="{ color: '#F77234' }" animation/>
                </a-space>
                <a-space size="large">
                    <!--鸽子总数-->
                    <a-statistic title="库中鸽子总数" :value="totalPigeon" :value-style="{ color: '#14C9C9' }"
                                 animation/>
                    <!--操作总数-->
                    <a-statistic extra="操作总数" :value="totalOplog" :value-style="{ color: '#D91AD9' }" animation/>
                </a-space>
            </div>
        </a-grid-item>
        <a-grid-item>
            <!--库中鸽子创建、删除时间变化图-->
            <div ref="pigeonLine" class="pigeonLine"></div>
        </a-grid-item>
    </a-grid>
</template>

<style scoped>
.pigeonLine {
    width: 100%;
    height: 500px;
}

.statistic {
    display: flex;
    flex-direction: column;
    justify-content: space-evenly;
    align-items: center;
    height: 100%;
}
</style>