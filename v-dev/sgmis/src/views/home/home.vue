<script>
import LoginData from "./components/loginData.vue";
import OplogData from "./components/oplogData.vue";
import PigeonData from "./components/pigeonData.vue";
import PigeonPicture from "./components/pigeonPicture.vue";

export default {
    components: {LoginData, OplogData, PigeonData, PigeonPicture},
}
</script>

<template>
    <a-divider :size="2" style="border-bottom-style: dotted" orientation="right">鸽子展示</a-divider>
    <PigeonPicture/>
    <a-divider :size="2" style="border-bottom-style: dotted" orientation="left">基本数据</a-divider>
    <PigeonData/>
    <a-divider :size="2" style="border-bottom-style: dotted" orientation="right">操作数据</a-divider>
    <OplogData/>
    <a-divider :size="2" style="border-bottom-style: dotted" orientation="left">登录信息</a-divider>
    <LoginData/>
</template>