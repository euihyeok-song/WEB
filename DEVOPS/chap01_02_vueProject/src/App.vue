<template>
  <div class="plus">
    <h1>덧셈 기능 만들기</h1>
    <label>num1: </label><input type="text" v-model="num1">&nbsp;
    <label>num2: </label><input type="text" v-model="num2">&nbsp;
    <button @click="sendPlus">더하기</button>
    <hr>
    <p>`{{ num1 }} + {{ num2 }} = {{ result }}`</p>
  </div>
</template>

<script setup>
  import { ref } from 'vue';

  const num1 = ref(0);
  const num2 = ref(0);
  const result = ref(0);

  // 비동기 통신으로 backend와 통신
  const sendPlus = async() => {

    // cors 해결(backend)
    // const response = await fetch(`http://localhost:7777/plus?num1=${num1.value}&num2=${num2.value}`);     // response에는 num1, num2, sum이 넘어옴

    // cors 해결(frontend)  - http://localhost:5173/api가 vite.config.js에 의해서 http://localhost:7777로 변환됨 (+ /api는 rewrite()를 통해서 삭제 필요)
    // const response =  await fetch(`http://localhost:5173/api/plus?num1=${num1.value}&num2=${num2.value}`);

    // 8055 docker container(8055:7777로 된 백엔드 서버 컨테이너)로 요청 시(백엔드에서 CORS 처리를 했을 경우)
    // const response = await fetch(`http://localhost:8055/plus?num1=${num1.value}&num2=${num2.value}`);

    // 프론트에서 CORS 처리를 하고자 하는 경우(프론트 프로젝트가 컨테이너가 아닌 상태)
    // const response =  await fetch(`http://localhost:5173/api/plus?num1=${num1.value}&num2=${num2.value}`);

    // 프론트에서 CORS 처리를 하고자 하는 경우(프론트 프로젝트가 컨테이너인 상태)
    // const response =  await fetch(`http://localhost:8011/api/plus?num1=${num1.value}&num2=${num2.value}`); 
    
    // k8s를 워커노드들로 띄워지고 난 이후(백엔드: 30001, 프론트엔드:30000) - Ingress 적용 이전 30001번의 백엔드 워커노드와 통신
    // const response =  await fetch(`http://localhost:30001/plus?num1=${num1.value}&num2=${num2.value}`);  

    // Ingress를 활용한 절대 경로로 통신
    const response = await fetch(`/boot/plus?num1=${num1.value}&num2=${num2.value}`);

    const data = await response.json();
    result.value = data.sum;
  }
</script>

<style scoped>
  .plus{
    text-align: center;
  }
</style>