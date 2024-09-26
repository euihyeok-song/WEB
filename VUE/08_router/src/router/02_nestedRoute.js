import {createRouter, createWebHistory} from 'vue-router';

import HomeView from '@/views/01_router/HomeView.vue';
import RootRouter from '@/views/02_nestedRoute/RootRouter.vue';
// import NestedHome from '@/views/02_nestedRoute/NestedHome.vue';
import NestedView from '@/views/02_nestedRoute/NestedView.vue';

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            component: HomeView
        },
        {
            path: '/nested',
            component: RootRouter,
            children: [             // 중첩 router는 children을 사용 (자식)
                {
                    path: "home",   // /nested/home 
                    // component: NestedHome

                    /*
                        lazy loaded: 코드를 분할(청크)해서 필요할 떄마다 코드를 그 시점에 로딩하여 메모리 사용량 및
                                    성능 향상 목적(코드 스플리팅(나중에 vue 파일 가져오는 개념))
                                    => 해당 router를 사용할 때 import 되도록 설정(처음 페이지 열었을 때 실행되지 않도록 함)
                    */
                    component: () => import('@/views/02_nestedRoute/NestedHome.vue')
                },
                {
                    path: "view",
                    component: NestedView
                }
            ]
        },
    ]
})

export default router;