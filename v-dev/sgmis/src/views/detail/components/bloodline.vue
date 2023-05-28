<script>
import * as echarts from "echarts";

export default {
    props: {
        tree: Object
    },
    beforeUpdate() {
        const opts = {
            renderer: "canvas",
            useDirtyRect: true,
            locale: "ZH"
        }
        const bloodline = echarts.init(this.$refs.bloodline, null, opts)
        bloodline.setOption(this.getOption())
    },
    methods: {
        //动态获取配置
        getOption() {
            return {
                title: {
                    text: `${this.tree[0].value.pigeon.ringNumber}`,
                    subtext: `${this.tree[0].value.pigeon.name ?? ""}\n${this.tree[0].value.pigeon.bloodline ?? ""}`
                },
                tooltip: {
                    trigger: "none",
                    triggerOn: "none"
                },
                toolbox: {
                    show: true,
                    orient: "vertical",
                    left: "left",
                    top: "100",
                    feature: {
                        //保存为图片
                        saveAsImage: {
                            title: "下载血统",
                            name: "鸽子血统",
                            type: "png",
                            pixelRatio: 5
                        }
                    }
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
                                pigeon?.ringNumber ? `{text|${pigeon.ringNumber}}` : "{placeholder|}",
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
                            placeholder: {
                                height: 30
                            },
                            hr: {
                                borderColor: '#777',
                                width: '100%',
                                borderWidth: 0.5,
                                height: 0
                            }
                        }
                    },
                    data: this.tree,
                }]
            }
        },
    }
}
</script>
<template>
    <div ref="bloodline" class="bloodline"></div>
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