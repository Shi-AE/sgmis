<script>
import axiosx from "@/assets/js/axiosx.js"
import {verifyData} from "@/assets/js/loading.js"
import {Modal, Notification} from '@arco-design/web-vue'
import {h} from "vue"
import {IconFilter, IconMan, IconRefresh, IconSearch, IconWoman} from "@arco-design/web-vue/es/icon";

export default {
    mounted() {
        this.updateAllData()
    },
    components: {
        IconRefresh, IconWoman, IconMan
    },
    data() {
        return {
            pigeonResourcePath: `${window.location.protocol}//${window.location.hostname}/pigeon`,
            columns: [
                {
                    title: "序号",
                    dataIndex: "key",
                    align: "center",
                    width: 90,
                    sortable: {
                        sortDirections: ['descend']
                    }
                },
                {
                    title: "操作",
                    slotName: "front-operation",
                    align: "center",
                    width: 165
                },
                {
                    title: "更新日期",
                    dataIndex: "updateData",
                    align: "center",
                    ellipsis: true,
                    tooltip: true,
                    width: 140,
                    sortable: {
                        sortDirections: ['ascend', 'descend']
                    },
                    filterable: {
                        filter: (value, record) => {
                            if (!record.updateData) {
                                return false
                            }
                            const date = new Date(`${record.updateData} 00:00:00`)
                            return value[0][0] <= date && date <= value[0][1]
                        },
                        slotName: 'updateData-filter',
                        icon: () => h(IconFilter)
                    }
                },
                {
                    title: "鸽棚/巢箱",
                    dataIndex: "gpcx",
                    align: "center",
                    ellipsis: true,
                    tooltip: true,
                    width: 120,
                    filterable: {
                        filter: (value, record) => record.gpcx?.includes(value),
                        slotName: 'gpcx-filter',
                        icon: () => h(IconSearch),
                        defaultFilteredValue: this.$route.params.name ? [this.$route.params.name] : null
                    }
                },
                {
                    title: "图片",
                    slotName: "picture",
                    align: "center",
                    width: 70
                },
                {
                    title: "足环",
                    slotName: "ringNumber",
                    align: "center",
                    width: 200,
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
                    title: "性别",
                    dataIndex: "sex",
                    align: "center",
                    width: 50
                },
                {
                    title: "父鸽足环",
                    slotName: "fatherRingNumber",
                    align: "center",
                    width: 200,
                    titleSlotName: "father",
                    filterable: {
                        filter: (value, record) =>
                            record.father?.ringNumber?.includes(value)
                            || record.father?.name?.includes(value)
                            || record.father?.bloodline?.includes(value),
                        slotName: 'fatherRingNumber-filter',
                        icon: () => h(IconSearch)
                    }
                },
                {
                    title: "母鸽足环",
                    slotName: "motherRingNumber",
                    align: "center",
                    width: 200,
                    titleSlotName: "mother",
                    filterable: {
                        filter: (value, record) =>
                            record.mather?.ringNumber?.includes(value)
                            || record.mather?.name?.includes(value)
                            || record.mather?.bloodline?.includes(value),
                        slotName: 'motherRingNumber-filter',
                        icon: () => h(IconSearch)
                    }
                },
                {
                    title: "眼砂",
                    dataIndex: "yan",
                    align: "center",
                    ellipsis: true,
                    tooltip: true,
                    width: 80,
                    filterable: {
                        filter: (value, record) => record.yan?.includes(value),
                        slotName: 'yan-filter',
                        icon: () => h(IconSearch)
                    }
                },
                {
                    title: "羽色",
                    dataIndex: "ys",
                    align: "center",
                    ellipsis: true,
                    tooltip: true,
                    width: 80,
                    filterable: {
                        filter: (value, record) => record.ys?.includes(value),
                        slotName: 'ys-filter',
                        icon: () => h(IconSearch)
                    }
                },
                {
                    title: "类型",
                    dataIndex: "lx",
                    align: "center",
                    ellipsis: true,
                    tooltip: true,
                    width: 80,
                    filterable: {
                        filter: (value, record) => record.lx?.includes(value),
                        slotName: 'lx-filter',
                        icon: () => h(IconSearch)
                    }
                },
                {
                    title: "状态",
                    dataIndex: "state",
                    align: "center",
                    ellipsis: true,
                    tooltip: true,
                    width: 80,
                    filterable: {
                        filter: (value, record) => record.state?.includes(value),
                        slotName: 'state-filter',
                        icon: () => h(IconSearch)
                    }
                },
                {
                    title: "级别",
                    dataIndex: "jb",
                    align: "center",
                    ellipsis: true,
                    tooltip: true,
                    width: 80,
                    filterable: {
                        filter: (value, record) => record.jb?.includes(value),
                        slotName: 'jb-filter',
                        icon: () => h(IconSearch)
                    }
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
                    width: 100,
                    filterable: {
                        filter: (value, record) => record.remark?.includes(value),
                        slotName: 'remark-filter',
                        icon: () => h(IconSearch)
                    }
                },
                {
                    title: "操作",
                    slotName: "end-operation",
                    align: "center",
                    width: 190
                },
            ],
            type: {
                yspz: {
                    toName: "羽色",
                    toField: "ys"
                },
                yanpz: {
                    toName: "眼砂",
                    toField: "yan"
                },
                lxpz: {
                    toName: "类型",
                    toField: "lx"
                },
                state: {
                    toName: "状态",
                    toField: "state"
                },
                jbpz: {
                    toName: "级别",
                    toField: "jb"
                },
                ylhl: {
                    toName: "医疗护理",
                    toField: "ylhl"
                },
            },
            //表格数据
            data: null,
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
            //模态框控制变量
            editModal: {
                visible: false,
                title: "查看",
                disabled: false
            },
            //系统选项配置存储变量
            options: {
                countryOptions: [],
                provinceOptions: [],
                yspzOptions: [],
                yanpzOptions: [],
                lxpzOptions: [],
                stateOptions: [],
                jbpzOptions: [],
                ylhlOptions: []
            },
            //整体数据模态框大型表单
            form: {
                id: null,
                country: null,
                year: null,
                province: null,
                code: null,
                bloodline: null,
                yan: null,
                ys: null,
                lx: null,
                state: null,
                jb: null,
                isGrade: null,
                remark: null,
                gid: null
            },
            //表格的多选变量
            selectedKeys: null,
            //批量编辑模态框控制变量
            batchEditModal: {
                visible: false,
                title: "",
                label: "",
                field: "",
                options: [],
                data: null
            },
            //批量分享模态框控制变量
            batchShareModal: {
                visible: false,
                options: [],
                gid: null
            },
            //批量加入鸽棚巢箱模态框控制变量
            batchGpcxModal: {
                visible: false,
                options: [],
                id: null,
            },
            //文件导入模态框控制变量
            fileModal: {
                visible: false
            }
        }
    },
    methods: {
        //更新所有
        updateAllData() {
            const parentMap = {}
            //获取鸽子信息
            axiosx({
                method: "GET",
                url: "pigeon",
                message: "正在获取鸽子信息"
            }).then(res => {
                if (res.data.code === 200) {
                    this.data = res.data.data.map((item, index) => {
                        parentMap[item.id] = {
                            ringNumber: item.ringNumber,
                            bloodline: item.bloodline,
                            name: item.name
                        }
                        return {
                            ...item,
                            key: index + 1,
                            pictureUrl: item.pictureUrl ?? null
                        }
                    })
                    this.data.forEach(item => {
                        item.father = parentMap[item.fid] ?? null
                        item.mather = parentMap[item.mid] ?? null
                    })
                    this.paginationProps.total = this.getTotal()
                }
            })
        },
        //根据页面更新
        updateCurrentPageData() {
            const parentMap = {}
            //获取当前页信息
            const page = {
                current: this.paginationProps.current,
                pageSize: this.paginationProps.pageSize,
            }
            //根据当前页信息获取当前页的更新
            axiosx({
                method: "PATCH",
                url: "pigeon",
                data: page,
                message: "正在获取更新后的数据"
            }).then(res => {
                if (res.data.code === 200) {
                    const start = (page.current - 1) * page.pageSize
                    const n = page.pageSize
                    const end = Math.min(start + n, this.data.length)
                    const data = res.data.data.map((item, index) => {
                        return {
                            ...item,
                            key: start + index + 1,
                            pictureUrl: item.pictureUrl ?? null
                        }
                    })
                    //局部更新数据
                    this.data.splice((page.current - 1) * page.pageSize, page.pageSize, ...data)
                    //全局遍历，获取父代信息
                    this.data.forEach(item => {
                        parentMap[item.id] = {
                            ringNumber: item.ringNumber,
                            bloodline: item.bloodline,
                            name: item.name
                        }
                    })
                    //局域遍历插入父代信息
                    for (let i = start; i < end; i++) {
                        this.data[i].father = parentMap[this.data[i].fid] ?? null
                        this.data[i].mather = parentMap[this.data[i].mid] ?? null
                    }
                } else {
                    Notification.error(res.data.message)
                }
            })
        },
        //更新单个数据
        updateSingleData(index) {
            //读取id
            const id = this.data[index].id
            //获取单条鸽子信息
            axiosx({
                method: "GET",
                url: `pigeon/base/${id}`,
                message: "正在获取更新信息"
            }).then(res => {
                if (res.data.code === 200) {
                    const data = res.data.data
                    const father = this.data.find(item => JSON.stringify(item.id) === JSON.stringify(data.fid))
                    const mather = this.data.find(item => JSON.stringify(item.id) === JSON.stringify(data.mid))
                    this.data[index] = {
                        ...data,
                        key: index + 1,
                        pictureUrl: data.pictureUrl ?? null,
                        father: {
                            ringNumber: father?.ringNumber,
                            bloodline: father?.bloodline,
                            name: father?.name
                        },
                        mather: {
                            ringNumber: mather?.ringNumber,
                            bloodline: mather?.bloodline,
                            name: mather?.name
                        }
                    }
                }
            })
        },
        toEditPigeon() {
            this.$router.push({name: "editPigeon"})
        },
        toRapid() {
            this.$router.push({name: "rapid"})
        },
        toBatch() {
            this.$router.push({name: "batch"})
        },
        toLog() {
            this.$router.push({name: "log"})
        },
        toEditPigeonById(record) {
            this.$router.push({name: "editPigeon", params: {id: record.id}})
        },
        openFileModal() {
            this.fileModal.visible = true
        },
        pageSizeChange(size) {
            if (size === "所有") {
                this.paginationProps.pageSize = this.getTotal()
                return
            }
            this.paginationProps.pageSize = size
        },
        pageChange(page) {
            this.paginationProps.current = page
        },
        getTotal() {
            return this.data ? this.data.length : 0
        },
        //清空所有的条件
        resetTable() {
            this.$refs.table.clearSorters()
            this.$refs.table.clearFilters()
            Notification.success("已清空筛选")
        },
        //处理关闭模态框
        handleCancel() {
            //判断请求是否发送
            if (this.$store.state.isPending) {
                Notification.error("请求已发送，取消失败")
            } else {
                Notification.info("已取消")
            }
            this.resetForm()
        },
        handleEditCancel() {
            if (!this.editModal.disabled) {
                //判断请求是否发送
                if (this.$store.state.isPending) {
                    Notification.error("请求已发送，取消失败")
                } else {
                    Notification.info("已取消")
                }
            }
            this.resetForm()
        },
        //处理打开模态框前加载
        handleBeforeOpen() {
            //判断是否读取过数据库
            let isSelected = true
            for (let key in this.options) {
                if (this.options[key].length === 0) {
                    isSelected = false
                    break
                }
            }
            if (!isSelected) {
                axiosx({
                    method: "GET",
                    url: `xxpz`
                }).then(res => {
                    if (res.data.code === 200) {
                        res.data.data.forEach(item => {
                            //判断是否存在改选项
                            if (this.options[`${item.type}Options`]) {
                                const splitName = item.name.split("/")
                                this.options[`${item.type}Options`].push({
                                    label: splitName[1] ? splitName[0] + splitName[1] : splitName[0],
                                    value: splitName[1] ? splitName[1] : splitName[0]
                                })
                            }
                        })
                    }
                })
            }
        },
        //处理编辑模态框提交
        async handleEditPigeon() {
            const form = this.form
            if (!verifyData(() => {
                //必填参数检查
                if (!form.country || !form.year || !form.province || !form.code) {
                    Notification.error("必填信息为空")
                    return false
                }
            }, "正在验证数据")) {
                return false
            }
            if (!this.editModal.disabled) {
                await axiosx({
                    method: "PUT",
                    url: "pigeon",
                    data: this.formToData(this.form),
                    message: "正在更新鸽子信息"
                }).then(res => {
                    if (res.data.code === 200) {
                        //确保数据正确性
                        //重新请求数据
                        //减少对数据库的检索
                        //采用分页检索，只更新当前一条信息
                        this.updateSingleData(this.form.key - 1)
                        Notification.success(res.data.message)
                    } else {
                        Notification.error(res.data.message)
                    }
                })
                this.resetForm()
                return true
            }
        },
        resetForm() {
            this.form = {
                id: null,
                country: null,
                year: null,
                province: null,
                code: null,
                bloodline: null,
                yan: null,
                ys: null,
                lx: null,
                state: null,
                jb: null,
                isGrade: null,
                remark: null,
                gid: null
            }
        },
        dataToForm(data) {
            const ring = data?.ringNumber?.split("-")
            return {
                country: ring ? ring[0] : null,
                year: ring ? ring[1] : null,
                province: ring ? ring[2] : null,
                code: ring ? ring[3] : null,
                ...data
            }
        },
        formToData(form) {
            return {
                ...form,
                ringNumber: [
                    form.country,
                    form.year,
                    form.province,
                    form.code
                ].join("-")
            }
        },
        handleCheckModalOpen(record) {
            this.form = this.dataToForm(record)
            this.editModal = {
                visible: true,
                title: "查看鸽子信息",
                disabled: true
            }
        },
        handleEditModalOpen(record) {
            this.form = this.dataToForm(record)
            this.editModal = {
                visible: true,
                title: "编辑鸽子信息",
                disabled: false
            }
        },
        //处理批量编辑提交
        async handleEditBatch() {
            if (this.batchEditModal.data === null) {
                Notification.warning("请选择数据");
                return false
            }
            //ylhl不做提交处理（doge）
            if (this.batchEditModal.field === "ylhl") {
                Notification.success("修改成功")
                return true
            }
            //装填ids
            const ids = this.selectedKeys.map(key => {
                return this.data[key - 1].id
            })
            //检查数据
            if (!verifyData(() => {
                if (!this.batchEditModal.data) {
                    Notification.error("属性设置值错误")
                    return false
                }
            })) {
                return false
            }
            await axiosx({
                method: "PUT",
                url: `pigeon/${this.batchEditModal.field}/${this.batchEditModal.data}`,
                data: ids
            }).then(res => {
                if (res.data.code === 200) {
                    //确保数据正确性
                    //重新请求数据
                    //减少对数据库的检索
                    //采用分页检索，只更新当前页面信息
                    this.updateCurrentPageData()
                    Notification.success(res.data.message)
                } else {
                    Notification.error(res.data.message)
                }
            })
            //清空多选框
            this.selectedKeys = []
            return true
        },
        //检测是否选择数据
        notSelectedKeys() {
            if (!this.selectedKeys?.length) {
                Notification.warning("还未选择数据");
                return true
            }
            return false
        },
        //处理模态框打开
        async handleBatchEditModalOpen(type) {
            if (this.notSelectedKeys()) {
                return
            }
            //获取配置
            if (this.options[`${type}Options`]?.length === 0) {
                await axiosx({
                    method: "GET",
                    url: `xxpz/${type}`,
                    message: "正在获取系统信息"
                }).then(res => {
                    if (res.data.code === 200) {
                        this.options[`${type}Options`] = res.data.data.map(item => {
                            return item.name
                        })
                    }
                })
            }
            //初始化模态框
            this.batchEditModal = {
                visible: true,
                title: `批量编辑${this.type[type].toName}`,
                label: this.type[type].toName,
                field: this.type[type].toField,
                options: this.options[`${type}Options`],
                data: null
            }
        },
        //处理单个删除和模态框关闭
        handleDeleteModalOpen(record) {
            const modal = Modal.warning({
                title: "删除鸽子",
                content: () => [
                    h("div", `确认要删除鸽子${record.ringNumber}吗？`),
                    h("div", "这可能会影响到子代关系")
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
                        method: "DELETE",
                        url: `pigeon/${record.id}/${record.sex}`,
                        message: "正在删除"
                    }).then(res => {
                        if (res.data.code === 200) {
                            //考虑到删除会印象到子代与父代的关系必
                            //须进行全局更新
                            this.updateAllData()
                            Notification.success(res.data.message)
                            modal.close()
                        } else {
                            Notification.error(res.data.message)
                            modal.close()
                        }
                    })
                }
            })
            //清空多选框
            this.selectedKeys = []
        },
        handleBatchLogModalOpen() {
        },
        //处理批量分享模态框开启
        handleBatchShareModalOpen() {
            if (this.notSelectedKeys()) {
                return
            }
            //获取管理员账号名称选项
            axiosx({
                method: "GET",
                url: "user/admin",
                message: "正在获取管理员信息"
            }).then(res => {
                if (res.data.code === 200) {
                    this.batchShareModal.visible = true
                    this.batchShareModal.gid = null
                    this.batchShareModal.options = res.data.data.map(item => {
                        return {
                            value: String(item.gid),
                            label: item.account
                        }
                    })
                } else {
                    Notification.error(res.data.message)
                }
            })
        },
        //处理批量分享逻辑
        async handleShareBatch() {
            if (this.batchShareModal.gid === null) {
                Notification.warning("请选择管理员账号名称")
                return false
            }
            //装填ids
            const ids = this.selectedKeys.map(key => {
                return this.data[key - 1].id
            })
            await axiosx({
                method: "POST",
                url: `pigeon/share/${this.batchShareModal.gid}`,
                data: ids,
                message: "正在分享血统"
            }).then(res => {
                if (res.data.code === 200) {
                    Notification.success(res.data.message)
                } else {
                    Notification.error(res.data.message + ",对方已拥有该血统")
                }
            })
            this.selectedKeys = []
            return true
        },
        //批量添加到鸽棚巢箱
        handleBatchGpcxModalOpen() {
            if (this.notSelectedKeys()) {
                return
            }
            axiosx({
                method: "GET",
                url: "gpcx/base",
                message: "正在获取鸽棚巢箱信息"
            }).then(res => {
                if (res.data.code === 200) {
                    const options = res.data.data.map(item => {
                        return {
                            value: String(item.id),
                            label: item.name
                        }
                    })
                    this.batchGpcxModal = {
                        visible: true,
                        options,
                        id: null
                    }
                }
            })
        },
        //处理批量添加到鸽棚巢箱逻辑
        async handleGpcxBatch() {
            if (this.batchGpcxModal.id === null) {
                Notification.warning("请选鸽棚巢箱名称")
                return false
            }
            //装填ids
            const ids = this.selectedKeys.map(key => {
                return this.data[key - 1].id
            })
            await axiosx({
                method: "POST",
                url: `gpcx/${this.batchGpcxModal.id}`,
                data: ids,
                message: "正在将鸽子添加到鸽棚巢箱"
            }).then(res => {
                if (res.data.code === 200) {
                    this.updateCurrentPageData()
                    Notification.success(res.data.message)
                } else {
                    Notification.error(res.data.message)
                }
            })
            this.selectedKeys = []
            return true
        },
        //处理批量删除，和模态框关闭
        handleBatchDeleteModalOpen() {
            if (this.notSelectedKeys()) {
                return
            }
            //装填鸽子信息
            const pigeons = this.selectedKeys.map(key => {
                const data = this.data[key - 1]
                return {
                    id: data?.id,
                    sex: data?.sex
                }
            })
            const modal = Modal.warning({
                title: "批量删除鸽子",
                content: () => [
                    h("div", `确认要批量删除选中的鸽子吗？`),
                    h("div", "这可能会影响到子代关系")
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
                        method: "DELETE",
                        headers: {
                            "X-HTTP-Method-Override": "POST"
                        },
                        url: `pigeon`,
                        data: pigeons,
                        message: "正在删除"
                    }).then(res => {
                        if (res.data.code === 200) {
                            //考虑到删除会印象到子代与父代的关系必
                            //须进行全局更新
                            this.updateAllData()
                            Notification.success(res.data.message)
                            this.selectedKeys = []
                            modal.close()
                        } else {
                            Notification.error(res.data.message)
                            modal.close()
                        }
                    })
                }
            })
        },
        handleFileUpload(option) {
            const {onProgress, onError, onSuccess, fileItem, name} = option
            const file = new FormData();
            file.append(name || 'file', fileItem.file);
            axiosx({
                method: "POST",
                url: "pigeon/file",
                data: file,
                message: "正在上传并解析文件",
                onUploadProgress: progressEvent => {
                    const percent = progressEvent.loaded / progressEvent.total
                    onProgress(percent, progressEvent)
                }
            }).then(res => {
                if (res.data.code === 200) {
                    onSuccess(res)
                    this.updateAllData()
                    Notification.success(res.data.message)
                    this.fileModal.visible = false
                } else {
                    onError(res)
                    Notification.error(res.data.message)
                }
            }).catch(res => {
                onError(res)
                Notification.error("服务器发生错误，上传超时")
            })
        }
    }
}
</script>

<template>
    <a-divider :size="2" style="border-bottom-style: dotted" orientation="left">批量操作</a-divider>
    <div class="button-group">
        <a-space wrap>
            <template #split>
                <a-divider direction="vertical"/>
            </template>
            <a-button type="primary" status="normal" @click="handleBatchEditModalOpen('yspz')">设置羽色</a-button>
            <a-button type="primary" status="normal" @click="handleBatchEditModalOpen('yanpz')">设置眼砂</a-button>
            <a-button type="primary" status="normal" @click="handleBatchEditModalOpen('lxpz')">设置类型</a-button>
            <a-button type="primary" status="normal" @click="handleBatchEditModalOpen('state')">设置状态</a-button>
            <a-button type="primary" status="normal" @click="handleBatchEditModalOpen('jbpz')">设置级别</a-button>
            <a-button type="primary" status="normal" @click="handleBatchEditModalOpen('ylhl')">医疗护理</a-button>
            <a-button type="primary" status="warning" @click="handleBatchLogModalOpen()">填写日志</a-button>
            <!--共享血统只有管理员可以操作-->
            <a-button :disabled="!$store.state.admin" type="primary" status="warning" @click="handleBatchShareModalOpen()">
                共享血统
            </a-button>
            <a-button type="primary" status="success" @click="handleBatchGpcxModalOpen()">加入鸽棚巢箱</a-button>
            <a-button type="primary" status="danger" @click="handleBatchDeleteModalOpen()">批量删除</a-button>
        </a-space>
    </div>
    <a-divider :size="2" style="border-bottom-style: dotted" orientation="left">新增操作</a-divider>
    <div class="button-group">
        <a-space wrap>
            <template #split>
                <a-divider direction="vertical"/>
            </template>
            <a-button type="primary" status="success" @click="toEditPigeon()">新增鸽子</a-button>
            <a-button type="primary" status="success" @click="toRapid()">鸽子快速入库</a-button>
            <a-button type="primary" status="success" @click="openFileModal()">文件批量导入</a-button>
        </a-space>
    </div>
    <a-divider :size="2" style="border-bottom-style: dotted" orientation="left">其它操作</a-divider>
    <div class="button-group">
        <a-row justify="space-between">
            <a-space wrap>
                <template #split>
                    <a-divider direction="vertical"/>
                </template>
                <a-button type="primary" status="warning" @click="toBatch()">高级批量操作</a-button>
                <a-button type="primary" status="warning" @click="toLog()">查看操作记录</a-button>
                <a-button type="primary" status="danger">打印血统</a-button>
                <a-button type="primary" status="danger">导出血统书展示链接</a-button>
            </a-space>
            <a-space>
                <a-button type="primary" status="success" @click="resetTable()">
                    <IconRefresh :style="{color: '#ffffff'}"/>
                    清空筛选
                </a-button>
            </a-space>
        </a-row>
    </div>
    <a-divider :size="2" style="border-bottom-style: dotted" orientation="left">鸽子信息</a-divider>
    <a-table ref="table" :columns="columns" :data="data" :sticky-header="true" :column-resizable="true"
             :bordered="{ cell: true }"
             :row-selection="{
            type: 'checkbox',
            showCheckedAll: true,
            onlyCurrent: true
        }" v-model:selectedKeys="selectedKeys" :pagination="paginationProps" @page-size-change="pageSizeChange"
             @page-change="pageChange">
        <!-- 父足环表头插槽 -->
        <template #father>
            <IconMan/>
            父足环
        </template>
        <!-- 母足环表头插槽 -->
        <template #mother>
            <IconWoman/>
            母足环
        </template>
        <!-- 操作插槽 -->
        <template #front-operation="{ record }">
            <a-space wrap>
                <a-button type="outline" status="success"
                          @click="handleCheckModalOpen(record)">
                    查看
                </a-button>
                <a-button type="outline" status="normal"
                          @click="handleEditModalOpen(record)">
                    编辑
                </a-button>
            </a-space>
        </template>
        <template #end-operation="{ record }">
            <a-space wrap>
                <a-button type="outline" status="warning"
                          @click="toEditPigeonById(record)">
                    编辑血统
                </a-button>
                <a-button type="outline" status="danger"
                          @click="handleDeleteModalOpen(record)">
                    删除
                </a-button>
            </a-space>
        </template>
        <!-- 图片插槽 -->
        <template #picture="{ record }">
            <a-image :src="`${pigeonResourcePath}/${record.pictureUrl}`" width="100%" height="100%" fit="cover"
                     v-if="record.pictureUrl"/>
        </template>
        <!-- 足环插槽 -->
        <template #ringNumber="{ record }">
            <routerLink :to="{name: 'detail', params: {id: record.id}}" :style="{color: '#D91AD9'}">
                <a-tooltip content="点击查看鸽子详情" :mini="true" background-color="#FDC2DB">
                    <div>{{ record.ringNumber }}</div>
                </a-tooltip>
            </routerLink>
            <div>{{ record.name }}</div>
            <div>{{ record.bloodline }}</div>
        </template>
        <!-- 父足环插槽 -->
        <template #fatherRingNumber="{ record }">
            <routerLink :to="{name: 'detail', params: {id: record.fid}}" :style="{color: '#D91AD9'}">
                <a-tooltip content="点击查看鸽子详情" :mini="true" background-color="#F7BAEF">
                    <div>{{ record.father?.ringNumber }}</div>
                </a-tooltip>
            </routerLink>
            <div>{{ record.father?.name }}</div>
            <div>{{ record.father?.bloodline }}</div>
        </template>
        <!-- 母足环插槽 -->
        <template #motherRingNumber="{ record }">
            <routerLink :to="{name: 'detail', params: {id: record.mid}}" :style="{color: '#D91AD9'}">
                <a-tooltip content="点击查看鸽子详情" :mini="true" background-color="#DDBEF6">
                    <div>{{ record.mather?.ringNumber }}</div>
                </a-tooltip>
            </routerLink>
            <div>{{ record.mather?.name }}</div>
            <div>{{ record.mather?.bloodline }}</div>
        </template>
        <!-- updateData搜索 -->
        <template #updateData-filter="{ filterValue, setFilterValue, handleFilterConfirm, handleFilterReset}">
            <div class="custom-filter">
                <a-space direction="vertical">
                    <a-range-picker value-format="Date" v-model="filterValue[0]"/>
                    <div class="custom-filter-footer">
                        <a-button @click="handleFilterConfirm">搜索</a-button>
                        <a-button @click="handleFilterReset">重置</a-button>
                    </div>
                </a-space>
            </div>
        </template>
        <!-- gpcx搜索 -->
        <template #gpcx-filter="{ filterValue, setFilterValue, handleFilterConfirm, handleFilterReset}">
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
        <!-- father搜索 -->
        <template #fatherRingNumber-filter="{ filterValue, setFilterValue, handleFilterConfirm, handleFilterReset}">
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
        <!-- mather搜索 -->
        <template #motherRingNumber-filter="{ filterValue, setFilterValue, handleFilterConfirm, handleFilterReset}">
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
        <!-- yan搜索 -->
        <template #yan-filter="{ filterValue, setFilterValue, handleFilterConfirm, handleFilterReset}">
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
        <!-- ys搜索 -->
        <template #ys-filter="{ filterValue, setFilterValue, handleFilterConfirm, handleFilterReset}">
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
        <!-- lx搜索 -->
        <template #lx-filter="{ filterValue, setFilterValue, handleFilterConfirm, handleFilterReset}">
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
        <!-- state搜索 -->
        <template #state-filter="{ filterValue, setFilterValue, handleFilterConfirm, handleFilterReset}">
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
        <template #jb-filter="{ filterValue, setFilterValue, handleFilterConfirm, handleFilterReset}">
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
        <!-- remark搜索 -->
        <template #remark-filter="{ filterValue, setFilterValue, handleFilterConfirm, handleFilterReset}">
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
    <a-modal v-model:visible="editModal.visible" :title="editModal.title" width="calc(520px + 0.1 * 100vw)"
             @before-ok="handleEditPigeon" @cancel="handleEditCancel" @before-open="handleBeforeOpen">
        <a-form :model="form" :disabled="editModal.disabled">
            <a-form-item label="足环" extra="由“-”组合成足环" :content-flex="false" :merge-props="false" required>
                <a-row :wrap="false" :gutter="1">
                    <a-col flex="1">
                        <a-form-item field="country" validate-trigger="blur" :hide-asterisk="true" required>
                            <a-select v-model.lazy="form.country" :options="options.countryOptions" placeholder="-国家-"
                                      allow-search/>
                        </a-form-item>
                    </a-col>
                    <a-col flex="1">
                        <a-form-item field="year" validate-trigger="blur" :hide-asterisk="true" required>
                            <a-year-picker v-model.lazy="form.year"/>
                        </a-form-item>
                    </a-col>
                    <a-col flex="1">
                        <a-form-item field="province" validate-trigger="blur" :hide-asterisk="true" required>
                            <a-select v-model.lazy="form.province" :options="options.provinceOptions"
                                      placeholder="-省份-"
                                      allow-search/>
                        </a-form-item>
                    </a-col>
                    <a-col flex="1">
                        <a-form-item field="code" validate-trigger="blur" :hide-asterisk="true" required>
                            <a-input v-model.lazy="form.code" placeholder="编号"/>
                        </a-form-item>
                    </a-col>
                </a-row>
            </a-form-item>
            <a-form-item label="名称" field="name">
                <a-input v-model.lazy="form.name" placeholder="名称"/>
            </a-form-item>
            <a-form-item label="血统" field="bloodline">
                <a-input v-model.lazy="form.bloodline" placeholder="血统"/>
            </a-form-item>
            <a-form-item label="眼砂" field="yan">
                <a-select v-model.lazy="form.yan" :options="options.yanpzOptions" placeholder="-眼砂-" allow-search/>
            </a-form-item>
            <a-form-item label="羽色" field="ys">
                <a-select v-model.lazy="form.ys" :options="options.yspzOptions" placeholder="-羽色-" allow-search/>
            </a-form-item>
            <a-form-item label="类型" field="lx">
                <a-select v-model.lazy="form.lx" :options="options.lxpzOptions" placeholder="-类型-" allow-search/>
            </a-form-item>
            <a-form-item label="状态" field="state">
                <a-select v-model.lazy="form.state" :options="options.stateOptions" placeholder="-状态-" allow-search/>
            </a-form-item>
            <a-form-item label="级别" field="jb">
                <a-select v-model.lazy="form.jb" :options="options.jbpzOptions" placeholder="-级别-" allow-search/>
            </a-form-item>
            <a-form-item label="赛绩鸽" field="isGrade">
                <a-select v-model.lazy="form.isGrade" :options="['否', '是']" placeholder="-赛绩鸽-"/>
            </a-form-item>
            <a-form-item label="备注" field="remark" extra="允许换行输入">
                <a-textarea v-model.lazy="form.remark" auto-size/>
            </a-form-item>
        </a-form>
    </a-modal>
  <!--批量编辑模态框-->
    <a-modal v-model:visible="batchEditModal.visible" :title="batchEditModal.title" width="calc(300px + 0.12 * 100vw)"
             @before-ok="handleEditBatch" @cancel="handleCancel">
        <a-form :model="batchEditModal">
            <a-form-item :label="batchEditModal.label" :field="batchEditModal.field">
                <a-select v-model.lazy="batchEditModal.data" :options="batchEditModal.options"
                          :placeholder="batchEditModal.label" allow-search/>
            </a-form-item>
        </a-form>
    </a-modal>
  <!--批量分享血统-->
    <a-modal v-model:visible="batchShareModal.visible" title="批量分享血统" width="calc(300px + 0.12 * 100vw)"
             @before-ok="handleShareBatch" @cancel="handleCancel">
        <a-form :model="batchShareModal">
            <a-form-item label="管理员名称" field="gid">
                <a-select v-model.lazy="batchShareModal.gid" :options="batchShareModal.options"
                          placeholder="选择对方的管理员名称" allow-search/>
            </a-form-item>
        </a-form>
        <div>共享后对方数据中不保留父代信息</div>
    </a-modal>
  <!--批量加入鸽棚巢箱-->
    <a-modal v-model:visible="batchGpcxModal.visible" title="批量加入鸽棚巢箱" width="calc(300px + 0.12 * 100vw)"
             @before-ok="handleGpcxBatch" @cancel="handleCancel">
        <a-form :model="batchGpcxModal">
            <a-form-item label="鸽棚巢箱名称" field="id">
                <a-select v-model.lazy="batchGpcxModal.id" :options="batchGpcxModal.options"
                          placeholder="选择鸽棚巢箱" allow-search/>
            </a-form-item>
        </a-form>
    </a-modal>
  <!--通过文件导入模态框-->
    <a-modal v-model:visible="fileModal.visible" title="文件快速导入" top="10" :align-center="false" draggable
             @cancel="handleCancel" :footer="false">
        <a-upload draggable :custom-request="handleFileUpload" tip="上传.xls\.xlsx文件"
                  accept="application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"/>
    </a-modal>
</template>

<style scoped lang="scss">
.button-group {
  margin: 0 10px;
}

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