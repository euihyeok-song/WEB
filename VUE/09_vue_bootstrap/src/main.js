import { createApp } from 'vue'
import App from './App.vue'

import BootstrapVue3 from 'bootstrap-vue-3';

import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue-3/dist/bootstrap-vue-3.css';

/* 부트스트랩 관련 라이브러리 설치 */
/*
    1. package.json 있는 폴더로 가서 
        npm install
    2. bootstrap 관련 npm 설치
        npm install vue bootstrap bootstrap-vue-3
*/

// createApp(App).mount('#app')
const app = createApp(App);
app.use(BootstrapVue3)
app.mount('#app')
