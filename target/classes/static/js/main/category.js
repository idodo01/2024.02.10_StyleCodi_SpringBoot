// 찜 추가
function toggleIcon(button) {
    // 하트 클릭시 아이콘 바뀜
    const icon = button.querySelector('i');

    if (icon.classList.contains('bi-heart-fill')) {
        icon.classList.remove('bi-heart-fill');
        icon.classList.add('bi-heart');
    } else {
        icon.classList.remove('bi-heart');
        icon.classList.add('bi-heart-fill');
    }
    
    
    // 찜 목록에 추가하기
    const cartObject = create_dibs_object();
    request('/style-dibs', cartObject.product).then(() => {
        if(confirm('찜목록에 상품이 추가되었습니다. 확인하시겠습니까?')){
            location.href = '/style-dibs';
        }
    });
    
}


// 찜 객체 생성
function create_dibs_object(){
    // // DibsDTO 형태
    // return {
    //     product:{
    //         no: productForm.id,
    //         options: options
    //     },
    //     amount: 1
    // }
}
