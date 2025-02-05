const parts = ["top", "outer", "bottom", "shoes", "bag"];

// 미리보기에 사용된 상품 목록

/***********************스타일 맵***************************/
function uploadImage(part) {
    document.getElementById(`input-${part}`).click();
}

function previewImage(event, part) {
    const file = event.target.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function(e) {
            // 미리보기 사진 추가됨
            const box = document.getElementById(part);
            box.innerHTML = `<img class="${part}-img" src="${e.target.result}" alt="${part}"> 
                            <b class="${part}-productNo" id="${e.target.result}"></b>
                            <input type="file" accept="image/*" id="input-${part}" 
                            onChange="previewImage(event, '${part}')"
                            style="display: none;"/>`;
            box.classList.remove('empty'); // 이미지가 첨부되면 'empty' 클래스 제거
        };
        reader.readAsDataURL(file);
    }
}


function saveList() {
    const topProductNo = Number(document.querySelector(`.top-productNo`).id);
    const outerProductNo = Number(document.querySelector(`.outer-productNo`).id);
    const bottomProductNo = Number(document.querySelector(`.bottom-productNo`).id);
    const shoesProductNo = Number(document.querySelector(`.shoes-productNo`).id);
    const bagProductNo = Number(document.querySelector(`.bag-productNo`).id);

    const csrfToken = document.querySelector('meta[name=_csrf]').getAttribute('content');

    const requestBody = [
        { no: topProductNo },
        { no: outerProductNo },
        { no: bottomProductNo },
        { no: shoesProductNo },
        { no: bagProductNo }
    ];

    // POST 요청 전송
    fetch(`/style-list`, {
        method: "POST",
        headers: {
            "X-CSRF-TOKEN": csrfToken,
            "Content-Type": "application/json"
        },
        body: JSON.stringify(requestBody)
    }).then(response => {
        if (response.ok) {
            console.log('요청 성공');
        } else {
            console.error('요청 실패:', response.status);
        }
    })
        .catch((error) => console.error('서버 통신 오류:', error));
}

function uploadList() {
    const topProductNo = Number(document.querySelector(`.top-productNo`).id);
    const outerProductNo = Number(document.querySelector(`.outer-productNo`).id);
    const bottomProductNo = Number(document.querySelector(`.bottom-productNo`).id);
    const shoesProductNo = Number(document.querySelector(`.shoes-productNo`).id);
    const bagProductNo = Number(document.querySelector(`.bag-productNo`).id);

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
        if (response.ok) {
            console.log('요청 성공');
        } else {
            console.error('요청 실패:', response.status);
        }
    })
        .catch((error) => console.error('서버 통신 오류:', error));
}