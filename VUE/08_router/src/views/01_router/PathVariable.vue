<template>
    <div>
        <h1>PathVariable</h1>
        <h2>넘어온 pathvariable 값(id) = {{ currentRoute.params.id }}</h2>
    </div>
</template>

<script setup>
    import {useRoute} from 'vue-router';

    const currentRoute = useRoute();
</script>