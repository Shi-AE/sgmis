<script>
import axiosx from "../../assets/js/axiosx.js";
import {Notification} from '@arco-design/web-vue'

export default {
    data() {
        return {
            columns: [
                {
                    title: "类型",
                    dataIndex: "content",
                    align: "center"
                },
                {
                    title: "信息",
                    dataIndex: "tip",
                    align: "center",
                    ellipsis: true,
                    tooltip: true
                },
                {
                    title: "足环",
                    slotName: "ringNumber",
                    align: "center"
                },
                {
                    title: "时间",
                    dataIndex: "time",
                    align: "center"
                },
                {
                    title: "人员",
                    dataIndex: "author",
                    align: "center"
                }
            ],
            data: [],
            //分页器参数
            paginationProps: {
                total: 0,
                current: 1,
                pageSize: 10,
                showPageSize: true,
                pageSizeOptions: [
                    5, 10, 20, 30, 40, 50, "所有"
                ],
                showJumper: true,
                showTotal: true
            },
            //查询条件
            condition: {
                content: null,
                tip: null,
                ringNumber: null,
                timeRange: null,
                author: null
            },
            contentOptions: [
                {label: "新增", value: 0},
                {label: "修改", value: 1},
                {label: "删除", value: 2},
                {label: "共享血统", value: 3},
                {label: "接收血统", value: 4},
                {label: "关联血亲", value: 5},
                {label: "解除血亲", value: 6},
                {label: "转移鸽棚巢箱", value: 7},
                {label: "其他", value: 8},
            ]
        }
    },
    methods: {
        getPage() {
            const condition = this.condition
            const paginationProps = this.paginationProps
            axiosx({
                method: "POST",
                url: "oplog",
                data: {
                    content: condition.content || null,
                    tip: condition.tip || null,
                    ringNumber: condition.ringNumber || null,
                    timeRange: condition.timeRange,
                    author: condition.author || null,
                    current: paginationProps.current,
                    pageSize: paginationProps.pageSize
                }
            }).then(res => {
                if (res.data.code === 200) {
                    const page = res.data.data
                    this.data = page.records.map(item => {
                        return {
                            ...item,
                            content: this.contentOptions[item.content].label
                        }
                    })
                    this.paginationProps.total = page.total
                } else {
                    Notification.error(res.data.message)
                }
            })
        },
        pageSizeChange(size) {
            if (size === "所有") {
                this.paginationProps.pageSize = this.paginationProps.total
                return
            }
            this.paginationProps.pageSize = size
            this.paginationProps.current = 1
            this.getPage()
        },
        pageChange(page) {
            this.paginationProps.current = page
            this.getPage()
        },
        commitCondition() {
            this.paginationProps.current = 1
            this.getPage()
        },
        clearCondition() {
            this.condition = {
                content: null,
                tip: null,
                ringNumber: null,
                timeRange: null,
                author: null
            }
            this.getPage()
        },
        handleCheckPigeon(pid) {
            if (!pid) {
                Notification.warning("此鸽子已删除，或存在")
                return
            }
            axiosx({
                method: "GET",
                url: `pigeon/exist/${pid}`,
                message: "检查鸽子是否存在"
            }).then(res => {
                if (res.data.code === 200) {
                    if (res.data.data) {
                        this.$router.push({name: 'detail', params: {id: pid}})
                    } else {
                        Notification.warning("此鸽子已删除，或存在")
                    }
                } else {
                    Notification.error(res.data.message)
                }
            })
        }
    },
    mounted() {
        this.getPage()
    }
}
</script>

<template>
    <a-divider :size="2" style="border-bottom-style: dotted" orientation="left">设置条件</a-divider>
    <a-form :model="condition" auto-label-width :style="{padding: '20px'}">
        <a-grid :cols="{xxl: 4,lg: 3 ,md: 2 ,sm: 1 ,xs: 1}" :col-gap="10">
            <a-form-item label="类型">
                <a-select v-model.lazy="condition.content" :options="contentOptions" placeholder="类型"
                          allow-clear/>
            </a-form-item>
            <a-form-item label="信息">
                <a-input v-model.lazy="condition.tip" placeholder="信息" allow-clear></a-input>
            </a-form-item>
            <a-form-item label="足环">
                <a-input v-model.lazy="condition.ringNumber" placeholder="足环" allow-clear></a-input>
            </a-form-item>
            <a-form-item label="时间">
                <a-range-picker allow-clear show-time v-model.lazy="condition.timeRange"></a-range-picker>
            </a-form-item>
            <a-form-item label="人员">
                <a-input v-model.lazy="condition.author" placeholder="人员" allow-clear></a-input>
            </a-form-item>
        </a-grid>
        <div class="button-group">
            <a-space wrap>
                <template #split>
                    <a-divider direction="vertical"/>
                </template>
                <a-button type="primary" @click="commitCondition()">提交查询条件</a-button>
                <a-button type="primary" status="warning" @click="clearCondition()">清空查询条件</a-button>
            </a-space>
        </div>
    </a-form>
    <a-divider :size="2" style="border-bottom-style: dotted" orientation="left">日志数据</a-divider>
    <a-table :columns="columns" :data="data" :bordered="{ cell: true }" :pagination="paginationProps"
             @page-size-change="pageSizeChange" @page-change="pageChange">
        <template #ringNumber="{ record }">
            <a-tooltip content="点击查看鸽子详情" :mini="true" background-color="#F7BAEF" @click="handleCheckPigeon(record.pid)">
                <div :style="{color: '#D91AD9', cursor: 'pointer'}">{{ record.ringNumber }}</div>
            </a-tooltip>
        </template>
    </a-table>
</template>

<style scoped lang="scss">
.button-group {
  margin: 0 10px;
}
</style>