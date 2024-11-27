let products = []; // 전역 변수 선언
let backgrounds = []; // 전역 변수 선언

window.onload = async (e) => {
    let response = null;
    let response2 = null;

    try {
        response = await axios.get('http://localhost:8080/api/products');
        response2 = await axios.get('http://localhost:8080/api/backgrounds');
        products = response.data.list; // 데이터 로딩 후 전역 변수에 할당
        backgrounds = response2.data.list; // 데이터 로딩 후 전역 변수에 할당
    } catch (error) {
        console.error(`[Error Code] ${error.code}`);
        console.error(`[Error message] ${error.message}`);
        let alertMsg = error.message;

        // HTTP 상태 메시지가 포함되어 있다면 해당 내용을 에러 문자열에 추가
        if (error.response !== undefined) {
            const errorMsg = `${error.response.status} error - ${error.response.statusText}`;
            console.error(`[HTTP Status] ${errorMsg}`);
            alertMsg += `\n${errorMsg}`;
        }

        alert(alertMsg);
        return;
    }

    updateBackgroundImage(backgrounds, products); // 배경 이미지 업데이트 함수 호출
    renderProductList(products); // 제품 리스트 렌더링 함수 호출
    updateCategoryName(products); // 카테고리 이름 업데이트 함수 호출
};

// 제품 리스트를 렌더링하는 함수
function renderProductList(products) {
    console.log(products);
    const list_container = document.querySelector('.list_container'); // 제품 리스트를 표시할 컨테이너
    list_container.innerHTML = ''; // 기존 내용을 초기화

    products.forEach(product => {
        const item = document.createElement('a');
        item.classList.add('item');
        // item.href = `view.html?prodid=${product.prodid}`; // 제품 상세 페이지 링크

        // 이미지 컨테이너 생성
        const imgContainer = document.createElement('div');
        imgContainer.classList.add('img_container');

        // 이미지 요소 생성
        const img = document.createElement('img');
        const thumbnailImage = product.images.find(image => image.thumbnail === 'Y');
        if (thumbnailImage) {
            img.src = thumbnailImage.filepath; // thumbnail이 'Y'인 이미지 경로 설정
        } 
        img.alt = product.title;

        imgContainer.appendChild(img);
        item.appendChild(imgContainer);

        // 제품 정보 컨테이너 생성
        const infoContainer = document.createElement('div');
        infoContainer.classList.add('info_container');

        const title = document.createElement('h3');
        title.textContent = product.title;

        const desc = document.createElement('p');
        desc.textContent = product.proddesc;

        infoContainer.appendChild(title);
        infoContainer.appendChild(desc);
        item.appendChild(infoContainer);

        list_container.appendChild(item);
    });
}

// 배경 이미지를 설정하는 함수
function updateBackgroundImage(backgrounds, products) {
    const categoryBackground = document.getElementById('categoryBackground');

    // 제품 리스트에 있는 제품의 타입과 배경 이미지의 타입을 비교하여 일치하는 배경 이미지를 설정
    products.forEach(product => {
        const background = backgrounds.find(bg => bg.type === product.type1 || bg.type === product.type2);
        if (background) {
            categoryBackground.style.backgroundImage = `url(${background.filepath})`;
            console.log(`Background: ${background.filepath}`);
        }
    });
}

// 페이지에 카테고리 이름 업데이트 하는 함수
function updateCategoryName(products) {
    const categoryName = document.querySelector('.category_name');
    console.log(products);

    // 제품 리스트에 있는 제품의 type1을 카테고리 이름으로 설정
    if(products.length > 0) {
        categoryName.textContent = products[0].type1;
    }
    
    
}

