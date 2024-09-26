<template>
    <h1>RootRouter</h1>
    <RouterView/>           <!-- NestedHome이나 NestedView가 Component로 붙음 -->
</template>

<script setup>
    import {RouterView} from 'vue-router';
</script>