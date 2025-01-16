
const itemImagesSectionOl = document.querySelector('.item-images-section ol')
const itemImageAddButton = document.getElementById('image-add-button');

$('#summernote').summernote({
    placeholder: '내용을 작성해주세요',
    tabsize: 2,
    height: 400
});


function reorder_image_numbers(){
    const imageLies = itemImagesSectionOl.querySelectorAll('li');
    imageLies.forEach((imageLi, index) => {
        const imageFileInput = imageLi.querySelector('input');
        imageFileInput.name = `images[${index}]`;
    })
}
// 이미지 추가 버튼을 눌렀다면
itemImageAddButton.onclick = () => {
    itemImagesSectionOl.insertAdjacentHTML('beforeend',
        `<li>
            <button type="button" onclick="delete_itemImage(this);"><i class="bi bi-dash"></i></button>
            <input type="file" name="images[0]">
        </li>`
    );
    reorder_image_numbers();
}
// 이미지 삭제 버튼을 눌렀다면
function delete_itemImage(imageDeleteButton){
    if(confirm('정말 삭제하시겠습니까?')){
        imageDeleteButton.parentElement.remove();
        reorder_image_numbers();
    }
}




