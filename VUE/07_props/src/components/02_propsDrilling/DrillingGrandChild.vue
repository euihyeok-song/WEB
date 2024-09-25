<template>
    <h3>GrandChild: {{ injectCount }}</h3>
    <!-- 원래는 이렇게 click이 이뤄지면 부모의 것도 수정되지만 부모 provide의 readOnly에 의해 수정되지 X-->
    <button @click="injectCount++">GrandChild가 증가(Parent 것 수정)</button>
</template>

<script setup>
    import { inject } from 'vue';
    
    // inject를 통해 바로 Parent에게 절달됨
    const injectCount = inject('count');
</script>
    
<style scoped>

</style>