<template>
    <div class="parentContainer">
        <h1>DynamicSlot: </h1>
        <input type="text" v-model="dynamicName">
        <DynamicChild>
            <!-- <template name = [dynamicName]>   -->
            <template #[dynamicName]>   <!-- 사용자의 입력으로 변화될 반응형 변수의 이름 -->
                {{ dynamicName }}       <!-- 사용자의 입력으로 변화될 반응형 변수의 값 -->
            </template>
        </DynamicChild>
    </div>
</template>

<script setup>
    import DynamicChild from './DynamicChild.vue';
    import {ref} from 'vue';

    const dynamicName = ref('');

</script>

<style scoped>
    .parentContainer {
        display: flex;
        align-items: center;
        flex-direction: column;
        border: 1px solid black;
    }
</style>