
const foodList = ['물회', '삼계탕', '냉면', '수박', '물회']

/* 동일한 것이 있으면 좌측 요소 우선 */
console.log(`foodList.indexOf('물회'): ${foodList.indexOf('물회')}`);
/* 요소가 없으면 -1 */
console.log(`foodList.indexOf('삼겹살'): ${foodList.indexOf('삼겹살')}`);


console.log(`foodList.indexOf('물회'): ${foodList.includes('물회')}`);
console.log(`foodList.indexOf('삼겹살'): ${foodList.includes('삼겹살')}`);


const chineseFood = ['짜장면', '짬뽕', '탕수육'];

/* push는 append와 동일한 기능(마지막 요소로 추가) */
chineseFood.push('팔보채');
chineseFood.push('양장피');

console.log(`push 후: ${chineseFood}`);

/* 마지막 요소를 제거 */
console.log(`chineseFood.pop(): ${chineseFood.pop()}`);
console.log(`chineseFood.pop(): ${chineseFood.pop()}`);
console.log(`pop 후: ${chineseFood}`);

const chickenList = ['양념치킨', '후라이드', '파닭'];

/* 처음 요소를 추가 */
console.log(`chickenList.unshift(): ${chickenList.unshift('간장치킨')}`);
console.log(`chickenList.unshift(): ${chickenList.unshift('마늘치킨')}`);
console.log(`unshift 후: ${chickenList}`);

/* 처음 요소를 제거 */
console.log(`chickenList.shift(): ${chickenList.shift()}`);
console.log(`shift 후: ${chickenList}`);

/* concat */
const idol1 = ['서태지와 아이돌', '소녀시대']
const idol2 = ['HOT', '젝스키스']
const idol3 = ['핑클','블랙핑크']

console.log(`idol1 기준으로 idol2 배열을 concat: ${idol1.concat(idol2)}`);

/* ES6에서는 스프레드 연산자를 활용해서 concat의 개념을 할 수도 있다. (이후 배울 내용) */
console.log(`idol1 기준으로 idol2 배열을 concat: ${[...idol1,...idol2]}`);          // concat말고 spread 연산자를 써도 가능 (ES6)

console.log(`idol3 기준으로 idol1, idol2 배열을 concat: ${idol3.concat(idol1, idol2)}`);


/* slice(선택한 요소 복사) / splice(선택한 인덱스 위치 요소 제거 및 추가) */
const front = ['HTML','CSS', 'JavaScript', 'Vue'];

console.log(`front.slice(): ${front.slice(1,3)}`);          // 1부터 2번 인덱스 까지 복사 후 붙여넣기 (원본 영향 X)
console.log(`front: ${front}`);

console.log(`front.slice(3, 1, "JDBC"): ${front.slice(3, 1, "JDBC")}`);     // 3번 index부터 길이가 1인 것(Vue)을 제거하고, "JDBC" 넣음
console.log(`front: ${front}`);

/* join(배열을 우리가 원하는 구분자와 함께 결합하여 문자열로 반환) */
const snackList = ['사탕', '초콜렛', '껌', '마이쮸'];
console.log(`snackList.join(): ${snakeList.join()}`);
console.log(`snackList.join('/'): ${snackList.join('/')}`);
