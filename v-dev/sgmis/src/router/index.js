import { createRouter, createWebHistory } from "vue-router"
import axiosx from "@/assets/js/axiosx.js"
import {Notification} from "@arco-design/web-vue";

const router = createRouter({
    history: createWebHistory('/'),
    routes: [
        {
            path: "/login",
            name: "login",
            component: () => import('@/views/login/login.vue'),
            meta: {
                title: "登录",
                free: true
            }
        },
        {
            path: "/main",
            component: () => import('@/components/layout.vue'),
            children: [
                {
                    path: "home",
                    name: "home",
                    component: () => import("@/views/home/home.vue"),
                    meta: {
                        title: "首页"
                    }
                },
                {
                    path: "loginMsg",
                    name: "loginMsg",
                    component: () => import("@/views/loginMsg/loginMsg.vue"),
                    meta: {
                        title: "登录信息"
                    }
                },
                {
                    path: "feedback",
                    name: "feedback",
                    component: () => import("@/views/feedback/feedback.vue"),
                    meta: {
                        title: "反馈中心"
                    }
                },
                {
                    path: "statistic",
                    name: "statistic",
                    component: () => import("@/views/statistic/statistic.vue"),
                    meta: {
                        title: "统计中心"
                    }
                },
                {
                    path: "gpcx",
                    name: "gpcx",
                    component: () => import("@/views/gpcx/gpcx.vue"),
                    meta: {
                        title: "鸽棚巢箱"
                    }
                },
                {
                    path: "system/gsxx",
                    name: "gsxx",
                    component: () => import("@/views/gsxx/gsxx.vue"),
                    meta: {
                        title: "鸽舍信息"
                    }
                },
                {
                    path: "gzk/:name?",
                    name: "gzk",
                    component: () => import("@/views/gzk/gzk.vue"),
                    meta: {
                        title: "鸽子库"
                    }
                },
                {
                    path: "pigeon/editPigeon/:id(\\d+)?",
                    name: "editPigeon",
                    component: () => import("@/views/editPigeon/editPigeon.vue"),
                    meta: {
                        title: "编辑血统"
                    }
                },
                {
                    path: "pigeon/rapid",
                    name: "rapid",
                    component: () => import("@/views/rapid/rapid.vue"),
                    meta: {
                        title: "快速入库"
                    }
                },
                {
                    path: "pigeon/batch",
                    name: "batch",
                    component: () => import("@/views/batch/batch.vue"),
                    meta: {
                        title: "高级批量操作"
                    }
                },
                {
                    path: "pigeon/log",
                    name: "log",
                    component: () => import("@/views/log/log.vue"),
                    meta: {
                        title: "操作日志"
                    }
                },
                {
                    path: "help",
                    name: "help",
                    component: () => import("@/views/help/help.vue"),
                    meta: {
                        title: "帮助中心"
                    }
                },
                {
                    path: "system/options",
                    name: "options",
                    component: () => import("@/views/options/options.vue"),
                    meta: {
                        title: "选项设置"
                    }
                },
                {
                    path: "system/xtspz",
                    name: "xtspz",
                    component: () => import("@/views/xtspz/xtspz.vue"),
                    meta: {
                        title: "血统书配置"
                    }
                },
                {
                    path: "system/admin",
                    name: "admin",
                    component: () => import("@/views/admin/admin.vue"),
                    meta: {
                        title: "管理员设置",
                        requiresAdminAuth: true
                    }
                },
                {
                    path: "system/user",
                    name: "user",
                    component: () => import("@/views/user/user.vue"),
                    meta: {
                        title: "用户设置"
                    }
                },
                {
                    path: "detail/:id(\\d+)?",
                    name: "detail",
                    component: () => import("@/views/detail/detail.vue"),
                    meta: {
                        title: "详情"
                    }
                }
            ],
            meta: {
                requiresAuth: true
            },
            redirect: { name: "home" }
        },
        {
            path: "/error",
            component: () => import('@/views/error/error.vue'),
            children: [
                {
                    path: "500",
                    name: "500",
                    component: () => import('@/views/error/components/500.vue'),
                    meta: {
                        title: "500"
                    }
                },
                {
                    path: "404/:undefined(.*)",
                    name: "404",
                    component: () => import("@/views/error/components/404.vue"),
                    meta: {
                        title: "404"
                    }
                },
                {
                    path: "403",
                    name: "403",
                    component: () => import("@/views/error/components/403.vue"),
                    meta: {
                        title: "403"
                    }
                }
            ]
        },
        {
            path: "/",
            redirect: { name: "login" }
        },
        {
            path: "/:undefined(.*)",
            redirect: { name: "404" }
        }
    ]
})

router.beforeEach(async (to) => {
    //免密登录
    if (to.meta.free) {
        await axiosx({
            method: "GET",
            url: "login/free",
            message: "验证信息"
        }).then(res => {
            if (res.data.code === 200) {
                router.push({ name: "home" })
                Notification.success(res.data.message)
            }
        })
    }
    //验证访问
    if (to.meta.requiresAuth) {
        await axiosx({
            method: "GET",
            url: "login",
            message: "登录验证"
        })
    }
    //验证管理员访问
    if(to.meta.requiresAdminAuth) {
        await axiosx({
            method: "GET",
            url: "login/admin",
            message: "验证管理员信息"
        })
    }
    //动态标题
    if (to.meta.title) {
        document.title = `赛鸽云库 -- ${to.meta.title}`
    }
})

export default router
