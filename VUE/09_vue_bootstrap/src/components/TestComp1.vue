<template>
    <div class="container mt-4">
        <b-card title="BootstrapVue 컴포넌트 예제" class="mb-4">
            <b-badge variant="primary" class="me-2">Primary</b-badge>
            <b-badge variant="secondary" class="me-2">Secondary</b-badge>
            <b-badge variant="success" class="me-2">Success</b-badge>
            <b-badge variant="danger" class="me-2">Danger</b-badge>
            <b-badge variant="warning" class="me-2">Warning</b-badge>
            <b-badge variant="info" class="me-2">Info</b-badge>
            <b-badge variant="light" class="me-2">Light</b-badge>
            <b-badge variant="dark">Dark</b-badge>
        </b-card>

        <b-card title="인터랙티브 컴포넌트" class="mb-4">
            <b-button variant="warning" @click="toggleBadge">배지 토글</b-button>

            <div class="mt-3" v-if="badgeVisible">
                <b-badge variant="info">추가된 배지</b-badge>
            </div>
        </b-card>

        <b-card title="입력 폼" class="mb-4">
            <b-form @submit.prevent="handleSubmit">
                <b-form-group label="이름" label-for="nameInput">
                    <b-form-input
                        id="nameInput"
                        v-model="form.name"
                        required
                        placeholder="이름을 입력하시오"
                    ></b-form-input>
                </b-form-group>

                <b-form-group label="이메일" label-for="emailInput">
                    <b-form-input
                        id="emailInput"
                        type="email"
                        v-model="form.email"
                        required
                        placeholder="이메일을 입력하시오"
                    ></b-form-input>
                </b-form-group>

                <b-button type="submit" variant="primary">제출</b-button>
            </b-form>
        </b-card>
    </div>
</template>

<script setup>
    import { ref } from 'vue';

    /* 반응형 변수들 */
    const badgeVisible = ref(true);
    const form = ref({
        name: '',
        email: ''
    });
    const formSubmitted = ref(false);

    /* 메소드들 */
    const toggleBadge = () => {
        badgeVisible.value = !badgeVisible.value;
    };

    const handleSubmit = () => {

        /* 여기서 폼 데이터를 처리 가능 */
        formSubmitted.value = true;
        /* 폼 초기화 */
        form.value.name = '';
        form.value.email = '';
    };
</script>

<style scoped>
    .container {
        max-width: 800px;
    }
</style>