<script>
export default {
    data() {
        return {
            columns: [
                {
                    title: "序号",
                    dataIndex: "key",
                    align: "center",
                    width: 40
                },
                {
                    title: "操作",
                    slotName: "operation",
                    align: "center",
                    width: 230
                },
                {
                    title: "更新日期",
                    dataIndex: "updateData",
                    align: "center",
                    ellipsis: true,
                    tooltip: true,
                    width: 120
                },
                {
                    title: "鸽棚/巢箱",
                    dataIndex: "gpcx",
                    align: "center",
                    ellipsis: true,
                    tooltip: true,
                    width: 100
                },
                {
                    title: "图片",
                    slotName: "picture",
                    align: "center",
                    width: 50
                },
                {
                    title: "足环",
                    slotName: "ringNumber",
                    align: "center",
                    width: 200
                },
                {
                    title: "性别",
                    dataIndex: "sex",
                    align: "center",
                    width: 40
                },
                {
                    title: "父鸽足环",
                    slotName: "fatherRingNumber",
                    align: "center",
                    width: 200,
                    titleSlotName: "father"
                },
                {
                    title: "母鸽足环",
                    slotName: "motherRingNumber",
                    align: "center",
                    width: 200,
                    titleSlotName: "father"
                },
                {
                    title: "眼砂",
                    dataIndex: "yan",
                    align: "center",
                    ellipsis: true,
                    tooltip: true,
                    width: 80
                },
                {
                    title: "羽色",
                    dataIndex: "ys",
                    align: "center",
                    ellipsis: true,
                    tooltip: true,
                    width: 80
                },
                {
                    title: "类型",
                    dataIndex: "lx",
                    align: "center",
                    ellipsis: true,
                    tooltip: true,
                    width: 80
                },
                {
                    title: "状态",
                    dataIndex: "state",
                    align: "center",
                    ellipsis: true,
                    tooltip: true,
                    width: 80
                },
                {
                    title: "级别",
                    dataIndex: "jb",
                    align: "center",
                    ellipsis: true,
                    tooltip: true,
                    width: 80
                },
                {
                    title: "赛绩鸽",
                    dataIndex: "isGrade",
                    align: "center",
                    ellipsis: true,
                    tooltip: true,
                    width: 80
                },
                {
                    title: "备注",
                    dataIndex: "remark",
                    align: "center",
                    ellipsis: true,
                    tooltip: true,
                    width: 100
                }
            ],
            data: [
                {
                    key: "1",
                    updateData: "2021-07-03",
                    gpcx: "a",
                    pictureUrl: "abc.png",
                    ringNumber: "ringNumber",
                    sex: "雌",
                    fatherRingNumber: "fatherRingNumber",
                    motherRingNumber: "motherRingNumber",
                    yan: "黄",
                    ys: "灰",
                    lx: "大",
                    state: "在棚",
                    jb: "A",
                    isGrade: "否",
                    remark: "test"
                }
            ],
        }
    },
    methods: {
        toEditPigeon() {
            this.$router.push({ name: "editPigeon" })
        }
    }
}
</script>

<template>
    <a-divider :size="2" style="border-bottom-style: dotted" orientation="left">批量操作</a-divider>
    <div class="button-group">
        <a-space wrap>
            <template #split>
                <a-divider direction="vertical" />
            </template>
            <a-button type="primary" status="normal">设置用途</a-button>
            <a-button type="primary" status="normal">设置类型</a-button>
            <a-button type="primary" status="normal">设置状态</a-button>
            <a-button type="primary" status="normal">设置级别</a-button>
            <a-button type="primary" status="normal">医疗护理</a-button>
            <a-button type="primary" status="warning">填写日志</a-button>
            <a-button type="primary" status="warning">共享血统</a-button>
            <a-button type="primary" status="success">转移鸽棚巢箱</a-button>
            <a-button type="primary" status="danger">批量删除</a-button>
        </a-space>
    </div>
    <a-divider :size="2" style="border-bottom-style: dotted" orientation="left">新增操作</a-divider>
    <div class="button-group">
        <a-space wrap>
            <template #split>
                <a-divider direction="vertical" />
            </template>
            <a-button type="primary" status="success" @click="toEditPigeon()">新增鸽子</a-button>
            <a-button type="primary" status="success">鸽子快速入库</a-button>
            <a-button type="primary" status="success">文件批量导入</a-button>
        </a-space>
    </div>
    <a-divider :size="2" style="border-bottom-style: dotted" orientation="left">其它操作</a-divider>
    <div class="button-group">
        <a-space wrap>
            <template #split>
                <a-divider direction="vertical" />
            </template>
            <a-button type="primary" status="warning">高级批量操作</a-button>
            <a-button type="primary" status="warning">查看操作记录</a-button>
            <a-button type="primary" status="danger">打印血统</a-button>
            <a-button type="primary" status="danger">导出血统书展示链接</a-button>
        </a-space>
    </div>
    <a-divider :size="2" style="border-bottom-style: dotted" orientation="left">鸽子信息</a-divider>
    <a-table :columns="columns" :data="data" :sticky-header="true" :column-resizable="true" :bordered="{ cell: true }"
        :row-selection="{
            type: 'checkbox',
            showCheckedAll: true,
            onlyCurrent: true
        }">
        <!-- 操作插槽 -->
        <template #operation="{ record }">
            <a-space wrap>
                <a-button type="outline" status="success">查看</a-button>
                <a-button type="outline" status="warning">编辑</a-button>
                <a-button type="outline" status="danger">删除</a-button>
            </a-space>
        </template>
        <!-- 图片插槽 -->
        <template #picture="{ record }">
            <div>{{ record.pictureUrl }}</div>
        </template>
        <!-- 足环插槽 -->
        <template #ringNumber="{ record }">
            {{ record.ringNumber }}
        </template>
        <!-- 父足环插槽 -->
        <template #fatherRingNumber="{ record }">
            {{ record.fatherRingNumber }}
        </template>
        <!-- 母足环插槽 -->
        <template #motherRingNumber="{ record }">
            {{ record.motherRingNumber }}
        </template>
    </a-table>
</template>

<style scoped>
.button-group {
    margin: 0 10px;
}
</style>