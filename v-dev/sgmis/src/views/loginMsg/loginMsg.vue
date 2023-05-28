<script>
import axiosx from "../../assets/js/axiosx.js";
import {Notification} from '@arco-design/web-vue'
import {h} from "vue";
import {IconRefresh, IconSearch} from "@arco-design/web-vue/es/icon/index.js";

export default {
    components: {IconSearch, IconRefresh},
    data() {
        return {
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
                    title: "用户",
                    dataIndex: "account",
                    align: "center",
                    filterable: {
                        filter: (value, record) => record.account?.includes(value),
                        slotName: 'account-filter',
                        icon: () => h(IconSearch)
                    }
                },
                {
                    title: "登录IP",
                    dataIndex: "ip",
                    align: "center",
                    filterable: {
                        filter: (value, record) => record.ip?.includes(value),
                        slotName: 'ip-filter',
                        icon: () => h(IconSearch)
                    }
                },
                {
                    title: "登录时间",
                    dataIndex: "time",
                    align: "center",
                    sortable: {
                        sortDirections: ['descend']
                    }
                },
                {
                    title: "登录浏览器",
                    dataIndex: "browser",
                    align: "center",
                    filterable: {
                        filter: (value, record) => record.browser?.includes(value),
                        slotName: 'browser-filter',
                        icon: () => h(IconSearch)
                    }
                },
                {
                    title: "登录系统",
                    dataIndex: "os",
                    align: "center",
                    filterable: {
                        filter: (value, record) => record.os?.includes(value),
                        slotName: 'os-filter',
                        icon: () => h(IconSearch)
                    }
                },
                {
                    title: "登录设备",
                    dataIndex: "device",
                    align: "center",
                    filterable: {
                        filter: (value, record) => record.device?.includes(value),
                        slotName: 'device-filter',
                        icon: () => h(IconSearch)
                    }
                },
            ],
            data: []
        }
    },
    methods: {
        //清空所有的条件
        resetTable() {
            this.$refs.table.clearSorters()
            this.$refs.table.clearFilters()
            Notification.success("已清空筛选")
        },
    },
    mounted() {
        //获取登录数据
        axiosx({
            method: "GET",
            url: "login/message",
            message: "正在获取登录数据"
        }).then(res => {
            if (res.data.code === 200) {
                this.data = res.data.data.map((item, index) => {
                    return {
                        ...item,
                        key: index + 1
                    }
                });
            } else {
                Notification.error(res.data.message)
            }
        })
    }
}
</script>

<template>
    <a-divider :size="2" style="border-bottom-style: dotted" orientation="left">筛选操作</a-divider>
    <a-button :style="{margin: '0 20px'}" type="primary" status="success" @click="resetTable()">
        <IconRefresh :style="{color: '#ffffff'}"/>
        清空筛选
    </a-button>
    <a-divider :size="2" style="border-bottom-style: dotted" orientation="left">登录信息</a-divider>
    <a-table :columns="columns" :data="data" :bordered="{ cell: true }">
        <!-- jb搜索 -->
        <template #account-filter="{ filterValue, setFilterValue, handleFilterConfirm, handleFilterReset}">
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
        <!-- jb搜索 -->
        <template #ip-filter="{ filterValue, setFilterValue, handleFilterConfirm, handleFilterReset}">
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
        <!-- jb搜索 -->
        <template #browser-filter="{ filterValue, setFilterValue, handleFilterConfirm, handleFilterReset}">
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
        <!-- jb搜索 -->
        <template #os-filter="{ filterValue, setFilterValue, handleFilterConfirm, handleFilterReset}">
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
        <!-- jb搜索 -->
        <template #device-filter="{ filterValue, setFilterValue, handleFilterConfirm, handleFilterReset}">
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
    </a-table>
</template>

<style scoped>
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
