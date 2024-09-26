<template>
  <header>
    <h1>Router 사용하기</h1>
    <hr>
    <div class="wrapper">
      <!-- replace 속성은 브라우저의 히스토리 스택을 대체하여 뒤로가기를 방지한다. -->
      <!--
      <h2>basic router</h2>
      <nav>

        // client side에서 URL 경로로 Routing
        <RouterLink to="/" active-class="active" replace>Home</RouterLink>  // '/' 요청시에 매칭될 Componentfmf RouterView에 그려라
        <RouterLink to="/pathvariable/1" active-class="active" replace>PathVariable</RouterLink>  
        <RouterLink to="/querystring?name=홍길동&age=20" active-class="active" replace>QueryString</RouterLink>  
      </nav>
      -->
      <h2>nested router</h2>
      <nav>

        <!-- active는 클릭 이벤트 시에 동작하는 헨들러 메소드 -->
        <!-- replace 속성은 브라우저의 히스토리 스택을 대체하여 뒤로가기를 방지한다. -->
        <RouterLink to="/" active-class="active" replace>Home</RouterLink>  
        <RouterLink to="/nested/home" active-class="active" replace>중첩 home</RouterLink>  
        <RouterLink to="/nested/view" active-class="active" replace>중첩 view</RouterLink>  
      </nav>
    </div>
  </header>
  <main>
    <RouterView/>
  </main>
</template>

<!--
  npm install vue-router@next
-->

<script setup>
  import {RouterView, RouterLink} from 'vue-router';    // RouterView는 화면에 뿌려줄 Component, RouterLink는 메뉴 버튼 기능(클릭시 해당으로 이동)
</script>

<style scoped>
  nav {
    width: 100%;
    font-size: 12px;
    text-align: center;
    margin-top: 2rem;
    font-size: 30px;
  }

  a {
    margin-left: 10px;
  }

  .active {
    color: red;
  }

  main {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }

  header {
    text-align: center;
  }
</style>