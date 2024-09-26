<!-- views는 page와 동일한 개념이여서 하나의 페이지를 나타내며, components들을 가져와 페이지에 붙여서 사용하는 느낌 (client side routing)-->

<template>
    <div>
        <h1>Home</h1>
        <h2>currentRoute = {{ currentRoute }}</h2>
        <!-- <h2>currentRoute = {{ currentRoute.path }}</h2>   // 전달받은 값의 요소들을 뽑아서 사용 가능 -->
        <button @click="changeRouter">router 경로 변경</button>
    </div>
</template>

<!--
    useRoute:
    현재 라우트 객체에 액세스 할 수 있도록 해주는 메소드로 이 객체를 사용하면 라우트 ***정보***를 확인할
    수 있다.
    주로 현재 라우트의 매개변수 혹은 쿼리 문자열과 같은 정보를 사용하여 동적으로 컴포넌트를 렌더링하거나
    조건부 렌더링을 구현할 떄 사용된다. 

    useRouter:
    useRouter에서 제공하는 push함수를 활용하여 우리가 원하는 페이지로 라우팅 ***요청***을 보낼 수 있게 되고 해당 컴포넌트로
    <RouterView>가 바뀌면서 정보도 전달(params 또는 query)할 수 있다. 
-->

<script setup>
    import {useRoute, useRouter} from 'vue-router';

    const currentRoute = useRoute();
    const router = useRouter();  

    function changeRouter() {
        const num = 2;
        // router.push('/pathvariable/2');
        router.push(`/pathvariable/${num}`);     // 다른 경로로 rerendering
    }

</script>
ㄴ