const productForm = document.forms.namedItem('product');

const mainImage = document.querySelector('.main-image-container > img');
const subImageButtons = document.querySelectorAll('.sub-image-container > button');
const mainImageSrc = mainImage.src;

const productOptionSelect = document.getElementById('product-option-select');
const productOptionUl = document.getElementById('product-option-ul');

const buyButton = document.querySelector('.buy-btn');
const cartButton = document.querySelector('.cart-btn');
const heartButton = document.querySelector('.heart-btn');


/// 상품 이미지 변경하기
subImageButtons.forEach(subImageButton => {
    subImageButton.onmouseenter = () => {
        const imageTag = subImageButton.querySelector('img');
        mainImage.src = imageTag.src;
    }
    subImageButton.onmouseleave = () => {
        mainImage.src = mainImageSrc;
    }
});

console.log(cartButton);
////// 상품을 카트에 추가하기
cartButton.onclick = () => {
    const csrfToken = document.querySelector('meta[name=_csrf]').getAttribute('content');

    // 추가된 옵션이 있다면 추가된 옵션들의 id를 배열로 수집한다
    const productOptions = productOptionUl.getElementsByTagName('li');
    const options = [...productOptions].map(productOption => {
        const no = productOption.id;
        const amount = +productOption.querySelector('input').value;
        return {
            no: no,
            amount: amount
        };
    });

    const requestBody = {
        no: productForm.id,
        options: options
    }
    // 장바구니 담기 POST 요청 전송
    fetch(`/cart`, {
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
        else if(response.ok){
            if(confirm('장바구니에 상품이 담겼습니다. 장바구니로 이동하시겠습니까?')){
                location.href = '/user/cart';
            }
        }
    })
}















