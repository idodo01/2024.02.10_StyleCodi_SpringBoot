function changeParentImage(event) {
    const category = event.target.dataset.category;
    let part;

    switch (category) {
        case '2':
            part = "bag";
            break;
        case '3':
            part = "shoes";
            break;
        case '4':
            part = "bottom";
            break;
        case '5':
            part = "top";
            break;
        case '6':
            part = "outer";
            break;

    }
    const imgClassName = `${part}-img`; // 동적 클래스 이름

    const parentImg = parent.document.querySelector(`.${imgClassName}`);

    // 부모 페이지의 이미지를 변경
    parentImg.src = event.target.getAttribute('src');

    /*****************************************************************/

    const bClassName = `${part}-productNo`; // 동적 클래스 이름

    const parentB = parent.document.querySelector(`.${bClassName}`);

    parentB.id = event.target.getAttribute('alt');
    // ProductNo.innerHTML += event.target.getAttribute('alt');


    console.log('받은 상품 번호:', event.target.getAttribute('alt'));
    const productNo = event.target.getAttribute('alt');

    const csrfToken = document.querySelector('meta[name=_csrf]').getAttribute('content');

    // 서버에 상품 번호 전달
    fetch('/add-list', {
        method: 'POST',
        headers: {
            "X-CSRF-TOKEN": csrfToken,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(
            {productNo: productNo}
        )
    })
        .then((response) => {
            if (response.ok) {
                console.log('상품 번호 서버에 전달 완료');
                const styleListIframe = parent.document.getElementById('style-list-iframe');
                // style-list iframe을 새로고침
                styleListIframe.contentWindow.location.reload();

                // window.location.href = '/style-list';
                // location.reload(); // 페이지 새로고침으로 타임리프 갱신
            } else {
                console.error('상품 번호 전달 실패');
            }
        })
        .catch((error) => console.error('서버 통신 오류:', error));

}