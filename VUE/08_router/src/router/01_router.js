/* 이 router 정보는 main.js에 인지시켜 줘야 한다 */
import {createRouter, createWebHashHistory, createWebHistory} from 'vue-router';
import HomeView from '@/views/01_router/HomeView.vue';
import PathVariable from '@/views/01_router/PathVariable.vue';
import QueryString from '@/views/01_router/QueryString.vue';

const router = createRouter({
    /* 요청을 보내거나 받으면, window의 프로퍼티인 location 객체에 history 속성에 저장됨 (뒤로가기 버튼에 사용) */
    // history: createWebHashHistory(),         // '/#/menu' => front에서 client routing을 위해 남기는 표식
    history: createWebHistory(),                // '/menu'  => front에서 client routing을 위해 남기는 표식
    routes: [
        {
            path: '/',
            component: HomeView
        },
        {
            /* pathvariable 방식으로 라우팅을 할 때는 값을 받아줄 변수명을 작성한다. */
            path: '/pathvariable/:id',
            component: PathVariable
        },
        {
            path: '/querystring',
            component: QueryString
        }
    ]
})

// export를 통해서 router를 내보내줘여지 다른 파일에서 이 파일을 불러서 사용할 수 있다.
export default router;