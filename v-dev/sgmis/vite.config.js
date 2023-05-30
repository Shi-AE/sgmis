import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'

//dev
export default defineConfig({
    plugins: [vue()],
    resolve: {
        alias: {
            "@": "/src"
        }
    },
    server: {
        host: "0.0.0.0",
        proxy: {
            "/api": {
                target: "http://localhost:8080",
                changeOrigin: true,
                headers: {
                    'X-Forwarded-For': '1.1.1.1',
                },
            },
            "/pigeon": {
                target: "http://localhost:8080",
                changeOrigin: true
            },
            "/logo": {
                target: "http://localhost:8080",
                changeOrigin: true
            }
        }
    }
})

//build
// export default defineConfig({
//     plugins: [vue()],
//     base: '/',
//     resolve: {
//         alias: {
//             "@": "/src"
//         }
//     },
//     build: {
//         host: "0.0.0.0",
//     }
// })
