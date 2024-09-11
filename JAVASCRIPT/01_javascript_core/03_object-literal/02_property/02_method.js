var dog = {
    name: '뽀삐',
    eat: function(food){
        console.log(`${this.name}(은)는 ${food}를 맛있게 먹어요.`);
    }
};

/* 자바스크립트의 함수는 객체이다. 함수는 값으로 취급할 수도 있고 프로퍼티 값으로도 사용할 수 있다 */
console.log(dog.eat);
console.log(dog.eat('고구마'));         // 객체에 값을 전달하는 역할만 하고, 반환이 없음으로 반환이 undefined로 나옴