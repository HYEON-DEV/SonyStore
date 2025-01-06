let productsByType = []; // type에 따른 제품 리스트
let productsByType2 = []; // type2에 따른 제품 리스트
let productsByType3 = []; // type3에 따른 제품 리스트
let backgrounds = []; // 전역 변수 선언
let currentProducts = []; // 현재 선택된 제품 리스트를 저장할 변수

const body = document.querySelector('body');
const loading = document.querySelector('#loading');

window.onload = async (e) => {

    // 페이지 로딩 => 로딩바 활성화
    loading.classList.add('active');
    body.style.opacity = '0.6';
    body.style.pointerEvents = 'none';
    body.style.overflow = 'hidden';

    let response = null;
    let response2 = null;
    let response3 = null;

    

    try {
        response = await axios.get(`${apiProducts}/${type}`);

        response2 = await axios.get(`${apiBackgrounds}`);

        productsByType = response.data.list; // type에 따른 데이터 로딩 후 전역 변수에 할당
    
        backgrounds = response2.data.list; // 데이터 로딩 후 전역 변수에 할당

        if(productsByType.length == 0) {
            window.location = "/error/error_404";
            return;
        }


        // 필터링된 제품 리스트 렌더링
        renderProductList(productsByType);
        currentProducts = productsByType; // 현재 제품 리스트 업데이트
        sortProductsByDate(); // 페이지 로드 시 최신순으로 정렬
        document.querySelector('.sort_recent').classList.add('sort_active'); // 최신순 버튼에 active 클래스 추가

        // 배경 이미지 업데이트
        updateBackgroundImage(backgrounds, productsByType);

        // 카테고리 이름 업데이트
        updateCategoryNameType1(productsByType);

        renderCategoryList(productsByType);

        if (type2) {
            response3 = await axios.get(`${apiProducts}/${type}/${type2}`);
            productsByType2 = response3.data.list; // type2에 따른 데이터 로딩 후 전역 변수에 할당

            if(productsByType2.length == 0) {
                window.location = "/error/error_404";
                return;
            }

            renderProductList(productsByType2);
            currentProducts = productsByType2; // 현재 제품 리스트 업데이트
            sortProductsByDate(); // 페이지 로드 시 최신순으로 정렬
            document.querySelector('.sort_recent').classList.add('sort_active'); // 최신순 버튼에 active 클래스 추가
            updateCategoryNameType2(productsByType2);
            updateType2(backgrounds, productsByType2);
        }

        // 정렬 버튼 클릭 이벤트 설정 및 active 클래스 추가
        setupSortButtons();
        
    } catch (error) {
        // console.error(`[Error Code] ${error.code}`);
        // console.error(`[Error message] ${error.message}`);
        // let alertMsg = error.message;

        // if (error.response !== undefined) {
        //     const errorMsg = `${error.response.status} ${error.response.statusText}`;
        //     console.error(`[HTTP Status] ${errorMsg}`);
        //     alertMsg += `\n${errorMsg}`;
        // }

        // alert(alertMsg);
        window.location = "/error/error_404";
        return;
    } finally {
        // 페이지 로딩 완료 => 로딩바 비활성화
        loading.classList.remove('active');
        body.style.opacity = '1';
        body.style.pointerEvents = 'all';
        body.style.overflow = 'visible';
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
        item.href = productView + `/${product.prodid}`; // 제품 상세 페이지 링크

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

        // 색상 선택 버튼들
        if (product.colors && product.colors.length > 1) {
            const colorButtons = document.createElement("div");
            colorButtons.classList.add("color-buttons");

            // pcolor 값이 'Y'인 색상을 먼저 처리
            const sortedColors = product.colors.sort((a, b) => b.pcolor.localeCompare(a.pcolor));

            sortedColors.forEach((color, index) => {
                const button = document.createElement("button");
                const buttonInner = document.createElement("button");

                button.classList.add("color-button");
                button.setAttribute("data-color-index", index);

                // 버튼 배경색을 실제 색상으로 설정
                button.style.backgroundColor = color.color;
                button.appendChild(buttonInner);
                buttonInner.classList.add("color-button-inner");
                buttonInner.style.backgroundColor = color.color;

                // pcolor 값이 'Y'인 경우 버튼을 활성화
                if (color.pcolor === 'Y') {
                    button.classList.add('active');
                }

                // 마우스 오버 이벤트 리스너
                button.addEventListener('mouseover', () => {
                    const colorImage = product.images.find(image => image.colorid === color.colorid);
                    if (colorImage) {
                        img.setAttribute("src", colorImage.filepath);
                    }
                    // 모든 버튼의 active 클래스 제거
                    document.querySelectorAll('.color-button').forEach(btn => btn.classList.remove('active'));

                    // 현재 버튼에 active 클래스 추가
                    button.classList.add('active');
                });

                colorButtons.appendChild(button);
            });

            item.appendChild(colorButtons);
        } else {
            img.style.marginBottom = "24px";
        }

        // 제품 정보 컨테이너 생성
        const infoContainer = document.createElement('div');
        infoContainer.classList.add('info_container');

        // 제품명
        const title = document.createElement('h3');
        title.textContent = product.title;

        // 제품 설명
        const desc = document.createElement('p');
        desc.textContent = product.proddesc;

        infoContainer.appendChild(title);
        infoContainer.appendChild(desc);
        item.appendChild(infoContainer);

        // 제품 가격
        const price = document.createElement("span");
        price.innerHTML = product.price.toLocaleString('ko-KR') + '원'; // 숫자 형태를 한국 스타일의 쉼표 구분 형식으로 변환
        item.appendChild(price);

        list_container.appendChild(item); // 리스트 컨테이너에 아이템 추가
    });

    // 제품 개수 업데이트
    updateProductCount(products.length);
    currentProducts = products; // 현재 제품 리스트 업데이트
}

// type1 배경 이미지를 설정하는 함수
function updateBackgroundImage(backgrounds, products) {
    const categoryBackground = document.getElementById('categoryBackground');
    

    // 제품 리스트에 있는 제품의 타입과 배경 이미지의 타입을 비교하여 일치하는 배경 이미지를 설정
    products.forEach(product => {
        const background = backgrounds.find(bg => bg.type === product.type1);
        if (background) {
            categoryBackground.style.backgroundImage = `url(${background.filepath})`;
            
        }
    });
}

// type2 배경 이미지 및 박스영역, 돌아가기 버튼 설정하는 함수
function updateType2(backgrounds, products) {
    const categoryBackground = document.getElementById('categoryBackground');
    const categoryList = document.querySelector(".category_list");
    const categoryLink = document.querySelector(".category_link");
    const goBack = document.querySelector(".go_back");
    

    // 카테고리 맵 가져오기
    const categoryMap = getCategoryMap1();
   
    // 탭 메뉴 렌더링
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
                window.location.href = urlProducts + `/${product.type1}`;
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
        'lens': '렌즈',
        'accessory': '액세서리'
    };
}

// Type2 영어 키와 한글 값을 매핑하는 함수
function getCategoryMap2() {
    return {
        'lens_change': '렌즈교환식 카메라',
        'compact': '컴팩트 카메라',
        'cinema': '시네마 라인 카메라',
        'camcorder': '캠코더',
        'fullframe': '풀프레임 렌즈',
        'APS-C': 'APS-C 렌즈'
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
    allItem.innerHTML = `<a href="#" class="All">전체보기</a>`;
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
    Object.values(uniqueType2).reverse().forEach(subCategory => {
        const li = document.createElement('li');
        const imgSrc = 'assets/img/subcategories/default.svg'; // 기본 이미지 경로 설정
        const categoryName = categoryMap2[subCategory.type2] || subCategory.type2; // 한글 이름 또는 원래 값 사용
        li.innerHTML = `<a href="${urlProducts}/${categoryKey}/${subCategory.type2}" class="${subCategory.type2}-filter">${categoryName}</a>`;
        categoryList.appendChild(li); // 서브 카테고리 리스트에 추가
    });
}


function renderTapMenu(products) {
    const tapMenuWrapper = document.querySelector('.tapMenu_wrapper');
    const tapMenuList = document.querySelector('.tapMenu_list');
    tapMenuList.innerHTML = ''; // 기존 탭 메뉴 초기화
    const type3Length = products.filter(product => product.type3).length; // type3 값이 있는 제품 개수
    // console.log(products);
    // console.log(products.type3);

    
    if (type3Length === 0) {
        
        tapMenuWrapper.style.display = 'none'; // type3 값이 없으면 탭 메뉴 숨김
        return;
    }

    tapMenuWrapper.style.display = 'block'; // 탭 메뉴 표시

    // '전체보기' 탭 추가
    const allTab = document.createElement('li');
    allTab.innerHTML = `<a href="#" class="all-filter active"><span>전체보기</span></a>`; // '전체보기'에 active 클래스 추가
    tapMenuList.appendChild(allTab);

    

    // 탭 메뉴에서 선택된 카테고리에 따라 해당 버튼에 활성화 상태를 설정하는 함수
    function activateTab(tabElement) {
        document.querySelectorAll('.tapMenu_list a').forEach(v => {
            v.classList.remove('active'); // 모든 버튼의 active 클래스 제거
        });
        tabElement.classList.add('active'); // 클릭된 버튼에만 active 클래스 추가

    }

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
        tab.addEventListener('click', async (event) => {
            event.preventDefault();

            

            // 클릭한 탭의 카테고리(type3) 가져오기
            const type3 = tab.className.replace('-filter', '');

            // '전체보기' 탭 처리
            let requestUrl;
            if (type3 === 'all') {
                requestUrl = `${apiProducts}/${type}/${type2}`;
            } else {
                requestUrl = `${apiProducts}/${type}/${type2}/${type3}`;
            }

            try {
                // AJAX 요청 보내기
                const response = await axios.get(requestUrl);
                const products = response.data.list;

                // 새로운 데이터로 제품 목록 렌더링
                renderProductList(products);

                activateTab(tab);
            } catch (error) {
                console.error(error);
            }
        });
    });
}

// 정렬 버튼 클릭 이벤트 설정 및 active 클래스 추가 함수
function setupSortButtons() {
    document.querySelector('.sort_high_price').addEventListener('click', e => {
        e.preventDefault();
        sortProductsByPrice('high_to_low');
    });

    document.querySelector('.sort_low_price').addEventListener('click', e => {
        e.preventDefault();
        sortProductsByPrice('low_to_high');
    });

    document.querySelector('.sort_recent').addEventListener('click', e => {
        e.preventDefault();
        sortProductsByDate();
    });

    document.querySelectorAll('.sort').forEach((v, i) => {
        v.addEventListener("click", (e) => {
            document.querySelectorAll('.sort').forEach((v, i) => {
                v.classList.remove("sort_active");
            });
            e.currentTarget.classList.add("sort_active");
        });
    });
}

// 가격순 정렬 함수
function sortProductsByPrice(order) {
    const sortedList = [...currentProducts].sort((a, b) => {
        if (order === 'high_to_low') {
            return b.price - a.price; // 높은 가격순
        } else {
            return a.price - b.price; // 낮은 가격순
        }
    });
    renderProductList(sortedList); // 정렬된 리스트 렌더링
}

// 날짜순 정렬 함수
function sortProductsByDate() {
    const sortedList = [...currentProducts].sort((a, b) => new Date(b.date) - new Date(a.date));
    renderProductList(sortedList); // 정렬된 리스트 렌더링
}

// 제품 개수를 업데이트하는 함수
function updateProductCount(count) {
    const productCount = document.querySelector('.list_info_num');
    if (productCount) {
        productCount.innerHTML = `(${count})`; // 제품 개수 표시
    }
}