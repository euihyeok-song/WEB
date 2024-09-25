<template>
    <div class="parentContainer">
        <h1>Parent</h1>
        <Child>
            {{ slotData }}    <!-- slotData를 SlotChild에 넘기고 이 값을 채운 child의 h1 태그부분이 넘어와서 적용됨 -->
        </Child>
    </div>  
</template>

<script setup>
    import Child from "./SlotChild.vue";
    import {ref} from 'vue';

    const slotData = ref('hello data');     // slot: 위 template의 slotData로 데이터를 태그로 만들어서 넘겨줌
</script>

<style scoped>
    .parentContainer {
        display:flex;
        align-items: center;       
        flex-direction: column;
        border: 1px solid black;
    }
</style>