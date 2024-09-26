<template>
    <div>
        <h1>QueryString</h1>
        <h2>넘어온 query string 값 = {{ currentRoute.query }}</h2>   <!-- QueryString은 query로 값 추출 -->
        <h2>name: {{ name }}</h2>
        <h2>age: {{ currentRoute.query.age }}</h2>
    </div>
</template>

<script setup>
    import {useRoute} from 'vue-router';

    const currentRoute = useRoute();
    const name = currentRoute.query.name;
</script>