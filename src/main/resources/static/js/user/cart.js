const carts = document.querySelectorAll('tbody tr');
const selectDeleteBtn = document.getElementById('select-delete-btn');
const orderSelectButton = document.getElementById('order-select-button');
const orderAllButton = document.getElementById('order-all-button');

// 선택 항목을 삭제하기 버튼
selectDeleteBtn.onclick = () => {
    // 선택 항목들을 가져오기
    carts.forEach(cart => {
        const checkBox = cart.querySelector('input[type=checkbox]');
        if(checkBox.checked){
            const cartId = cart.id;
            remove_cart(cartId);
        }
    });
}
// 선택 항목을 주문하기 버튼
// 모든 항목을 주문하기 버튼
orderAllButton.onclick = () => {
    const csrfToken = document.querySelector('meta[name=_csrf]').getAttribute('content');
    // const cartNumbers = [...carts].map(cart => cart.id);
    // const formData = new FormData();
    // formData.append('_csrf', csrfToken);
    // formData.append('cartNumbers', cartNumbers);
    // fetch('/user/order', {
    //     method: "POST",
    //     body: formData
    // }).then(r => r.text()).then(console.log);

    const form = document.createElement('form');
    const csrfInput = document.createElement('input');
    csrfInput.name = '_csrf';
    csrfInput.value = csrfToken;
    form.appendChild(csrfInput);
    document.body.appendChild(form);
    [...carts].forEach(cart => {
        const input = document.createElement('input');
        input.name = 'cartNumbers';
        input.value = cart.id;
        form.appendChild(input);
    });
    form.method = 'POST';
    form.action = '/user/order';
    form.submit();
}

/**********************************************************/
carts.forEach(cart => {
    const cartId = cart.id;
    const amountInput = cart.querySelector('.amount-change-label > input');
    console.log(cartId, amountInput)
    amountInput.onchange = () => {
        change_cart_amount(cartId, +amountInput.value);
    }
    cart.querySelector('.amount-plus-btn').onclick = () => {
        change_cart_amount(cartId, +amountInput.value + 1);
    }
    cart.querySelector('.amount-minus-btn').onclick = () => {
        change_cart_amount(cartId, +amountInput.value - 1);
    }
    cart.querySelector('.delete-btn').onclick = () => {
        remove_cart(cartId);
    }
});

function change_cart_amount(cartId, amount){
    const csrfToken = document.querySelector('meta[name=_csrf]').getAttribute('content');
    fetch(`/cart/${cartId}`, {
        method: "PATCH",
        headers: {
            "X-CSRF-TOKEN": csrfToken,
            "Content-Type": "application/json"
        },
        body: amount
    }).then(response => {
        if(response.ok){
            location.reload();
        }
    });
}

function remove_cart(cartId){
    const csrfToken = document.querySelector('meta[name=_csrf]').getAttribute('content');
    fetch(`/cart/${cartId}`, {
        method: "DELETE",
        headers: { "X-CSRF-TOKEN": csrfToken }
    }).then(response => {
        if(response.ok){
            location.reload();
        }
    });
}


