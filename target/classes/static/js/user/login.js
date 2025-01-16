const idInput = document.getElementById('id')
const idSaveCheckbox = document.getElementById('id-save');
// 로그인 페이지에 왔을 때 저장된 id가 있으면 input에 넣어줌 (id 저장)
const cookies = document.cookie;
for (const item of cookies.split(';')){
    const [key, value] = item.trim().split('=');
    // 찾은 값이 id저장 쿠키라면
    if(key === 'id-save'){
        // 쿠키의 값으로 idInput의 값을 변경한다
        idInput.value = value;
    }
}

// 로그인 버튼을 누르면 id를 저장할까말까 (id 저장)
document.forms[0].onsubmit = event => {
    // id저장 체크박스를 클릭한 상태라면 오늘 날짜의 +3일까지의 만료 날짜를 정한다
    const idSaveExpiresDate = new Date();
    idSaveExpiresDate.setDate(idSaveExpiresDate.getDate() + 3);
    document.cookie = `id-save=; expires=${idSaveExpiresDate.toUTCString()}`;
    if(idSaveCheckbox.checked){
        const id = idInput.value;
        // Cookie에 id값을 저장해야 한다
        document.cookie = `id-save=${id}; expires=${idSaveExpiresDate.toUTCString()};`;
    }
}




