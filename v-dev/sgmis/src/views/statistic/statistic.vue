<script>
import SexHalfDoughnut from "./components/sexHalfDoughnut.vue";
import YanNightingale from "./components/yanNightingale.vue";
import YsNightingale from "./components/ysNightingale.vue";
import GradeHalfDoughnut from "./components/gradeHalfDoughnut.vue";
import LxRoundedCornerDoughnut from "./components/lxRoundedCornerDoughnut.vue";
import StateRoundedCornerDoughnut from "./components/stateRoundedCornerDoughnut.vue";
import JbRoundedCornerDoughnut from "./components/jbRoundedCornerDoughnut.vue";
import SourceBar from "./components/sourceBar.vue";

export default {
    components: {
        SourceBar, JbRoundedCornerDoughnut, StateRoundedCornerDoughnut, LxRoundedCornerDoughnut,
        GradeHalfDoughnut, YsNightingale, YanNightingale, SexHalfDoughnut
    }
}
</script>

<template>
    <a-grid :cols="{xs: 1, sm: 1, md: 2, xxl: 3}" :col-gap="16" :row-gap="16">
        <a-grid-item>
            <a-card title="库中鸽子雌雄比例图">
                <div class="chart">
                    <sex-half-doughnut/>
                </div>
            </a-card>
        </a-grid-item>
        <a-grid-item>
            <a-card title="赛绩鸽环形半圆图">
                <div class="chart">
                    <grade-half-doughnut/>
                </div>
            </a-card>
        </a-grid-item>
        <a-grid-item :span="2">
            <a-card title="眼色玫瑰图">
                <div class="chart">
                    <yan-nightingale/>
                </div>
            </a-card>
        </a-grid-item>
        <a-grid-item :span="2">
            <a-card title="羽色玫瑰图图">
                <div class="chart">
                    <ys-nightingale/>
                </div>
            </a-card>
        </a-grid-item>
        <a-grid-item>
            <a-card title="类型圆角环形图">
                <div class="chart">
                    <lx-rounded-corner-doughnut/>
                </div>
            </a-card>
        </a-grid-item>
        <a-grid-item>
            <a-card title="状态圆角环形图">
                <div class="chart">
                    <state-rounded-corner-doughnut/>
                </div>
            </a-card>
        </a-grid-item>
        <a-grid-item>
            <a-card title="级别圆角环形图">
                <div class="chart">
                    <jb-rounded-corner-doughnut/>
                </div>
            </a-card>
        </a-grid-item>
        <a-grid-item>
            <a-card title="来源柱状图">
                <div class="chart">
                    <source-bar/>
                </div>
            </a-card>
        </a-grid-item>
    </a-grid>
</template>

<style scoped>
.chart {
    height: 600px;
    width: 100%;
}
</style>
