const orderLies = document.querySelectorAll('.order-list-section li');
const orderDetailContainer = document.querySelector('.order-detail-container');
const orderDetailTbody = orderDetailContainer.querySelector('tbody');
const closeBtn = document.getElementById('order-detail-close-btn');

orderLies.forEach(orderLi => {
    const orderDetailButton = orderLi.querySelector('.order-detail-btn');
    const orderReviewButton = orderLi.querySelector('.order-review-btn');
    const orderATag = orderLi.querySelector('.order-number a');
    const orderId = orderATag.textContent;
    orderATag.onclick = event => {
        event.preventDefault();
        clicked_order_item(orderId);
    }
    // 상세정보 보기 버튼 클릭 시
    orderDetailButton.addEventListener('click', () => {
        clicked_order_item(orderId);
    });
});

function clicked_order_item(orderId){
    fetch(`/order/${orderId}`)
        .then(response => {
            // 하나의 주문 정보를 잘 가져왔음
            if(response.ok && response.status === 200){
                return response.json();
            }
            throw new Error();
        }).then(order => {
            create_order_details(order);
            orderDetailContainer.style.display = 'block';
        });
}

closeBtn.onclick = () => {
    orderDetailContainer.style.display = 'none';
    orderDetailTbody.innerHTML = ``;
}

// 주문했던 목록 생성하기
function create_order_details(order){
    orderDetailTbody.innerHTML = ``;
    const cartList = order.carts;
    for(let i = 0; i < cartList.length; i++){
        const cart = cartList[i];
        const cartAmount = cart.amount;
        const product = cart.product;
        const productName = product.name;
        const productPrice = product.price;
        const options = product.options;
        const image = product.images[0];
        let optionHTML = '';
        for(const option of options){
            optionHTML += `<div class="sub-title">${option.name}(+${option.price}원)</div>`;
        }

        orderDetailTbody.insertAdjacentHTML(`beforeend`,
            `<tr>
                <td class="product-no">${i+1}</td>
                <td class="product-img">
                    <img src="/image/${image.no}" alt="#">
                </td>
                <td class="product-summary">
                    <div class="title">${productName}</div>
                    ${optionHTML}
                </td>
                <td class="product-price">${productPrice}원</td>
                <td class="product-amount">${cartAmount}</td>
                ${i === 0 ? `<td id="product-total-price" rowspan="100">${order.totalPrice}</td>` : ''}
            </tr>`
        );
    }
}















