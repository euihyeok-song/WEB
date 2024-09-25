
<!--
    부모로부터 전달된 이벤트(사용자 정의형)를 활용해 부모의 이벤트를 동작시켜 부모의 이벤트
    헨들러 함수를 실행한다.
    (자식 컴포넌트가 부모 컴포넌트로 발신(emit('해당 이벤트 이름')))
-->

<template>
    <!-- emit은 부모쪽의 toggle이라는 event 헨들러를 호출해주는 기능 -->
    <button @click="emit('toggle')">다크모드</button>
</template>

<script setup>
    import { defineEmits } from 'vue';

    /*
        부모로부터 물려받은 toggle이라는 이름의 이벤트를 호출할 수 있는 반환 받기
        (defineEmits를 활용해서 배열에 이벤트 이름을 작성)
    */

    const emit =defineEmits(['toggle']);    // 배열 구조 분해 할당
</script>

<style scoped>

</style>