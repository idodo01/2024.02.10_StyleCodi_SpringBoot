const viewSizeSelect = document.getElementById('view-size-select');
viewSizeSelect.onchange = () => {
    const searchParams = new URLSearchParams(location.search);
    searchParams.set('size', viewSizeSelect.value);
    location.href = `/product?${searchParams.toString()}`;
}