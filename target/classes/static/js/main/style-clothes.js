function changeParentImage(event) {

    const category = event.target.dataset.category.toString();
    // console.log(category+"출력");

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
    /*****************************************************************/

    const bClassName = `${part}-productNo`; // 동적 클래스 이름

    const parentB = parent.document.querySelector(`.${bClassName}`);

    // 부모 문서인 style-make의 ${part}-productNo 클래스의 위치의 id에 삽입
    parentB.id = event.target.getAttribute('alt');

}
