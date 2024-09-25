<template>
    <!-- props 방식은 부모로부터 객체로 값을 전달함으로, 객체에서 값을 꺼내서 사용해야함 -->
    <h3>전달 받은 props: {{ props.message }}</h3>
</template>

<script setup>
    import { defineProps } from 'vue';
    
    /*
        defineProps 함수를 활용해 String형의 message props를 받아 props 변수에 담는다.
        (slot과 달리 부모로부터 받으면 객체이다.)
        (또한 slot과 달리 변수에 담아두고 계속 사용할 수 있다.)
    */

    const props = defineProps({     // 객체로 만들어서 넘김
        message: String             // 넘어오는 값의 이름: 자료형 
    })
</script>

<style scoped>

</style>