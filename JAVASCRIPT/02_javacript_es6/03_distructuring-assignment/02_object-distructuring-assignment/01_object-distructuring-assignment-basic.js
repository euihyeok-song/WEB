
/* 객체 구조분해 할당 기본 문법 */
let pants = {
    productName: '배기팬츠',
    color: '검정색',
    price: 30000,
    getInfo() {
        console.log(this.productName, '좋아!');
    }
};

// let productName = pants.productName;
// let color = pants.color;

/* 배열 구조분해 할당과 달리 프로퍼티 순서는 중요하지 않지만 프로퍼티명과 변수명은 같아야 한다. */
// let {productName, color, price} = pants;
let {color, getInfo, productName} = pants;        // 필요한 프로퍼티만 추출(순서 상관 X)

console.log(productName, color);
console.log(getInfo);
getInfo();                  // getInfo의 this는 함수를 호출하는 객체를 나타내는 데 여기선 없기 떄문에 'undefined 좋아!'로 결과값이 반환됨

/* 객체 구조분해 할당으로 꺼낸 변수 대신 다른 걸 쓰고 싶다면... */
let {color: co, price: pr, productName: pn} = pants;        // 담는 변수를 다른것 지정 가능
console.log(co, pr, pn);

