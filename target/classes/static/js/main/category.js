

// 찜 추가
function toggleIcon(button) {
    
    const productForm = button.closest('form'); // 현재 버튼이 속한 form 찾기
    const csrfToken = document.querySelector('meta[name=_csrf]').getAttribute('content');


    // 하트 클릭시 아이콘 바뀜
    const icon = button.querySelector('i');

    if (icon.classList.contains('bi-heart')) { // 찜이 아닌 상태에서 찜 (빈하트 - > 풀하트)
        // icon.classList.remove('bi-heart');
        // icon.classList.add('bi-heart-fill');

        // 찜 추가
        const requestBody = {
            no: productForm.id,
        }

        // 찜 POST 요청 전송
        fetch(`/loves-post`, {
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
                // 요청 성공 시 페이지 리로드
                window.location.reload();

                if(confirm('상품이 찜목록에 담겼습니다. 찜목록으로 이동하시겠습니까?')){
                    location.href = '/user/myLove';
                }
            }

        })
    } else { // 찜인 상태에서 찜 해제 (풀하트 - > 빈하트)
        // icon.classList.remove('bi-heart-fill');
        // icon.classList.add('bi-heart');
        
        // 찜 해제
        const requestBody = {
            no: productForm.id,
        }

        fetch('/loves-delete', {
            method: "DELETE",
            headers: {
                "X-CSRF-TOKEN": csrfToken,
                "Content-Type": "application/json"
            },
            body: JSON.stringify(requestBody)
        }).then(response => {
            if (response.ok) {
                // 요청 성공 시 페이지 리로드
                window.location.reload();
            }
            // switch (response.status){
            //     case 200:
            //         alert('삭제 완료!');
            //         break;
            //     case 404:
            //         alert('해당 시리얼은 존재하지 않음!');
            //         break;
            //     default: // 아무것도 입력하지 않음
            //         throw Error("응답 에러 발생");
            // }
        })

    }

   

    
}
