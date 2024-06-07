<script>
import axiosx from "@/assets/js/axiosx.js"
import {Modal, Notification} from '@arco-design/web-vue'
import {verifyData} from '@/assets/js/loading.js'
import {h} from "vue"

export default {
    data() {
        return {
            columns: [
                {
                    title: "序号",
                    dataIndex: "key"
                },
                {
                    title: "鸽棚",
                    dataIndex: "name",
                    align: "right"
                },
                {
                    title: "巢箱数量",
                    dataIndex: "cxNumber",
                    align: "right"
                },
                {
                    title: "鸽子数量",
                    dataIndex: "pigeonPopulation",
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
                id: null,
                name: null,
                cxNumber: null,
                gid: null
            },
            //添加模态框控制变量
            inputModal: false,
            updateModal: false
        }
    },
    methods: {
        resetForm() {
            this.form = {
                id: null,
                name: null,
                cxNumber: null,
                gid: null
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
        //验证数据
        checkData() {
            const res = !verifyData(() => {
                const {name, cxNumber} = this.form
                if (!name || name.length === 0) {
                    Notification.error("请填写完整鸽棚名称")
                    return false
                }
                //默认为0
                if (!cxNumber) {
                    this.form.cxNumber = 0
                } else {
                    if (cxNumber < 0 || cxNumber > 1000000000) {
                        Notification.error("数字范围错误")
                        return false
                    }
                }
                return true
            })
            //重置表单
            if (res) {
                this.resetForm()
            }
            return res
        },
        //模态框处理
        //修改
        async handleUpdate() {
            //验证数据
            if (this.checkData()) {
                return true
            }
            await axiosx({
                method: "PUT",
                url: "gpcx",
                data: this.form,
                message: "正在修改数据",
            }).then(res => {
                if (res.data.code === 200) {
                    Notification.success(res.data.message)
                    this.data = this.data.map(item => {
                        if (item.id === this.form.id) {
                            item.name = this.form.name
                            item.cxNumber = this.form.cxNumber
                        }
                        return item
                    })
                } else {
                    Notification.error(res.data.message)
                }
                this.resetForm()
                return true
            }).catch(() => {
                this.updateModal = false
            })
        },
        //删除
        async DeleteUserClick(record) {
            //查询影响到的鸽子数量
            let pigeon = 0
            let success = false
            await axiosx({
                method: "GET",
                url: `gpcx/pigeon/${record.id}`,
                message: "正在查询影响到的鸽子数量"
            }).then((res) => {
                if (res.data.code === 200) {
                    pigeon = res.data.data
                    success = true
                } else {
                    Notification.error(`${res.dat.message}，暂时无法删除，请重试`)
                }
            })
            if (!success) {
                return
            }
            //函数式模态框
            const modal = Modal.warning({
                title: "删除鸽棚巢箱",
                content: () => [
                    h("div", `确认要删除"${record.name}"吗？`),
                    h("div", `这将会影响到${pigeon}只鸽子`),
                    h("div", `${pigeon}只鸽子将会被移出鸽棚巢箱`)
                ],
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
                    //执行删除
                    axiosx({
                        method: "DELETE",
                        url: `gpcx/${record.id}`,
                        message: "正在删除鸽棚巢箱"
                    }).then(res => {
                        if (res.data.code === 200) {
                            Notification.success(`${res.data.message}，鸽棚巢箱中移除了${res.data.data}只鸽子`)
                            this.data = this.data.filter((item) => item.id !== record.id)
                        } else {
                            Notification.error(res.data.message)
                        }
                    })
                    modal.close()
                }
            })
        },
        //添加
        async handleInput() {
            //验证数据
            if (this.checkData()) {
                return true
            }
            await axiosx({
                method: "POST",
                url: "gpcx",
                data: this.form,
                message: "正在上传信息"
            }).then(res => {
                if (res.data.code === 200) {
                    Notification.success(res.data.message)
                    this.data.unshift({
                        key: this.data.length + 1,
                        ...res.data.data
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
        //触发模态框处理
        //修改
        UpdateClick(record) {
            const {key, pigeonPopulation, ...info} = record
            this.form = info
            this.updateModal = true;
        },
        checkPigeon(record) {
            this.$router.push({name: "gzk", params: {name: record.name}})
        }
    },
    mounted() {
        axiosx({
            method: "GET",
            url: "gpcx",
            message: "正在获取鸽棚巢箱信息"
        }).then(res => {
            if (res.data.code === 200) {
                this.data = res.data.data.map((item, index) => {
                    return {
                        key: index + 1,
                        ...item
                    }
                })
            }
        })
    }
}
</script>
<template>
    <a-divider :size="2" style="border-bottom-style: dotted" orientation="left">操作</a-divider>
    <div class="button-group">
        <a-space>
            <a-button type="primary" status="success" @click="inputModal = true">新建巢箱</a-button>
        </a-space>
    </div>
    <a-divider :size="2" style="border-bottom-style: dotted" orientation="left">巢箱信息</a-divider>
    <a-table :columns="columns" :data="data" :pagination="false">
        <template #empty>
            <a-empty>
                还没有数据哦
            </a-empty>
        </template>
        <!-- 每条记录控制按钮 -->
        <template #operation="{ record }">
            <a-space>
                <a-button type="outline" @click="UpdateClick(record)">修改信息</a-button>
                <a-button type="outline" status="danger" @click="DeleteUserClick(record)">删除巢箱</a-button>
                <a-button type="outline" status="success" @click="checkPigeon(record)">查看鸽子</a-button>
            </a-space>
        </template>
    </a-table>
  <!-- 添加提交信息框 -->
    <a-modal v-model:visible="inputModal" width="calc(300px + 0.1 * 100vw)" title="新建鸽棚巢箱"
             @before-ok="handleInput"
             @cancel="handleCancel">
        <a-form :model="form">
            <a-form-item field="name" label="鸽棚名称">
                <a-input v-model.lazy="form.name" placeholder="鸽棚名称"/>
            </a-form-item>
            <a-form-item field="cxNumber" label="巢箱数量">
                <a-input-number v-model.lazy="form.cxNumber" :min="0" :max="1000000000" placeholder="巢箱数量"/>
            </a-form-item>
        </a-form>
        <div class="tip">
            <div>提示：巢箱数量填写0</div>
            <div>视为大棚无巢箱</div>
        </div>
    </a-modal>
  <!-- 修改信息模态框 -->
    <a-modal v-model:visible="updateModal" width="calc(300px + 0.1 * 100vw)" title="修改鸽棚巢箱"
             @before-ok="handleUpdate"
             @cancel="handleCancel">
        <a-form :model="form">
            <a-form-item field="name" label="鸽棚名称">
                <a-input v-model.lazy="form.name" placeholder="鸽棚名称"/>
            </a-form-item>
            <a-form-item field="cxNumber" label="巢箱数量">
                <a-input-number v-model.lazy="form.cxNumber" :min="0" :max="1000000000" placeholder="巢箱数量"/>
            </a-form-item>
        </a-form>
        <div class="tip">
            <div>提示：巢箱数量填写0</div>
            <div>视为大棚无巢箱</div>
            <div>此修改只影响鸽棚名称和巢箱数量</div>
            <div>不影响鸽棚内的鸽子</div>
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
