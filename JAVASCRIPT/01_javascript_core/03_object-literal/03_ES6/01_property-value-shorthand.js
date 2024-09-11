/* 프로퍼티 값 단축 */

var id = 'p-0001';
var price = 30000;

var product  = {
    id: id,
    price: price
};
console.log(product);

/*
    ES6에서 프로퍼티 값으로 변수를 사용할 경우
    변수 이름과 프로퍼티 키가 동일한 이름으로 작성되게 하고
    싶을 때 프로퍼티 키를 생략할 수 있다.
    프로퍼티 키는 변수 이름과 일치되게 자동으로 생성된다. 
*/
var product2 = {id, price};
console.log(product2)