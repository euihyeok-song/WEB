<!-- 단축키: vbase-3-setup -->

<!--
  template:
  Vue는 사용자의 화면에 보여지는 부분을 내보낼 때 다음과 같이 <template> 하위에 구성하여 적성한다.
  template은 다른 엘리먼트 요소를 감싸주는 역할을 수행하며 사용자의 화면에 렌더링될 때 제거된다.
  (하나의 컴포넌트가 화면에 어떻게 보여질지 컴포넌트 단위의 html이 작성된다고 볼 수 있다.)
-->
<template>
  <header>
    <p>Hello!</p>
    <MyComponent/>    <!-- 첫문자가 대문자인 파스칼표기법이자, 존재하지 않는 태그 => components에 들어가는 Vue -->
  </header>
  <main>
    <SlotParent/>
    <NamedParent/>
    <DynamicParent/>
  </main> 
</template>

<!-- 
  script setup:
  해당 컴포넌트에 적용할 setup 함수 안의 내용(반응형 변수/반응형 변수에 변화를 주는 함수/라이프 사이클 훅)을 작성하며
  View model의 개념을 작성하는 영역이다.
-->
<script setup>
  import MyComponent from './components/01_component/MyComponent.vue'  // 통신하려는 vue가 멀리 있으면 .(현재경로) 대신 @(src)를 통해서 경로 설정 가능
  import SlotParent from './components/02_slotintro/SlotParent.vue';
  import NamedParent from './components/03_namedslot/NamedParent.vue';
  import DynamicParent from './components/04_dynamicslot/DynamicParent.vue';
</script>

<!--
  style scoped:
  현재 컴포넌트에서만 적용되는 스타일을 작성한다.(범위 css(고유한 번호의 컴포넌트에만 적용 됨))
-->
<style scoped>

</style>t