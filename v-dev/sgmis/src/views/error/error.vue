<script>
import {IconEmail, IconGithub, IconGoogleCircleFill} from '@arco-design/web-vue/es/icon'

export default {
    components: {
        IconGithub,
        IconEmail,
        IconGoogleCircleFill
    }
}
</script>

<template>
    <div class="content">
        <router-view></router-view>
        <div class="arco-result-subtitle">联系管理员：2021070030101@guat.edu.cn</div>
        <div class="arco-result-subtitle">
            <a-space>
                <div>
                    <a target="_blank" href="https://github.com/Shi-AE">
                        <iconGithub size="30"/>
                    </a>
                </div>
                <div>
                    <a target="_blank" href="mailto:2021070030101@guat.edu.cn">
                        <IconEmail size="30"/>
                    </a>
                </div>
                <div>
                    <a target="_blank" href="mailto:shiyixiangae@gmail.com">
                        <iconGoogleCircleFill size="30"/>
                    </a>
                </div>
            </a-space>
        </div>
        <div :style="{background:'#FFFFFF', display: 'flex', alignItems: 'center', justifyContent: 'center', width:'100%' , position: 'absolute', bottom: 0}">
            2023-09@SGMIS

            备案号：
            <a href="https://beian.miit.gov.cn" target="_blank" style=" text-decoration: none;">
                浙ICP备2024071340号
            </a>
        </div>
    </div>
</template>
<style scoped>
.content {
    height: 100vh;
    display: flex;
    flex-direction: column;
    justify-content: center;
}
</style>
