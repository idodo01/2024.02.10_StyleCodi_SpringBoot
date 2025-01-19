function changeParentImage(event) {

    const category = event.target.dataset.category.toString();
    let part;

    switch (category) {
        case "2":
            part = "bag";
            break;
        case "3":
            part = "shoes";
            break;
        case "4":
            part = "bottom";
            break;
        case "5":
            part = "top";
            break;
        case "6":
            part = "outer";
            break;

    }

    const className = `${part}-img`; // 동적 클래스 이름

    const parentImg = parent.document.querySelector(`.${className}`);

    // 부모 페이지의 이미지를 변경
    parentImg.src = event.target.getAttribute('src');


}
