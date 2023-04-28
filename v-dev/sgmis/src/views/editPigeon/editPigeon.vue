<script>
import * as echarts from "echarts"
import axiosx from "@/assets/js/axiosx.js"
import {verifyData} from "@/assets/js/loading.js"
import {Notification} from '@arco-design/web-vue'
import {IconSearch, IconPlus, IconEdit} from '@arco-design/web-vue/es/icon'

const height = [400, 400, 280, 120]
const offset = [0, 70, 0, -27]
const tree = 0
const pigeonResourcePath = `${window.location.protocol}//${window.location.hostname}/pigeon`
const sexJudge = {
    "父": 0,
    "我": 0,
    "母": 1
}

export default {
    components: {IconSearch, IconPlus, IconEdit},
    data() {
        return {
            data: null,
            updatedBloodline: null,
            //模态框控制
            editModal: false,
            form: {
                id: null,
                country: null,
                year: null,
                province: null,
                code: null,
                name: null,
                bloodline: null,
                detail: null,
                source: '自育',
                breeder: null,
                subRingNumber: null,
                sex: '雄',
                ys: null,
                yan: null,
                lx: null,
                isGrade: null,
                pictureUrl: null,
                //临时子代id
                oid: null,
            },
            //表单禁用控制
            isMe: false,
            //临时祖先树，用于深搜更新
            treeAncestors: null,
            //模态框选框参数
            options: {
                countryOptions: [],
                provinceOptions: [],
                breederOptions: [],
                yspzOptions: [],
                yanpzOptions: [],
                lxpzOptions: [],
            },
            //图片上传变量
            file: {},
        }
    },
    async mounted() {
        await this.initData()
        this.updatedBloodline = this.initBloodline()
    },
    methods: {
        //初始化数据树
        async initData() {
            this.data = [await this.createTree(this.$route.params.id, "我", 0)]
        },
        //递归建树
        async createTree(id, name, index) {
            if (index < 4) {
                let node = {
                    name,
                    value: {
                        pigeon: {
                            id
                        }
                    },
                    label: {
                        height: height[index],
                        offset: [0, name === "母" ? -offset[index] : offset[index]],
                        backgroundColor: id ? "#FFE8FB" : "#ffffff"
                    },
                    children: []
                }
                if (id) {
                    await axiosx({
                        method: "GET",
                        url: `pigeon/${id}`,
                        message: "正在获取鸽子数据"
                    }).then(res => {
                        if (res.data.code === 200) {
                            node.value = JSON.parse(JSON.stringify(res.data.data))
                        }
                    })
                }
                const father = await this.createTree(node.value.pigeon.fid, "父", index + 1)
                if (father) {
                    node.children.push(father)
                }
                const mother = await this.createTree(node.value.pigeon.mid, "母", index + 1)
                if (mother) {
                    node.children.push(mother)
                }
                return node
            }
        },
        //深搜更新树
        async handleUpdateTree(nodes, index, id) {
            const sex = this.treeAncestors[index].name
            if (index < this.treeAncestors.length - 1) {
                await this.handleUpdateTree(nodes[sexJudge[sex]].children, index + 1, id)
                return
            }
            //更新值本节点值,搜索本节点、子节点值
            nodes[sexJudge[sex]] = await this.createTree(id, sex, index - 1)
            this.updatedBloodline()
        },
        //动态获取配置
        getOption() {
            return {
                title: {
                    text: "编辑鸽子",
                    subtext: this.$route.params.id ? "修改" : "新增"
                },
                tooltip: {
                    trigger: "none",
                    triggerOn: "none"
                },
                series: [{
                    type: "tree",
                    orient: "LR",
                    top: '0%',
                    left: '12%',
                    bottom: '0%',
                    right: '12%',
                    edgeShape: "polyline",
                    symbol: "none",
                    initialTreeDepth: -1,
                    expandAndCollapse: false,
                    roam: "move",
                    label: {
                        borderWidth: 10,
                        padding: 10,
                        borderColor: '#eee',
                        backgroundColor: "#fff",
                        width: 200,
                        overflow: "truncate",
                        formatter: node => {
                            const pigeon = node.value.pigeon
                            const pigeonInfo = node.value.pigeonInfo
                            return [
                                `{title|${node.name}}`,
                                `{hr|}`,
                                pigeon?.ringNumber ? `{text|${pigeon.ringNumber}}` : "{empty|}",
                                `{hr|}`,
                                pigeon?.name ? `{text|${pigeon.name}}` : "{empty|}",
                                `{text|${pigeon?.sex ?? ""} ${pigeon?.ys ?? ""} ${pigeon?.yan ?? ""}}`,
                                pigeon?.bloodline ? `{text|${pigeon.bloodline}}` : "{empty|}",
                                pigeonInfo?.detail ? `{text|${pigeonInfo.detail}}` : "{empty|}"
                            ].join("\n")
                        },
                        rich: {
                            title: {
                                align: "center",
                                fontSize: 15,
                                height: 30
                            },
                            text: {
                                height: 30,
                                fontSize: 15
                            },
                            empty: {
                                height: 0,
                            },
                            hr: {
                                borderColor: '#777',
                                width: '100%',
                                borderWidth: 0.5,
                                height: 0
                            }
                        }
                    },
                    data: this.data,
                }]
            }
        },
        //初始化血统书树状图
        initBloodline() {

            const opts = {
                renderer: "svg",
                useDirtyRect: true,
                locale: "ZH"
            }

            const bloodline = echarts.init(this.$refs.bloodline, null, opts)

            //点击节点触发事件
            bloodline.on("click", e => {
                let n = e.treeAncestors.length
                //查看父级是否有数据
                if (n > 2 && !e.treeAncestors[n - 2].value.pigeon.id) {
                    //无数据
                    Notification.warning("子代暂无数据，无法编辑")
                    return
                }
                //模态表单初始化
                this.isMe = e.name === "我"
                if (e.value.pigeon.id) {
                    const pigeon = e.value.pigeon
                    const pigeonInfo = e.value.pigeonInfo
                    const splitRingNumber = pigeon?.ringNumber?.split("-")
                    this.form = {
                        id: pigeon.id ?? null,
                        country: splitRingNumber ? splitRingNumber[0] : null,
                        year: splitRingNumber ? splitRingNumber[1] : null,
                        province: splitRingNumber ? splitRingNumber[2] : null,
                        code: splitRingNumber ? splitRingNumber[3] : null,
                        name: pigeon?.name ?? null,
                        bloodline: pigeon?.bloodline ?? null,
                        detail: pigeonInfo?.detail ?? null,
                        source: pigeonInfo?.source ?? "自育" === "自育" ? "自育" : "引进",
                        breeder: pigeonInfo?.source ?? "自育" === "自育" ? null : pigeonInfo.source,
                        subRingNumber: pigeonInfo?.subRingNumber ?? null,
                        sex: pigeon?.sex ?? null,
                        ys: pigeon?.ys ?? null,
                        yan: pigeon?.yan ?? null,
                        lx: pigeon?.lx ?? null,
                        isGrade: pigeon?.isGrade ?? null,
                        pictureUrl: pigeon?.pictureUrl ?? null,
                    }
                    this.file = {
                        url: pigeon?.pictureUrl ? `${pigeonResourcePath}/${pigeon.pictureUrl}` : null,
                        name: pigeon?.pictureUrl ?? null
                    }
                }
                //临时子代id
                this.form.oid = e.treeAncestors[n - 2]?.value?.pigeon?.id ?? null
                this.form.sex = e.name === "母" ? "雌" : "雄"

                //存放临时时间树
                this.treeAncestors = e.treeAncestors

                //开启模态框
                this.editModal = true
            })

            bloodline.setOption(this.getOption())

            return () => bloodline.setOption(this.getOption())
        },
        //重置表单
        resetForm() {
            this.form = {
                id: null,
                country: null,
                year: null,
                province: null,
                code: null,
                name: null,
                bloodline: null,
                detail: null,
                source: '自育',
                breeder: null,
                subRingNumber: null,
                sex: '雄',
                ys: null,
                yan: null,
                lx: null,
                isGrade: null,
                pictureUrl: null,
                //临时子代id
                oid: null,
            }
            this.isMe = false
            this.treeAncestors = null
            this.file = {}
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
        //获取选项信息
        handleBeforeOpen() {
            //判断是否读取过数据库
            let isSelected = false
            for (let key in this.options) {
                if (this.options[key].length > 0) {
                    isSelected = true
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
        onChange(_, currentFile) {
            this.file = currentFile
        },
        //处理上传图片
        customRequest(option) {
            const {onProgress, onError, onSuccess, fileItem, name} = option
            const formData = new FormData();
            formData.append(name || 'file', fileItem.file);
            axiosx({
                method: "POST",
                url: "pigeon/picture",
                data: formData,
                message: "正在上传图片",
                onUploadProgress: progressEvent => {
                    const percent = progressEvent.loaded / progressEvent.total
                    onProgress(percent, progressEvent)
                }
            }).then(res => {
                if (res.data.code === 200) {
                    onSuccess(res)
                    this.form.pictureUrl = res.data.data
                    Notification.success(res.data.message)
                } else {
                    onError(res)
                    this.file = {}
                    Notification.error(res.data.message)
                }
            }).catch(res => {
                onError(res)
                this.file = {}
                Notification.error("服务器发生错误，上传超时")
            })
        },
        //处理编辑鸽子
        async handleEditPigeon() {
            const form = this.form
            if (!verifyData(() => {
                //必填参数检查
                if (!form.country || !form.year || !form.province || !form.code || !form.sex) {
                    Notification.error("必填信息为空")
                    return false
                }
                if (form.source === "引进" && !form.breeder) {
                    Notification.error("引进选项未找到作育者")
                    return false
                }
            }, "正在验证数据")) {
                return true
            }
            const formWrapper = {
                pigeon: {
                    id: form.id,
                    pictureUrl: form.pictureUrl,
                    ringNumber: [
                        form.country,
                        form.year,
                        form.province,
                        form.code
                    ].join("-"),
                    name: form.name,
                    bloodline: form.bloodline,
                    sex: form.sex,
                    yan: form.yan,
                    ys: form.ys,
                    lx: form.lx,
                    isGrade: form.isGrade
                },
                pigeonInfo: {
                    source: form.source === "自育" ? "自育" : form.breeder,
                    subRingNumber: form.subRingNumber,
                    detail: form.detail
                },
                oid: this.form.oid
            }
            await axiosx({
                method: "POST",
                url: "pigeon",
                data: formWrapper,
                message: "正在上传鸽子信息"
            }).then(res => {
                if (res.data.code === 200) {
                    Notification.success(res.data.message)
                    //更新树
                    const id = res.data.data
                    this.handleUpdateTree(this.data, 1, id)
                    this.resetForm()
                    return true
                } else if (res.data.code === 414) {
                    Notification.warning(`足环${res.data.message}，如需添加血亲关系请使用“搜索”`)
                }
            })
        }
    }
}
</script>

<template>
    <div ref="bloodline" class="bloodline"></div>
  <!-- 新编辑子模态框 -->
    <a-modal v-model:visible="editModal" title="编辑鸽子" :width="800" @before-ok="handleEditPigeon"
             @cancel="handleCancel"
             @before-open="handleBeforeOpen">
        <a-form :model="form">
            <a-divider :size="2" style="border-bottom-style: dotted" orientation="left">通过搜索输入</a-divider>
            <a-button type="primary">
                <IconSearch/>
                搜索
            </a-button>
            <a-divider :size="2" style="border-bottom-style: dotted" orientation="left">完成信息填写</a-divider>
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
            <a-form-item label="描述" field="detail" extra="允许换行输入">
                <a-textarea v-model.lazy="form.detail" auto-size/>
            </a-form-item>
            <a-form-item label="来源" field="source">
                <a-radio-group v-model.lazy="form.source" :options="['自育', '引进']"/>
            </a-form-item>
            <a-form-item v-if="form.source === '引进'" label="作育者" field="breeder" required>
                <a-select v-model.lazy="form.breeder" :options="options.breederOptions" placeholder="-作育者-"
                          allow-search/>
            </a-form-item>
            <a-form-item label="副足环" field="subRingNumber">
                <a-input v-model="form.subRingNumber" placeholder="副足环"/>
            </a-form-item>
            <a-form-item label="性别" field="sex" required>
                <a-select v-model.lazy="form.sex" :options="['雄', '雌']" placeholder="-性别-"
                          :disabled="!isMe || !!form.id"/>
            </a-form-item>
            <a-form-item label="羽色" field="ys">
                <a-select v-model.lazy="form.ys" :options="options.yspzOptions" placeholder="-羽色-" allow-search/>
            </a-form-item>
            <a-form-item label="眼砂" field="yan">
                <a-select v-model.lazy="form.yan" :options="options.yanpzOptions" placeholder="-眼砂-" allow-search/>
            </a-form-item>
            <a-form-item label="类型" field="lx">
                <a-select v-model.lazy="form.lx" :options="options.lxpzOptions" placeholder="-类型-" allow-search/>
            </a-form-item>
            <a-form-item label="赛绩鸽" field="isGrade">
                <a-select v-model.lazy="form.isGrade" :options="['否', '是']" placeholder="-赛绩鸽-"/>
            </a-form-item>
            <a-form-item label="鸽子照片" field="pictureUrl">
                <a-upload action="pigeon/picture" :fileList="file ? [file] : []" :show-file-list="false"
                          @change="onChange"
                          :custom-request="customRequest">
                    <template #upload-button>
                        <div :class="`arco-upload-list-item${file && file.status === 'error' ? ' arco-upload-list-item-error' : ''
                            }`">
                            <div class="arco-upload-list-picture custom-upload-avatar" v-if="file && file.url">
                                <img :src="file.url" alt="鸽子照片"/>
                                <div class="arco-upload-list-picture-mask">
                                    <IconEdit/>
                                </div>
                                <a-progress v-if="file.status === 'uploading' && file.percent < 100"
                                            :percent="file.percent"
                                            type="circle" size="mini" :style="{
                                        position: 'absolute',
                                        left: '50%',
                                        top: '50%',
                                        transform: 'translateX(-50%) translateY(-50%)',
                                    }"/>
                            </div>
                            <div class="arco-upload-picture-card" v-else>
                                <div class="arco-upload-picture-card-text">
                                    <IconPlus/>
                                    <div style="margin-top: 10px; font-weight: 600">上传</div>
                                </div>
                            </div>
                        </div>
                    </template>
                </a-upload>
            </a-form-item>
        </a-form>
    </a-modal>
</template>

<style scoped>
.bloodline {
    margin: 10px auto;
    height: 1320px;
    width: 1080px;
    border-radius: 10px;
    box-shadow: 0 0 5px 5px #00000015;
}
</style>