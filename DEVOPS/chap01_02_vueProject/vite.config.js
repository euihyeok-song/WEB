import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  // cors 해결
  server:{
    proxy:{
      '/api':{
        // 7777번 포트로 솎여서 7777번 포트로 갈것임을 의미 - 나머지는 속성
        target: 'http://localhost:7777',
        changeOrigin: true,   
        rewrite: (path) => path.replace(/^\/api/, '')     // /api를 경로에서 지워주는 역할
      }
    }
  }
})
