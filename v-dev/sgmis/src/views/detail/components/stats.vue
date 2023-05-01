<script>

export default {
    props: {
        tree: Object
    },
    data() {
        return {
            data: null
        }
    },
    beforeUpdate() {
        //解析包装
        const pigeonWrapper = this.tree[0].value
        const pigeon = pigeonWrapper.pigeon
        const pigeonInfo = pigeonWrapper.pigeonInfo

        this.data = [
            {
                label: "鸽棚/巢箱位置",
                value: pigeon.gpcx ?? "-"
            },
            {
                label: "入库时间",
                value: pigeonInfo.createTime ?? "-"
            },
            {
                label: "性别",
                value: pigeon.sex ?? "-"
            },
            {
                label: "眼砂",
                value: pigeon.yan ?? "-"
            },
            {
                label: "羽色",
                value: pigeon.ys ?? "-"
            },
            {
                label: "作育者",
                value: pigeonInfo.source ?? "-"
            },
            {
                label: "血统名称",
                value: pigeon.bloodline ?? "-"
            },
            {
                label: "类型",
                value: pigeon.lx ?? "-"
            },
            {
                label: "级别",
                value: pigeon.jb ?? "-"
            },
        ]
    }
}
</script>

<template>
    <a-divider :size="2" style="border-bottom-style: dotted" orientation="left">基本资料</a-divider>
    <div class="descriptions">
        <a-descriptions :data="data" title="数据表" size="large" layout="horizontal"
                        :column="{xxl: 6, xl: 5, lg: 4, md: 3, sm: 2, xs: 1}">
            <a-descriptions-item v-for="item of data" :label="item.label">
                <a-tag>{{ item.value }}</a-tag>
            </a-descriptions-item>
        </a-descriptions>
    </div>
</template>

<style scoped>
.descriptions {
    padding: 40px;
}
</style>
