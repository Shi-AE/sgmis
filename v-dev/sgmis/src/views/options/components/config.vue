<script>
import { h } from 'vue';
import { IconSearch } from '@arco-design/web-vue/es/icon'
import axiosx from "@/assets/js/axiosx.js"
import { Modal, Notification } from '@arco-design/web-vue'

export default {
    props: {
        api: {
            type: String,
            required: true
        },
        head: {
            type: String,
            required: true
        }
    },
    data() {
        return {
            columns: [
                {
                    title: "序号",
                    dataIndex: "index"
                },
                {
                    title: this.$props.head,
                    dataIndex: "name",
                    align: "left",
                    filterable: {
                        filter: (value, record) => record.name.includes(value),
                        slotName: 'head-filter',
                        icon: () => h(IconSearch)
                    }
                },
                {
                    title: "来源",
                    dataIndex: "author",
                    align: "center"
                },
                {
                    title: "操作",
                    slotName: "operation",
                    align: "right"
                }
            ],
            data: [],
            rowSelection: {
                type: 'checkbox',
                showCheckedAll: true
            },
            selectedKeys: [],
            tableBorder: {
                headerCell: true
            },
            inputModal: false,
            batchDeleteModal: false,
            updateModal: false,
            form: {
                name: "",
                id: null,
                gid: null
            }
        }
    },
    methods: {
        //重置表单
        resetForm() {
            this.form = {
                name: "",
                id: null,
                gid: null
            }
        },
        //添加
        handleInputModal() {
            this.inputModal = true
        },
        //批量删除
        handleBatchDeleteModal() {
            this.batchDeleteModal = true
        },
        //编辑
        handlUpdateModal(record) {
            if (record.gid === 0) {
                Notification.error("无法修改系统选项")
                return
            }
            this.form.name = record.name
            this.form.id = record.id
            this.form.gid = record.gid
            this.updateModal = true
        },
        //处理关闭窗口
        handleCancel() {
            //判断请求是否发送
            if (this.$store.state.isPending) {
                Notification.error("请求已发送，取消失败")
            } else {
                Notification.info("已取消")
            }
            this.resetForm()
        },
        //添加处理
        async handleInput() {
            let name = this.form.name
            if (name === "") {
                Notification.error("提交信息为空")
                return true
            }
            if (this.data.some(item => item.name === name)) {
                Notification.error("提交重复信息")
                return true
            }
            await axiosx({
                method: "POST",
                url: `xxpz/${this.$props.api}`,
                data: {
                    name: this.form.name
                },
                message: "正在处理添加请求"
            }).then(res => {
                if (res.data.code === 200) {
                    Notification.success(res.data.message)
                    //添加写入的配置选项，原因、结果同上
                    this.data.unshift({
                        ...res.data.data,
                        key: res.data.data.id,
                        index: this.data.length + 1,
                        disabled: res.data.data.gid === 0
                    })
                } else {
                    Notification.error(res.data.message)
                }
                this.resetForm()
                return true
            }).catch(() => {
                this.inputModal = false
            })
        },
        //处理单个删除
        handleDelete(record) {
            if (record.gid === 0) {
                Notification.error("无法删除系统选项")
                return
            }
            //询问模态框
            const modal = Modal.warning({
                title: "删除配置选项",
                content: `确认要删除"${record.name}"吗？`,
                closable: true,
                hideCancel: false,
                onCancel: () => {
                    //判断请求是否发送
                    if (this.$store.state.isPending) {
                        Notification.error("请求已发送，取消失败")
                    } else {
                        Notification.info("已取消")
                    }
                },
                onBeforeOk: () => {
                    axiosx({
                        method: "DELETE",
                        url: `xxpz/${record.id}`,
                        message: "正在处理删除请求"
                    }).then(res => {
                        if (res.data.code === 200) {
                            Notification.success(res.data.message)
                            //删除选中的配置选项，
                            //不重复提交请求减少服务器压力，
                            //但可能出现数据版本不对称
                            this.data = this.data.filter((item) => item.id !== record.id)
                        } else {
                            Notification.error(res.data.message)
                        }
                        modal.close()
                    }).catch(() => {
                        modal.close()
                    })
                }
            })
        },
        //批量删除处理
        async handleBatchDelete() {
            if (!this.selectedKeys.length) {
                Notification.error("未选中信息")
                return true
            }
            let ids = Object.values(this.selectedKeys)
            await axiosx({
                method: "POST",
                url: `xxpz/delete`,
                data: ids,
                message: "正在批量删除"
            }).then(res => {
                if (res.data.code === 200) {
                    Notification.success(res.data.message)
                    //添加写入的配置选项，原因、结果同上
                    this.data = this.data.filter(item => !ids.includes(item.id))
                } else {
                    Notification.error(res.data.message)
                }
                return true
            }).catch(() => {
                this.inputModal = false
            })
        },
        async handlUpdate() {
            await axiosx({
                method: "PUT",
                url: "xxpz",
                data: this.form,
                message: "正在更新数据"
            }).then(res => {
                if (res.data.code === 200) {
                    Notification.success(res.data.message)
                    //添加写入的配置选项，原因、结果同上
                    this.data = this.data.map(item => {
                        if (item.id === this.form.id) {
                            item.name = this.form.name
                        }
                        return item
                    })
                    this.resetForm()
                    return true
                } else {
                    Notification.error(res.data.message)
                }
            }).catch(() => {
                this.updateModal = false
            })
        }
    },
    created() {
        axiosx({
            method: "GET",
            url: `xxpz/${this.$props.api}`,
            message: "查询配置信息"
        }).then(res => {
            if (res.data.code === 200) {
                this.data = res.data.data.map((item, index) => {
                    return {
                        ...item,
                        key: item.id,
                        index: index + 1,
                        disabled: item.gid === 0
                    }
                })
            }
        })
    }
}
</script>
<template>
    <div>
        <div class="button-group">
            <a-space>
                <a-button type="primary" status="success" @click="handleInputModal()">添加</a-button>
                <a-button type="primary" status="danger" @click="handleBatchDeleteModal()">批量删除</a-button>
            </a-space>
        </div>
        <a-table :columns="columns" :data="data" :scroll="{ minWidth: 540 }" :pagination="false"
            :row-selection="rowSelection" v-model:selectedKeys="selectedKeys" :bordered="tableBorder">
            <!-- 筛选器 -->
            <template #head-filter="{ filterValue, setFilterValue, handleFilterConfirm, handleFilterReset }">
                <div class="custom-filter">
                    <a-space direction="vertical">
                        <a-input :model-value="filterValue[0]" @input="(value) => setFilterValue([value])" />
                        <div class="custom-filter-footer">
                            <a-button @click="handleFilterConfirm">查找</a-button>
                            <a-button @click="handleFilterReset">重置</a-button>
                        </div>
                    </a-space>
                </div>
            </template>
            <!-- 控制按钮 -->
            <template #operation="{ record }">
                <a-space>
                    <a-button type="primary" @click="handlUpdateModal(record)">编辑</a-button>
                    <a-button type="primary" status="danger" @click="handleDelete(record)">删除</a-button>
                </a-space>
            </template>
        </a-table>
        <!-- 添加提交信息框 -->
        <a-modal v-model:visible="inputModal" width="calc(300px + 0.1 * 100vw)" title="添加配置选项" @before-ok="handleInput"
            @cancel="handleCancel">
            <a-form :model="form">
                <a-form-item field="name" label="Name">
                    <a-input v-model.lazy="form.name" />
                </a-form-item>
            </a-form>
        </a-modal>
        <!-- 修改提交信息框 -->
        <a-modal v-model:visible="updateModal" width="calc(300px + 0.1 * 100vw)" title="编辑配置选项" @before-ok="handlUpdate"
            @cancel="handleCancel">
            <a-form :model="form">
                <a-form-item field="name" label="Name">
                    <a-input v-model.lazy="form.name" />
                </a-form-item>
            </a-form>
        </a-modal>
        <!-- 批量删除提示框 -->
        <a-modal v-model:visible="batchDeleteModal" width="calc(300px + 0.1 * 100vw)" title="批量删除配置选项"
            @before-ok="handleBatchDelete" @cancel="handleCancel" simple>
            确定要删除选中的配置选项吗？
        </a-modal>
    </div>
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

.button-group {
    margin: 10px;
}
</style>