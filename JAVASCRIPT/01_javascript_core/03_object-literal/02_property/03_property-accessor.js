var dog = {
    name: '뽀삐',
    eat: function(food){
        console.log(`${this.name}(은)는 ${food}를 맛있게 먹어요.`);
    }
};

/* 1. 마침표 표기법(dot notation) */
console.log(dog.name);
dog.eat('감자');

/* 2. 대괄호 표기법(square braket notation) */
console.log(dog['name']);           // 홀따옴표(')를 붙이지 않으면 변수로 판단한다.
dog['eat']('고구마');

/* 대괄호 표기법만 가능한 경우 */
var obj = {
    'dash-key': 'dash-vaule',
    0: 1
};

/* 일부 특수기호(_,$)를 제외하고는 마침표 표기법을 사용할 수 없다. */
// console.log(obj.dash-key);          
// console.log(obj.'dash-key');
// console.log(obj[dash-key]);

console.log(obj['dash-key']);

/* 숫자로 된 프로퍼티는 역시나 마침표 표기법을 사용할 수 없다. */
// console.log(obj.0);
// console.log(obj.'0');

console.log(obj['0']);
console.log(obj[0]);