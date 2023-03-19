<script setup>
import config from "./components/config.vue"
</script>
<template>
    <a-tabs default-active-key="1" lazy-load>
        <a-tab-pane key="1" title="羽色配置">
            <config api="yspz" head="羽色" dbn="ys"></config>
        </a-tab-pane>
        <a-tab-pane key="2" title="类型配置">
            <config api="lxpz" head="类型" :dbn="lx"></config>
        </a-tab-pane>
        <a-tab-pane key="3" title="眼砂配置">
            <config api="眼砂配置" head="眼砂" :dbn="ys"></config>
        </a-tab-pane>
        <a-tab-pane key="4" title="级别配置">
            <config api="级别配置" head="级别"></config>
        </a-tab-pane>
        <a-tab-pane key="5" title="医疗护理">
            <config api="医疗护理" head="用途名称"></config>
        </a-tab-pane>
        <a-tab-pane key="6" title="鸽蛋雏鸽状态">
            <config api="鸽蛋雏鸽状态" head="状态"></config>
        </a-tab-pane>
        <a-tab-pane key="7" title="作育者">
        </a-tab-pane>
    </a-tabs>
</template>