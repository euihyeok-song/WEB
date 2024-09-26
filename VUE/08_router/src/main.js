import { createApp } from 'vue'

/* router는 반드시 use를 통해 등록해 주어야 한다. */
// import router from './router/01_router.js';
import router2 from './router/02_nestedRoute.js';

import App from './App.vue'

// createApp(App).use(router).mount('#app')     // routing 정보를 App.vue의 RouterView에 알려줌을 의미
const app = createApp(App);
// app.use(router);
app.use(router2);
app.mount('#app');
