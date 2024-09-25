<template>
    <h1>{{  props.readValue }}</h1>
    <!-- Vue는 자식의 값 변경이 부모의 값에 영향을 주지 않도록 막아버림(readOnly) => 해결법: 2번처럼 event를 물려주는 방식 사용해야 함 -->
    자식의 readValue: <input type="text" v-model="props.readValue">
</template>

<script setup>
    import { defineProps } from 'vue';

    const props = defineProps({     // props는 {'readValue':'Vue는 재밌다'}값이 저장
        readValue: String
    });
</script>

<style scoped>

</style> 