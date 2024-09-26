<template>
    <div class="container mt-4">
      <!-- 모달 예제 -->
      <b-card title="모달 예제" class="mb-4">
        <b-button variant="primary" @click="showModal">모달 열기</b-button>
  
        <b-modal
          id="example-modal"
          title="BootstrapVue 모달"
          v-model="isModalVisible"
        >
          <p class="my-4">이것은 모달 창으로 필요한 정보를 여기에 표시하시요.</p>
          <template #modal-footer="{ ok, cancel }">
            <b-button variant="secondary" @click="cancel">취소</b-button>
            <b-button variant="primary" @click="ok">확인</b-button>
          </template>
        </b-modal>
      </b-card>
  
      <!-- 드롭다운 예제 -->
      <b-card title="드롭다운 예제" class="mb-4">
        <b-dropdown id="dropdown-1" text="드롭다운 메뉴" variant="success">
          <b-dropdown-item href="#">옵션 1</b-dropdown-item>
          <b-dropdown-item href="#">옵션 2</b-dropdown-item>
          <b-dropdown-item href="#">옵션 3</b-dropdown-item>
        </b-dropdown>
      </b-card>
  
      <!-- 테이블 예제 -->
      <b-card title="테이블 예제" class="mb-4">
        <b-table :items="items" :fields="fields" striped hover></b-table>
      </b-card>
    </div>
  </template>
  
  <script setup>
    import { ref } from 'vue';
  
    // 모달 상태 관리
    const isModalVisible = ref(false);
  
    const showModal = () => {
      isModalVisible.value = true;
    };
  
    // 테이블 데이터
    const items = [
      { name: '홍길동', email: 'hong@swcamp.com' },
      { name: '신사임당', email: 'shin@swcamp.com' },
      { name: '이순신', email: 'lee@swcamp.com' },
    ];
  
    const fields = [
      { key: 'name', label: '이름' },
      { key: 'email', label: '이메일' },
    ];
  </script>
  
  <style scoped>
    .container {
      max-width: 800px;
    }
  </style>