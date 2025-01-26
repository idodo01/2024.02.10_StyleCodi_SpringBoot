const parts = ["top", "outer", "bottom", "shoes", "bag"];

async function combineImages() {
    console.log("결합하기 눌림");

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

