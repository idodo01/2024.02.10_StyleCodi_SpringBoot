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



async function combineImages() {
    const canvas = document.getElementById("canvas");
    const ctx = canvas.getContext("2d");

    // Clear the canvas
    ctx.clearRect(0, 0, canvas.width, canvas.height);

    // Define dimensions for each part
    const dimensions = {
        top: {x: 0, y: 0, width: 200, height: 200},
        outer: {x: 200, y: 0, width: 200, height: 400},
        bottom: {x: 0, y: 200, width: 200, height: 200},
        shoes: {x: 0, y: 400, width: 200, height: 200},
        bag: {x: 200, y: 400, width: 200, height: 200}
    };

    for (const part of parts) {
        const box = document.getElementById(part);
        const img = box.querySelector("img");
        if (img) {
            const {x, y, width, height} = dimensions[part];
            const image = new Image();
            image.src = img.src;
            await new Promise((resolve) => {
                image.onload = () => {
                    ctx.drawImage(image, x, y, width, height);
                    resolve();
                };
            });
        } else {
            // 이미지가 없을 경우, 빈 하얀 사각형을 캔버스에 그리기
            const {x, y, width, height} = dimensions[part];
            ctx.fillStyle = "#fff"; // 하얀색으로 설정
            ctx.fillRect(x, y, width, height);
        }
    }

    // Create a download link
    const link = document.createElement("a");
    link.download = "combined-image.png";
    link.href = canvas.toDataURL("image/png");
    link.click();
}



function saveClickList() {

    const topProductNo = Number(document.querySelector(`.top-productNo`).id);
    const outerProductNo = Number(document.querySelector(`.outer-productNo`).id);
    const bottomProductNo = Number(document.querySelector(`.bottom-productNo`).id);
    const shoesProductNo = Number(document.querySelector(`.shoes-productNo`).id);
    const bagProductNo = Number(document.querySelector(`.bag-productNo`).id);

    // console.log(topProductNo,outerProductNo,bottomProductNo,shoesProductNo,bagProductNo);
    // console.log(typeof topProductNo);
    // console.log(typeof (Number(topProductNo)));

    const csrfToken = document.querySelector('meta[name=_csrf]').getAttribute('content');

    const requestBody = [
        { no: topProductNo },
        { no: outerProductNo },
        { no: bottomProductNo },
        { no: shoesProductNo },
        { no: bagProductNo },
        // no: outerProductNo,
        // bottomProductNo: bottomProductNo,
        // shoesProductNo: shoesProductNo,
        // bagProductNo: bagProductNo
    ]

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

    //
    // 세션 처리 방법
    // fetch('/save-click-list', {
    //         method: 'POST',
    //         headers: {
    //             "X-CSRF-TOKEN": csrfToken,
    //             'Content-Type': 'application/json'
    //         },
    //         body: JSON.stringify(
    //             {productNo: productNo}
    //         )
    //     })
    //         .then((response) => {
    //             if (response.ok) {
    //                 console.log('상품 번호 서버에 전달 완료');
    //                 const styleListIframe = parent.document.getElementById('style-list-iframe');
    //                 // style-list iframe을 새로고침
    //                 styleListIframe.contentWindow.location.reload();
    //
    //                 // window.location.href = '/style-list';
    //                 // location.reload(); // 페이지 새로고침으로 타임리프 갱신
    //             } else {
    //                 console.error('상품 번호 전달 실패');
    //             }
    //         })
    //         .catch((error) => console.error('서버 통신 오류:', error));
}
