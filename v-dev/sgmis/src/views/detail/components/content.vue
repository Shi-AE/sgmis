<script>
import Bloodline from "./bloodline.vue";
import Offspring from "./offspring.vue";
import SameGeneration from "./sameGeneration.vue";
import Log from "./log.vue";

export default {
    components: {
        Bloodline, Offspring, SameGeneration, Log
    },
    props: {
        tree: Object
    }
}
</script>
<template>
    <a-divider :size="2" style="border-bottom-style: dotted" orientation="left">详细资料</a-divider>
    <div class="content">
        <a-tabs type="line" lazy-load>
            <!--血统-->
            <a-tab-pane key="1" title="血统">
                <bloodline :tree="tree"/>
            </a-tab-pane>
            <!--子代资料-->
            <a-tab-pane key="2" title="子代资料">
                <offspring/>
            </a-tab-pane>
            <!--平辈资料-->
            <a-tab-pane key="3" title="平辈资料">
                <same-generation/>
            </a-tab-pane>
            <!--日志-->
            <a-tab-pane key="4" title="日志">
                <log/>
            </a-tab-pane>
        </a-tabs>
    </div>
</template>

<style scoped>
.content {
    min-height: 100vh;
}
</style>