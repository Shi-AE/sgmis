<script>
import {Notification} from '@arco-design/web-vue'
import axiosx from "../../assets/js/axiosx.js"
import {
    IconBytedanceColor,
    IconEmail,
    IconFaceSmileFill,
    IconGithub,
    IconGoogleCircleFill,
    IconHeartFill,
    IconLarkColor,
    IconThumbUpFill,
} from '@arco-design/web-vue/es/icon'

export default {
    components: {
        IconGithub,
        IconEmail,
        IconFaceSmileFill,
        IconLarkColor,
        IconBytedanceColor,
        IconHeartFill,
        IconThumbUpFill,
        IconGoogleCircleFill,
    },
    data() {
        return {
            githubQRC: null,
            guatQRC: null,
            mailQRC: null
        }
    },
    mounted() {
        axiosx({
            method: "GET",
            url: "feedback/github"
        }).then(res => {
            if (res.data.code === 200) {
                this.githubQRC = res.data.data
            } else {
                Notification.error(res.data.message)
            }
        })
        axiosx({
            method: "GET",
            url: "feedback/guat"
        }).then(res => {
            if (res.data.code === 200) {
                this.guatQRC = res.data.data
            } else {
                Notification.error(res.data.message)
            }
        })
        axiosx({
            method: "GET",
            url: "feedback/mail"
        }).then(res => {
            if (res.data.code === 200) {
                this.mailQRC = res.data.data
            } else {
                Notification.error(res.data.message)
            }
        })
    }
}
</script>

<template>
    <div class="feedback">
        <a-card title="反馈中心">
            <a-grid :cols="{xs: 1, sm: 2, md: 3}" :col-gap="16">
                <a-grid-item>
                    <a-card title="咨询、合作" hoverable>
                        <div>
                            <a target="_blank" href="https://github.com/Shi-AE">
                                <iconGithub size="30"/>
                            </a>
                        </div>
                        <a-image :src="githubQRC"></a-image>
                        <div>扫一扫</div>
                    </a-card>
                </a-grid-item>
                <a-grid-item>
                    <a-card title="反馈" hoverable>
                        <div>
                            <a target="_blank" href="mailto:2021070030101@guat.edu.cn">
                                <IconEmail size="30"/>
                            </a>
                        </div>
                        <a-image :src="mailQRC"/>
                        <div>扫一扫</div>
                    </a-card>
                </a-grid-item>
                <a-grid-item>
                    <a-card title="友情链接" hoverable>
                        <div>
                            <a target="_blank" href="https://www.guat.edu.cn">
                                <iconFaceSmileFill size="30"/>
                            </a>
                        </div>
                        <a-image :src="githubQRC"></a-image>
                        <div>扫一扫</div>
                    </a-card>
                </a-grid-item>
            </a-grid>
            <div :style="{padding: '50px'}">
                <a-space>
                    <a target="_blank" href="https://arco.design/">
                        <iconLarkColor size="20"/>
                    </a>
                    <a target="_blank" href="https://arco.design/">
                        <iconBytedanceColor size="20"/>
                    </a>
                    <a target="_blank" href="mailto:2021070030101@guat.edu.cn">
                        <iconHeartFill size="20"/>
                    </a>
                    <a target="_blank" href="mailto:2021070030101@guat.edu.cn">
                        <iconThumbUpFill size="20"/>
                    </a>
                    <a target="_blank" href="mailto:shiyixiangae@gmail.com">
                        <iconGoogleCircleFill size="20"/>
                    </a>
                </a-space>
            </div>
        </a-card>
    </div>
</template>

<style scoped>
.feedback {
    padding: 16px;
    text-align: center;
}
</style>