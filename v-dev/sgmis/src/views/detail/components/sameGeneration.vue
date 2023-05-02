<script>
import {h} from "vue";
import axiosx from "../../../assets/js/axiosx.js";
import {IconSearch} from "@arco-design/web-vue/es/icon/index.js";

export default {
    data() {
        return {
            pigeonResourcePath: `${window.location.protocol}//${window.location.hostname}/pigeon`,
            columns: [
                {
                    title: "序号",
                    dataIndex: "key",
                    align: "center",
                    sortable: {
                        sortDirections: ['descend']
                    }
                },
                {
                    title: "足环",
                    slotName: "ringNumber",
                    align: "center",
                    filterable: {
                        filter: (value, record) =>
                            record.ringNumber?.includes(value)
                            || record.name?.includes(value)
                            || record.bloodline?.includes(value),
                        slotName: 'ringNumber-filter',
                        icon: () => h(IconSearch)
                    }
                },
                {
                    title: "名称",
                    dataIndex: "name",
                    align: "center"
                },
                {
                    title: "图片",
                    slotName: "picture",
                    align: "center",
                    width: 70
                },
                {
                    title: "平辈关系",
                    dataIndex: "relation",
                    align: "center"
                },
                {
                    title: "性别",
                    dataIndex: "sex",
                    align: "center"
                },
                {
                    title: "羽色",
                    dataIndex: "ys",
                    align: "center"
                },
                {
                    title: "眼砂",
                    dataIndex: "yan",
                    align: "center"
                },
                {
                    title: "赛绩鸽",
                    dataIndex: "isGrade",
                    align: "center"
                },
                {
                    title: "类型",
                    dataIndex: "lx",
                    align: "center"
                },
                {
                    title: "状态",
                    dataIndex: "state",
                    align: "center"
                }
            ],
            data: null
        }
    },
    mounted() {
        //查子代树
        axiosx({
            method: "GET",
            url: `detail/peer/${this.$route.params.id}`,
            message: "正在查询子代"
        }).then(res => {
            if (res.data.code === 200) {
                //装填
                const map = res.data.data
                const list = []
                //同父异母
                if (map.fatherHalf?.length > 0) {
                    map.fatherHalf.forEach(item => {
                        list.push({
                            ...item,
                            key: list.length + 1,
                            relation: "同父异母"
                        })
                    })
                }
                //同母异父
                if (map.motherHalf?.length > 0) {
                    map.motherHalf.forEach(item => {
                        list.push({
                            ...item,
                            key: list.length + 1,
                            relation: "同母异父"
                        })
                    })
                }
                //同父同母
                if (map.full?.length > 0) {
                    map.full.forEach(item => {
                        list.push({
                            ...item,
                            key: list.length + 1,
                            relation: "同父同母"
                        })
                    })
                }
                this.data = list
            }
        })
    }
}
</script>

<template>
    <a-table :columns="columns" :data="data" :bordered="{ cell: true }">
        <!-- 图片插槽 -->
        <template #picture="{ record }">
            <a-image :src="`${pigeonResourcePath}/${record.pictureUrl}`" width="100%" height="100%" fit="contain"
                     v-if="record.pictureUrl"/>
        </template>
        <!-- 足环插槽 -->
        <template #ringNumber="{ record }">
            <routerLink :to="{name: 'detail', params: {id: record.id}}" :style="{color: '#D91AD9'}">
                <a-tooltip content="点击查看鸽子详情" :mini="true" background-color="#FDC2DB">
                    <div>{{ record.ringNumber }}</div>
                </a-tooltip>
            </routerLink>
        </template>
        <!-- ringNumber搜索 -->
        <template #ringNumber-filter="{ filterValue, setFilterValue, handleFilterConfirm, handleFilterReset}">
            <div class="custom-filter">
                <a-space direction="vertical">
                    <a-input :model-value="filterValue[0]" @input="(value)=>setFilterValue([value])"/>
                    <div class="custom-filter-footer">
                        <a-button @click="handleFilterConfirm">搜索</a-button>
                        <a-button @click="handleFilterReset">重置</a-button>
                    </div>
                </a-space>
            </div>
        </template>
        <template #empty>
            <a-empty description="暂无平辈"/>
        </template>
    </a-table>
</template>

<style>
.custom-filter {
    padding: 20px;
    background: var(--color-bg-5);
    border: 1px solid var(--color-neutral-3);
    border-radius: var(--border-radius-medium);
    box-shadow: 0 2px 5px rgb(0 0 0 / 10%);
}

.custom-filter-footer {
    display: flex;
    justify-content: space-between;
}
</style>