
<!--
    slot은 부모가 자식에게 태그형태로 물려줄 것이 있는 경우 사용
    (물려줄 것이 여러개면 name=[](#[])을 달아서 넘겨줌)
-->

<template>
    <div class="container">
        <h1>NamedSlot</h1>
        <NamedChild>
            <template v-slot:name>      <!-- slot = h2태그에 해당하는 내용을 name이라는 이름으로 보낸다 -->
                <h2>
                    name: {{ obj.name }}
                </h2>
            </template>
            <template v-slot:founder>      
                <p>
                    founder: {{  obj.founder }}
                </p>
            </template>
            <template v-slot:img>      
                <img :src="obj.img">
            </template>
        </NamedChild>
    </div>
</template>

<script setup>
    import NamedChild from '@/components/03_namedslot/NamedChild.vue';
    import {reactive} from 'vue';  

    const obj = reactive({
        name: "vue",
        founder:"Evan You",
        img: "https://images.ctfassets.net/s5uo95nf6njh/2fEr6ctL9FxPtOPhUcM4FA/773007e573bfa16aebdb736f767b45fa/evan-you-hero.jpg"
    })
</script>

<style scoped>
    .container {
        display: flex;
        flex-direction: column;
        align-items: center;
        border: 1px solid black;
    }
    image{
        width: 200px;
        height: 200px;
    }
    
</style>p