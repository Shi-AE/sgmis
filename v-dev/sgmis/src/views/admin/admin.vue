<script>
import axiosx from "@/assets/js/axiosx.js"
import { Modal, Notification } from '@arco-design/web-vue'
import { h } from "vue"

export default {
    data() {
        return {
            columns: [
                {
                    title: "序号",
                    dataIndex: "key"
                },
                {
                    title: "成员用户名",
                    dataIndex: "account",
                    align: "center"
                },
                {
                    title: "管理员权限",
                    dataIndex: "admin",
                    align: "right"
                },
                {
                    title: "操作",
                    slotName: "operation",
                    align: "right"
                }
            ],
            data: [],
            form: {
                gid: null,
                id: null,
                account: ""
            },
            inputModal: false
        }
    },
    methods: {
        resetForm() {
            this.form = {
                gid: null,
                id: null,
                account: ""
            }
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
        //重置密码
        resetPasswordClick(record) {
            if (record.isAdmin) {
                Notification.error("无法修改管理员信息")
                return
            }
            const modal = Modal.warning({
                title: "重置成员密码",
                content: () => [
                    h("div", `确认要重置成员${record.account}的密码吗？`),
                    h("div", `重置后密码初始值为“123456”`),
                    h("div", `成功后请尽快更换密码！`),
                ],
                closable: true,
                hideCancel: false,
                onCancel: () => {
                    if (this.$store.state.isPending) {
                        Notification.error("请求已发送，取消失败")
                    } else {
                        Notification.info("已取消")
                    }
                },
                onBeforeOk: () => {
                    axiosx({
                        method: "PUT",
                        url: "user",
                        data: {
                            id: record.id,
                            gid: record.gid
                        },
                        message: "正在重置用户密码"
                    }).then(res => {
                        if (res.data.code === 200) {
                            Notification.success(res.data.message)
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
        //删除成员
        DeleteUserClick(record) {
            if (record.isAdmin) {
                Notification.error("无法修改管理员信息")
                return
            }
            const modal = Modal.warning({
                title: "删除成员",
                content: `确认要删除成员${record.account}吗？`,
                closable: true,
                hideCancel: false,
                onCancel: () => {
                    if (this.$store.state.isPending) {
                        Notification.error("请求已发送，取消失败")
                    } else {
                        Notification.info("已取消")
                    }
                },
                onBeforeOk: () => {
                    axiosx({
                        method: "DELETE",
                        url: `user/${record.id}`,
                        message: `正在删除${record.account}`
                    }).then(res => {
                        if (res.data.code === 200) {
                            Notification.success(res.data.message)
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
        //添加新成员
        async handleInput() {
            await axiosx({
                method: "POST",
                url: "user",
                data: this.form
            }).then(res => {
                if (res.data.code === 200) {
                    Notification.success(res.data.message)
                    const item = res.data.data
                    this.data.unshift({
                        key: this.data.length + 1,
                        account: item.account,
                        id: item.id,
                        gid: item.gid,
                        admin: item.admin ? "管理员" : "成员",
                        isAdmin: item.admin
                    })
                } else {
                    Notification.error(res.data.message)
                }
                return true;
            }).catch(() => {
                return true;
            })
        }
    },
    mounted() {
        //查询原有成员
        axiosx({
            method: "GET",
            url: "user",
            message: "正在查询成员"
        }).then(res => {
            if (res.data.code === 200) {
                this.data = res.data.data.map((item, index) => {
                    return {
                        account: item.account,
                        key: index + 1,
                        id: item.id,
                        gid: item.gid,
                        admin: item.admin ? "管理员" : "成员",
                        isAdmin: item.admin
                    }
                })
            } else {
                Notification.error("成员加载失败")
            }
        })
    }
}
</script>
<template>
    <a-divider :size="2" style="border-bottom-style: dotted" orientation="left">操作</a-divider>
    <div class="button-group">
        <a-space>
            <a-button type="primary" status="success" @click="inputModal = true">新建成员</a-button>
        </a-space>
    </div>
    <a-divider :size="2" style="border-bottom-style: dotted" orientation="left">成员</a-divider>
    <a-table :columns="columns" :data="data" :pagination="false" :scroll="{ minWidth: 540 }">
        <template #empty>
            <a-empty>
                数据发生异常，请重试
            </a-empty>
        </template>
        <!-- 每条记录控制按钮 -->
        <template #operation="{ record }">
            <a-space>
                <a-button type="primary" @click="resetPasswordClick(record)">重置密码</a-button>
                <a-button type="primary" status="danger" @click="DeleteUserClick(record)">删除成员</a-button>
            </a-space>
        </template>
    </a-table>
    <!-- 添加提交信息框 -->
    <a-modal v-model:visible="inputModal" width="calc(300px + 0.1 * 100vw)" title="新建成员账号" @before-ok="handleInput"
        @cancel="handleCancel">
        <a-form :model="form">
            <a-form-item field="account" label="成员用户名">
                <a-input v-model.lazy="form.account" />
            </a-form-item>
        </a-form>
        <div class="tip">
            <div>注：成员密码初始值为“123456”</div>
            <div>注册成功后请尽快更换密码</div>
            <div>成员无法设置为管理员</div>
        </div>
    </a-modal>
</template>
<style scoped>
.button-group {
    margin: 10px;
}

.tip {
    color: rgb(var(--gray-5));
    font-size: 14px;
    text-align: center;
}
</style>