
let exampleProduct = {
    items: ["Coffee", "Donut"],
    producer: "신사임당"
};

/* 함수 호출 시 객체를 인수로 넘겨줄 때 매개변수에서부터 객체 구조분해 할당이 가능하다. */
function displayProduct({producer = "아무개", items = [], width = 10, height = 20}) {
    console.log(producer);
    console.log(items);
    console.log(width);
    console.log(height);
}

displayProduct(exampleProduct);