/* 메소드 단축 */

var pika = {
    name: '피카츄',
    eat: function(food) {
        console.log(`${food}를 먹는다.`);
    }
};

pika.eat('백만볼트');

/* 메소드를 정의할 때는 콜론(:) 및 function 키워드를 생력할 수 있다. */
var pika2 = {
    name: '피카츄',
    eat(food) {                 // 메소드로 단축되었지만 프로퍼티로 취급된다.
        console.log(`${food}를 먹는다.2`);
    }
};

pika2.eat('백만볼트');