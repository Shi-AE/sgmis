<script>
import Picture from "./components/picture.vue";
import Content from "./components/content.vue";
import Stats from "./components/stats.vue";
import axiosx from "../../assets/js/axiosx.js";

const height = [400, 400, 280, 120]
const offset = [0, 70, 0, -27]

export default {
    components: {Picture, Content, Stats},
    data() {
        return {
            data: null
        }
    },
    async mounted() {
        await this.initData()
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
                        offset: [0, name === "母" ? -offset[index] : offset[index]]
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
        getPictureUrl() {
            const pictures = []
            if (this.data) {
                //子
                const s = this.data[0]
                if (s?.value?.pigeon?.pictureUrl) {
                    pictures.push(
                        `${this.$store.state.pigeonResourcePath}/${s.value.pigeon.pictureUrl}`
                    )
                }
                //父
                const f = s.children[0]
                if (f?.value?.pigeon?.pictureUrl) {
                    pictures.push(
                        `${this.$store.state.pigeonResourcePath}/${f.value.pigeon.pictureUrl}`
                    )
                }
                //母
                const m = s.children[1]
                if (m?.value?.pigeon?.pictureUrl) {
                    pictures.push(
                        `${this.$store.state.pigeonResourcePath}/${m.value.pigeon.pictureUrl}`
                    )
                }
            }
            return pictures
        }
    }
}

</script>

<template>
  <!--鸽子图片展示-->
    <Picture :urls="getPictureUrl()"/>
  <!--数据展示-->
    <Stats :tree="data"/>
  <!--血统、鸽子信息、日志选项卡-->
    <Content :tree="data"/>
</template>