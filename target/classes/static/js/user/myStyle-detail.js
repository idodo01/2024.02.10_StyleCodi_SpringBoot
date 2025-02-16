
function uploadList() {
    const topProductNo = Number(document.querySelector(`.top`).id);
    const outerProductNo = Number(document.querySelector(`.outer`).id);
    const bottomProductNo = Number(document.querySelector(`.bottom`).id);
    const shoesProductNo = Number(document.querySelector(`.shoes`).id);
    const bagProductNo = Number(document.querySelector(`.bag`).id);

    const csrfToken = document.querySelector('meta[name=_csrf]').getAttribute('content');

    const requestBody = [
        { no: topProductNo },
        { no: outerProductNo },
        { no: bottomProductNo },
        { no: shoesProductNo },
        { no: bagProductNo }
    ];

    // POST 요청 전송
    fetch(`/style-list-stylecodi`, {
        method: "POST",
        headers: {
            "X-CSRF-TOKEN": csrfToken,
            "Content-Type": "application/json"
        },
        body: JSON.stringify(requestBody)
    }).then(response => {
        // 로그인이 안된 유저가 클릭 시
        if(response.status === 401){
            alert('로그인을 먼저 해주세요');
        }
        else if (response.ok) {
            if (confirm('업로드 완료! 확인하시겠습니까?')) {
                window.location.href = '/styleCategory'; // 페이지 이동
            }
        }
    }).catch((error) => console.error('서버 통신 오류:', error));
}