/* 다양한 사용법 */
let shirts = {
    productName: '베이직 셔츠'
};

/* 기본값 설정 및 다른 이름의 변수에 담기 */
let {productName: productName2 = '어떤 상품',   // 1. 다른 이름의 변수에 담기, 2.defualt값 설정 
    color: color2 = "어떤 색상",
    price: price2 = 0} = shirts;

console.log('productName2',productName2);
console.log('color2',color2);                
console.log('price2',price2);                

let pants = {
    productName: '배기팬츠',
    color: '흰색',
    price: 25000
};

let {productName, ...rest} = pants;
console.log(`productName: ${productName}`);
console.log(`rest: ${rest}`);
console.log(`color: ${rest.color}`);          // rest가 나머지를 객체로 받아냄으로 이와 같은 방식 사용
console.log(`price: ${rest.price}`);