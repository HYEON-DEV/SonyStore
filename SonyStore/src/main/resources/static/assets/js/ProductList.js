let productsByType = []; // type에 따른 제품 리스트
let productsByType2 = []; // type2에 따른 제품 리스트
let backgrounds = []; // 전역 변수 선언

window.onload = async (e) => {
    let response = null;
    let response2 = null;
    let response3 = null;

    

    try {
        // response = await axios.get(`http://localhost:8080/api/products`);
        response = await axios.get(`http://localhost:8080/api/products/${type}`);
        response2 = await axios.get('http://localhost:8080/api/backgrounds');

        productsByType = response.data.list; // type에 따른 데이터 로딩 후 전역 변수에 할당
    
        backgrounds = response2.data.list; // 데이터 로딩 후 전역 변수에 할당

        // 필터링된 제품 리스트 렌더링
        renderProductList(productsByType);

        // 배경 이미지 업데이트
        updateBackgroundImage(backgrounds, productsByType);

        // 카테고리 이름 업데이트
        updateCategoryNameType1(productsByType);

        renderCategoryList(productsByType);

        if (type2) {
            response3 = await axios.get(`http://localhost:8080/api/products/${type}/${type2}`);
            productsByType2 = response3.data.list; // type2에 따른 데이터 로딩 후 전역 변수에 할당
            renderProductList(productsByType2);
            updateCategoryNameType2(productsByType2);
            updateType2(backgrounds, productsByType2);
        }
        
    } catch (error) {
        console.error(`[Error Code] ${error.code}`);
        console.error(`[Error message] ${error.message}`);
        let alertMsg = error.message;

        if (error.response !== undefined) {
            const errorMsg = `${error.response.status} error - ${error.response.statusText}`;
            console.error(`[HTTP Status] ${errorMsg}`);
            alertMsg += `\n${errorMsg}`;
        }

        alert(alertMsg);
        return;
    }
};

// 제품 리스트를 렌더링하는 함수
function renderProductList(products) {
    const list_container = document.querySelector('.list_container'); // 제품 리스트를 표시할 컨테이너
    if (!list_container) {
        console.error('List container not found');
        return;
    }
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
        const background = backgrounds.find(bg => bg.type === product.type1);
        if (background) {
            categoryBackground.style.backgroundImage = `url(${background.filepath})`;
            console.log(`Background: ${background.filepath}`);
        }
    });
}

// type2 배경 이미지 및 박스영역, 돌아가기 버튼 , 탭 메뉴를 설정하는 함수
function updateType2(backgrounds, products) {
    const categoryBackground = document.getElementById('categoryBackground');
    const categoryList = document.querySelector(".category_list");
    const categoryLink = document.querySelector(".category_link");
    const goBack = document.querySelector(".go_back");
    

    // 카테고리 맵 가져오기
    const categoryMap = getCategoryMap1();
    console.log(products);
    renderTapMenu(products);

    // 제품 리스트에 있는 제품의 타입과 배경 이미지의 타입을 비교하여 일치하는 배경 이미지를 설정
    products.forEach(product => {
        const background = backgrounds.find(bg => bg.type === product.type2);
        if (background) {
            categoryBackground.style.backgroundImage = `url(${background.filepath})`;
            
            // 클래스 조작 추가
            categoryList.classList.add("display_none");
            categoryLink.classList.remove("display_none");
            goBack.classList.remove("display_none");

            // 'go_back' 버튼 텍스트 설정
            goBack.innerHTML = '<i class="fas fa-angle-left"></i>&nbsp;' + (categoryMap[product.type1] || product.type1);

            // 'go_back' 버튼 클릭 이벤트 추가
            goBack.addEventListener('click', () => {
                window.location.href = `http://localhost:8080/products/${product.type1}`;
            });
        }
    });
}


// Type1 영어 키와 한글 값을 매핑하는 함수
function getCategoryMap1() {
    return {
        'camera': '카메라',
        'video': '비디오 카메라',
        'audio': '오디오',
        'accessory': '액세서리'
    };
}

// Type2 영어 키와 한글 값을 매핑하는 함수
function getCategoryMap2() {
    return {
        'lens_change': '렌즈교환식 카메라',
        'compact': '컴팩트 카메라',
        'cinema': '시네마 라인 카메라',
        'camcorder': '캠코더'
    };
}

// Type3 영어 키와 한글 값을 매핑하는 함수
function getCategoryMap3() {
    return {
        'APS-C': 'APS-C',
        'Full-Frame': '풀프레임'
    };
}

// 페이지에 카테고리 이름(type1) 업데이트 하는 함수
function updateCategoryNameType1(products) {
    const categoryName = document.querySelector('.category_name');
    if (!categoryName) {
        console.error('Category name element not found');
        return;
    }
    
    const categoryMap = getCategoryMap1();
    

    // 제품 리스트에 있는 제품의 type1을 카테고리 이름으로 설정
    if(products.length > 0) {
        const type1 = products[0].type1;
        categoryName.textContent = categoryMap[type1] || type1; // 매핑된 값이 없으면 원래 값을 사용
    }
}

// 페이지에 카테고리 이름(type2) 업데이트 하는 함수
function updateCategoryNameType2(products) {
    const categoryName = document.querySelector('.category_name');
    if (!categoryName) {
        console.error('Category name element not found');
        return;
    }
    

    const categoryMap = getCategoryMap2();

    // 제품 리스트에 있는 제품의 type2을 카테고리 이름으로 설정
    if(products.length > 0) {
        const type2 = products[0].type2;
        categoryName.textContent = categoryMap[type2] || type2; // 매핑된 값이 없으면 원래 값을 사용
    }
}

// 카테고리 리스트 렌더링 함수
function renderCategoryList(products) {
    const categoryList = document.querySelector('.category_list .list');
    if (!categoryList) {
        console.error('Category list element not found');
        return;
    }
    categoryList.innerHTML = ''; // 기존 리스트 초기화

    // categoryKey 정의
    let categoryKey = '';
    if (products.length > 0) {
        categoryKey = products[0].type1;
    }

    // '전체보기' 버튼 추가
    const allItem = document.createElement('li');
    allItem.innerHTML = `<a href="#" class="All"><img src="assets/img/products/crossHair.svg">전체보기</a>`;
    categoryList.appendChild(allItem);

    // type2 종류별로 한 개씩만 담기
    const uniqueType2 = {};
    products.forEach(product => {
        if (!uniqueType2[product.type2]) {
            uniqueType2[product.type2] = product;
        }
    });
    
    const categoryMap2 = getCategoryMap2();

    // uniqueType2 객체를 배열로 변환하여 렌더링
    Object.values(uniqueType2).forEach(subCategory => {
        const li = document.createElement('li');
        const imgSrc = 'assets/img/subcategories/default.svg'; // 기본 이미지 경로 설정
        const categoryName = categoryMap2[subCategory.type2] || subCategory.type2; // 한글 이름 또는 원래 값 사용
        li.innerHTML = `<a href="http://localhost:8080/products/${categoryKey}/${subCategory.type2}" class="${subCategory.type2}-filter"><img src="${imgSrc}">${categoryName}</a>`;
        categoryList.appendChild(li); // 서브 카테고리 리스트에 추가
    });
}


function renderTapMenu(products) {
    const tapMenuWrapper = document.querySelector('.tapMenu_wrapper');
    const tapMenuList = document.querySelector('.tapMenu_list');
    tapMenuList.innerHTML = ''; // 기존 탭 메뉴 초기화

    if (products.type3 ==="") {
        console.log('type3 is empty, hiding tap menu'); // 조건 확인
        tapMenuWrapper.style.display = 'none'; // type3 값이 없으면 탭 메뉴 숨김
        return;
    }

    tapMenuWrapper.style.display = 'block'; // 탭 메뉴 표시

    // '전체보기' 탭 추가
    const allTab = document.createElement('li');
    allTab.innerHTML = `<a href="#" class="all-filter"><span>전체보기</span></a>`;
    tapMenuList.appendChild(allTab);

    // 활성화 상태 설정: type3 값이 'all'이면 '전체보기' 탭 활성화
    // if (products.type3 === 'all') {
    //     allTab.querySelector('a').classList.add('active'); // '전체보기' 탭 활성화
    // }

    // productsByType2 데이터를 기반으로 type3 값에 따른 탭 추가
    const uniqueType3 = [...new Set(productsByType2.map(product => product.type3))].filter(Boolean); // type3 값의 유니크한 배열 생성, 빈 문자열 제거
    uniqueType3.forEach(category => {
        const li = document.createElement('li');
        li.innerHTML = `<a href="#" class="${category}-filter"><span>${category}</span></a>`;
        if (products.type3 === category) {
            li.querySelector('a').classList.add('active'); // 현재 type3 값에 해당하는 탭 활성화
        }
        tapMenuList.appendChild(li);
    });

    // 탭 클릭 이벤트 추가
    tapMenuList.querySelectorAll('a').forEach(tab => {
        tab.addEventListener('click', (event) => {
            event.preventDefault();
            
            renderProductList(); // 선택된 카테고리에 따라 제품 목록 렌더링
        });
    });
}
