<script>
import {defineComponent, ref} from 'vue';
import axiosx from "@/assets/js/axiosx.js"
import {
    IconApps,
    IconBarChart,
    IconBook,
    IconBranch,
    IconCalendar,
    IconCaretLeft,
    IconCaretRight,
    IconCustomerService,
    IconEdit,
    IconExport,
    IconFindReplace,
    IconHome,
    IconIdcard,
    IconNav,
    IconQuestionCircle,
    IconSearch,
    IconSelectAll,
    IconSettings,
    IconStorage,
    IconSubscribeAdd,
    IconTool,
    IconUser
} from '@arco-design/web-vue/es/icon'

export default defineComponent({
    components: {
        IconApps, IconBook, IconBranch, IconCalendar, IconCaretLeft, IconCaretRight, IconFindReplace,
        IconCustomerService, IconHome, IconNav, IconQuestionCircle, IconSearch, IconSettings, IconStorage,
        IconUser, IconSubscribeAdd, IconEdit, IconExport, IconSelectAll, IconTool, IconIdcard, IconBarChart
    },
    setup() {
        const collapsed = ref(false)
        const onCollapse = () => {
            collapsed.value = !collapsed.value
        }
        return {
            collapsed,
            onCollapse,
            onClickMenuItem(key) {
                return key
            },
            getSelectedKeys() {
                let path = this.$route.path.split('/')
                let params = this.$route.params
                for (let key in params) {
                    if (params[key] && params[key] > 0) {
                        path.pop()
                    }
                }
                return path.pop()
            },
            getOpenKeys() {
                let path = this.$route.path.split('/')
                let params = this.$route.params
                for (let key in params) {
                    if (params[key] && params[key] > 0) {
                        path.pop()
                    }
                }
                path.pop()
                path.shift()
                path.shift()
                return path
            },
            toSetting() {
                this.$router.push({name: "user"})
                this.$forceUpdate()
            },
            exit() {
                axiosx({
                    method: "DELETE",
                    url: "/login"
                }).then(() => {
                    this.$store.commit("setToken", "")
                    this.$store.commit("setAccount", "")
                    this.$store.commit("setAdmin", false)
                    this.$router.push({name: "login"})
                })
            },
            toAdmin() {
                this.$router.push({name: "admin"})
                this.$forceUpdate()
            }
        }
    },
    data() {
        return {
            title: document.title
        }
    },
    mounted() {
        const titleObserver = new MutationObserver(() => {
            this.title = document.title
        })
        titleObserver.observe(document.querySelector('title'), {childList: true})
    }
})
</script>

<template>
    <a-layout class="layout-main">
        <a-layout-sider hide-trigger collapsible :collapsed="collapsed" breakpoint="lg" :width="220"
                        @collapse="onCollapse">
            <div class="logo"></div>
            <a-menu :default-open-keys="getOpenKeys()" :selected-keys="[getSelectedKeys()]" :style="{ width: '100%' }">
                <RouterLink :to="{ name: 'home' }">
                    <a-menu-item key="home">
                        <IconHome/>
                        首页
                    </a-menu-item>
                </RouterLink>
                <a-divider margin="0 0 5px"/>

                <RouterLink :to="{ name: 'gzk' }">
                    <a-menu-item key="gzk">
                        <IconStorage/>
                        鸽子库
                    </a-menu-item>
                </RouterLink>
                <a-divider margin="0 0 5px"/>

                <a-sub-menu key="pigeon">
                    <template #title>
                        <span>
                            <IconTool/>
                            鸽子操作
                        </span>
                    </template>
                    <a-divider margin="0 0 5px"/>

                    <RouterLink :to="{ name: 'editPigeon' }">
                        <a-menu-item key="editPigeon">
                            <IconEdit/>
                            编辑血统
                        </a-menu-item>
                    </RouterLink>
                    <a-divider margin="0 0 5px"/>

                    <RouterLink :to="{ name: 'rapid' }">
                        <a-menu-item key="rapid">
                            <IconExport/>
                            快速入库
                        </a-menu-item>
                    </RouterLink>
                    <a-divider margin="0 0 5px"/>

                    <RouterLink :to="{ name: 'batch' }">
                        <a-menu-item key="batch">
                            <IconSelectAll/>
                            高级批量操作
                        </a-menu-item>
                    </RouterLink>
                    <a-divider margin="0 0 5px"/>

                    <RouterLink :to="{ name: 'log' }">
                        <a-menu-item key="log">
                            <IconBranch/>
                            操作日志
                        </a-menu-item>
                    </RouterLink>
                </a-sub-menu>
                <a-divider margin="0 0 5px"/>

                <RouterLink :to="{ name: 'statistic' }">
                    <a-menu-item key="statistic">
                        <iconBarChart/>
                        统计中心
                    </a-menu-item>
                </RouterLink>
                <a-divider margin="0 0 5px"/>

                <RouterLink :to="{ name: 'gpcx' }">
                    <a-menu-item key="gpcx">
                        <IconNav/>
                        鸽棚巢箱
                    </a-menu-item>
                </RouterLink>
                <a-divider margin="0 0 5px"/>

                <a-sub-menu key="system">
                    <template #title>
                        <span>
                            <IconSettings/>
                            系统配置
                        </span>
                    </template>
                    <a-divider margin="0 0 5px"/>

                    <RouterLink :to="{ name: 'options' }">
                        <a-menu-item key="options">
                            <IconApps/>
                            选项设置
                        </a-menu-item>
                    </RouterLink>
                    <a-divider margin="0 0 5px"/>

                    <RouterLink :to="{ name: 'gsxx' }">
                        <a-menu-item key="gsxx">
                            <IconSearch/>
                            鸽舍信息
                        </a-menu-item>
                    </RouterLink>
                    <a-divider margin="0 0 5px"/>

                    <RouterLink :to="{ name: 'xtspz' }">
                        <a-menu-item key="xtspz">
                            <IconBook/>
                            血统书配置
                        </a-menu-item>
                    </RouterLink>
                    <a-divider margin="0 0 5px"/>

                    <RouterLink :to="{ name: 'user' }">
                        <a-menu-item key="user">
                            <IconUser/>
                            用户设置
                        </a-menu-item>
                    </RouterLink>

                    <div v-if="$store.state.admin">
                        <a-divider margin="0 0 5px"/>
                        <RouterLink :to="{ name: 'admin' }">
                            <a-menu-item key="admin">
                                <IconIdcard/>
                                管理员设置
                            </a-menu-item>
                        </RouterLink>
                    </div>

                </a-sub-menu>
                <a-divider margin="0 0 5px"/>

                <RouterLink :to="{ name: 'loginMsg' }">
                    <a-menu-item key="loginMsg">
                        <IconFindReplace/>
                        登录信息
                    </a-menu-item>
                </RouterLink>
                <a-divider margin="0 0 5px"/>

                <RouterLink :to="{ name: 'help' }">
                    <a-menu-item key="help">
                        <IconQuestionCircle/>
                        帮助中心
                    </a-menu-item>
                </RouterLink>
                <a-divider margin="0 0 5px"/>

                <RouterLink :to="{ name: 'feedback' }">
                    <a-menu-item key="feedback">
                        <IconCustomerService/>
                        反馈中心
                    </a-menu-item>
                </RouterLink>

            </a-menu>
        </a-layout-sider>
        <a-layout>
            <a-layout-header style="padding-left: 20px;">
                <a-button shape="round" @click="onCollapse">
                    <IconCaretRight v-if="collapsed"/>
                    <IconCaretLeft v-else/>
                </a-button>
                <div class="header-title .container-sm">{{ title }}</div>
                <div class="user d-flex align-items-center">
                    <div class="header-title .container-sm" style="margin-right: 5px;">{{
                            this.$store.state.account
                        }}
                    </div>
                    <a-dropdown>
                        <a-button>
                            <IconUser/>
                        </a-button>
                        <template #content>
                            <a-doption @click="toSetting()">用户设置</a-doption>
                            <a-doption v-if="$store.state.admin" @click="toAdmin()">管理员设置</a-doption>
                            <a-doption @click="exit()">退出登录</a-doption>
                        </template>
                    </a-dropdown>
                </div>
            </a-layout-header>
            <a-layout :style="{
                padding: '0 20px',
                'background-color': '#F5E8FF'
            }">
                <a-breadcrumb :style="{ margin: '5px 0' }">
                </a-breadcrumb>
                <a-layout-content>
                    <RouterView></RouterView>
                </a-layout-content>
                <a-layout-footer>
                    2023-09@SGMIS

                    备案号：
                    <a href="https://beian.miit.gov.cn" target="_blank" style=" text-decoration: none;">
                        浙ICP备2024071340号
                    </a>
                </a-layout-footer>
            </a-layout>
        </a-layout>
    </a-layout>
</template>

<style scoped>
.layout-main {
    height: 100vh;
    background: var(--color-fill-2);
    border: 1px solid var(--color-border);
}

.layout-main :deep(.arco-layout-sider) .logo {
    height: 56px;
    background: rgba(255, 255, 255, 0.2);
    border-bottom: #D91AD950 1px solid;
    border-right: #D91AD950 1px solid;
    position: sticky;
    top: 0;
    z-index: 1;
    background-color: white;
}

.layout-main :deep(.arco-layout-sider-light) .logo {
    background-image: url(/img/logo.jpg);
    background-repeat: no-repeat;
    background-size: contain;
}

.layout-main :deep(.arco-layout-header) {
    height: 56px;
    line-height: 56px;
    padding-right: 30px;
    background: var(--color-bg-3);
    position: sticky;
    top: 0;
    display: flex;
    align-items: center;
    justify-content: space-between;
    border-bottom: #D91AD950 1px solid;
    z-index: 1;
}

.layout-main :deep(.arco-layout-footer) {
    height: 18px;
    color: var(--color-text-2);
    font-weight: 400;
    font-size: 14px;
    line-height: 18px;
    text-align: center;
}

.layout-main :deep(.arco-layout-content) {
    color: var(--color-text-2);
    font-weight: 400;
    font-size: 14px;
    background: var(--color-bg-3);
    padding: 5px;
    overflow: auto;
}

.layout-main :deep(.arco-layout-footer),
.layout-main :deep(.arco-layout-content) {
    font-stretch: condensed;
}

.layout-main :deep(.arco-menu-item),
.layout-main :deep(.arco-icon),
.layout-main :deep(.arco-menu-inline-header) {
    font-weight: 550;
    color: var(--color-text-1);
}

.layout-main :deep(.arco-menu-selected),
.layout-main :deep(.arco-menu-inline-header.arco-menu-selected .arco-icon),
.layout-main :deep(.arco-menu-item.arco-menu-selected .arco-icon),
.layout-main :deep(.arco-menu-light .arco-menu-pop-header.arco-menu-selected .arco-icon) {
    color: #D91AD9;
}

.layout-main :deep(.arco-btn-secondary[type='button']) {
    background-color: #F08EE6;
}

.layout-main .user :deep(.arco-btn-shape-square) {
    border-radius: 25%;
}

.layout-main :deep(.arco-trigger-menu-item) {
    text-decoration: none;
}

.layout-main .header-title {
    white-space: nowrap;
    text-overflow: ellipsis;
    font-weight: 600;
    color: #E865DF;
}
</style>
