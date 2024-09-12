
/* 
    UTC(협정 세계시): 국제 표준시로 기술적 표기에 사용된다. (영국 런던)
    GMT(그리니치 평균시): UTC와 소수점 단위 정도의 초가 차이나지만, 일상 생활에는 혼용
    KST(한국 표준시): UTC + 9시간
*/

/* 1. new Date() - new를 붙이고 안붙이고는 차이가 존재 */
console.log(new Date());        // UTC 상으로 1970년 1월 1일 0시 기준
console.log(Date());            // KST 기준

console.log(new Date(0));
console.log(new Date(24 * 60 * 60 * 1000));

/* 
    연, 월, 일, 시, 분, 초, 밀리초를 의미하는 숫자들을 원하는 만큼 인수로 전달하며 
    UTC기준 Date 객체 반환(9시간 전)
    
    month(0~11)
*/
console.log(new Date(2022, 1));             // UTC 상으로 한국 시간 2022년 2월 1일은 2022년 1월 31일 오후 3시(9시간 전)
console.log(new Date(2022, 1, 1, 9, 0, 0, 0)); 




