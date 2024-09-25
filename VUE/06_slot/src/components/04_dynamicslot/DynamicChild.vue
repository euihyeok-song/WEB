<template>
    <div class="container">
        <div class="north">     <!-- 사용자가 입력한 이름이 "north"이면 Parent로 부터 north가 넘어오고 그 경우를 담당 -->
            <slot name="north"></slot>
        </div>
        <div class="west">
            <slot name="west"></slot>
        </div>
        <div class="east">
            <slot name="east"></slot>
        </div>
        <div class="south">
            <slot name="south"></slot>
        </div>
    </div>
</template>

<script setup>

</script>

<style scoped>
    .container {
    display: grid;
    grid-template-columns: 1fr 1fr;
    grid-template-rows: 1fr 1fr;
    gap: 10px;
    }

    .north {
    grid-column: 1 / span 2;
    height: 200px;
    background-color: #ff9999;
    }

    .south {
        grid-column: 1 / span 2;
        height: 200px;
        background-color: #99ccff;
    }

    .east {
        width: 200px;
        height: 200px;
        background-color: #99ff99;
    }

    .west {
        width: 200px;
        height: 200px;
        background-color: #ffcc66;
    }
</style>