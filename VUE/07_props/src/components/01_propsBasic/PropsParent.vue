<template>
    <div :class="{container:true, dark: darkState}">    <!-- darkState가 true이면 .dark(css)를 적용시킴 -->
        
        <!-- v-model에 의해 양방향 바인딩으로 부모가 수정되면 자식도 동일하게 수정 => props는 자식의 수정사항이 부모에 영향 미치지 X(read-only) -->
        props 입력: <input type="text" v-model="message">   
        <!-- 자식에게 Props로 값을 전달하는 방식(이름(:message)은 되도록 반응형 변수의 이름과 동일하게 설정) -->
        <PropsChild :message="message"/>        <!-- message라는 이름으로 PropsChild에게 message 값을 넣어서 넘겨줌 -->


        <!-- 부모가 자식에게 사용자 정의 event를 물려줄 수 있음(props는 X) => 자식이 event의 이름을 호출하면 부모의 값이 변경됨 -->
        <DarkMode @toggle="toggleDarkMode"/>

        <!-- 자식 컴포넌트에서 직접 props 수정해 보기 (권장 X) -->
        부모의 readValue: <input type="text" v-model="readValue">
        <ReadProps :readValue="readValue"/>
    </div>
</template>

<script setup>
    import { ref } from 'vue';
    import PropsChild from './PropsChild.vue';
    import DarkMode from './DarkMode.vue';
    import ReadProps from './ReadProps.vue';
    
    const message = ref('hello');
    const darkState = ref(false);
    const readValue = ref('Vue는 재밌다.');

    function toggleDarkMode() {
        darkState.value = !darkState.value;
    }
</script>

<style scoped>
    .container{
        border: 1px solid;
        display: flex;
        flex-direction: column;
        align-items: center;
    }
    .dark{
        background-color: black;
        color: white;
    }
</style>