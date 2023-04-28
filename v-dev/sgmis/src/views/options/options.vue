<script setup>
import Config from "./components/config.vue";
</script>
<template>
    <a-tabs default-active-key="1" lazy-load>
        <a-tab-pane key="1" title="羽色配置">
            <config api="yspz" head="羽色"></config>
        </a-tab-pane>
        <a-tab-pane key="2" title="类型配置">
            <config api="lxpz" head="类型"></config>
        </a-tab-pane>
        <a-tab-pane key="3" title="眼砂配置">
            <config api="yanpz" head="眼砂"></config>
        </a-tab-pane>
        <a-tab-pane key="4" title="级别配置">
            <config api="jbpz" head="级别"></config>
        </a-tab-pane>
        <a-tab-pane key="5" title="医疗护理">
            <config api="ylhl" head="用途名称"></config>
        </a-tab-pane>
        <a-tab-pane key="6" title="鸽蛋雏鸽状态">
            <config api="gdcgzt" head="状态"></config>
        </a-tab-pane>
        <a-tab-pane key="7" title="作育者">
            <config api="breeder" head="姓名"></config>
        </a-tab-pane>
        <a-tab-pane key="8" title="鸽子状态">
            <config api="state" head="鸽子状态"></config>
        </a-tab-pane>
    </a-tabs>
</template>