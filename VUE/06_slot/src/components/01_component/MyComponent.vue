<template>
    <div>
        <h1>MyComponent</h1>
    </div>
</template>

<!-- <script> -->
<script setup>      // setup을 적으면 자동으로 export해서 App에 전달해줌
    import { onMounted } from 'vue';

    // export default {
    //     setup() {
            onMounted(async() => {
                console.log("Hello Vue!!");
            })
    //     }
    // }
</script>

<!-- 정의된 영역만 css를 적용시킴 -->
<style scoped>  
    h1 {
        color: red;
    }
</style>