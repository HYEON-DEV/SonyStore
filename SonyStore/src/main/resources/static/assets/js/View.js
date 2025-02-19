// console.log(window.location.search);
const clr_light_grey = '#fbfbfb';


/* -- -- -- -- -- -- circleColor 메서드 -- -- -- -- -- -- */
const circleColor = selector => {
    //const selector = document.querySelector( selectorName ); //console.log(selector);
    if( !selector ) return;

    selector.classList.add('color-btn');
     
    const span1 = document.createElement('span');
    span1.classList.add('circle-color');

    const span2 = document.createElement('span');
    span2.classList.add('c-bg');

    span1.appendChild(span2);
    selector.appendChild(span1);
};

/* -- -- -- clr_name 메서드 -- -- -- */
const clr_name = clr => {
    switch ( clr ) {
        case "black":
            return "블랙";
            break;
        case "white":
            return "화이트";
            break;
        case "silver":
            return "실버";
            break;
    }
};

( async () => {

    // const params = utilHelper.getQuery();       
    
    // const paramsArr = Object.values(params).slice(1); // id 값 제외
    
    // const curId = params.id; 
    const curId = 1;    // spring 옮겨오면서 일단 id 1로 지정

    if ( !curId ) {
        alert("제품이 없습니다");
        history.back();
        return;
    }

    let response = null;
    let products, item;
    

    try {
        //  전체 json 조회
        // response = await axios.get(`http://localhost:8080/api/product-view/${prodid}`);
        response = await axios.get(`[[@{/api/product-view/${prodid}}]]`);
        
        //  paramsArr 요소로 백엔드 json 접근
        products = paramsArr.reduce((acc, key) => {
            return acc[key]; // 각 단계에서 접근
        }, response.data);
   
        //  접근한 배열에서 현재 제품의 id가 포함된 객체 가져오기
        //item = products.find(item => item.id == curId);       =>   spring 옮겨오면서 아래 item으로 대체
        
        // 아래는 기존 백엔드에서 불러온 "item 값"
        const item = {
            "id": 1,
            "thumbnail": "clr0_0.png",
            "title": "ZV-E10M2",
            "info": "기록의완성",
            "price": 1340000,
            "date": 202407,
            "color": [
                "black",
                "white"
            ],
            "img": [
                [
                    "clr0_0.png",
                    "clr0_1.png",
                    "clr0_2.png",
                    "clr0_3.png",
                    "clr0_4.png",
                    "clr0_5.png"
                ],
                [
                    "clr1_0.png",
                    "clr1_1.png",
                    "clr1_2.png",
                    "clr1_3.png",
                    "clr1_4.png",
                    "clr1_5.png"
                ]
            ]
        };

    } catch (e) {
        console.error(e);
        alert("요청 실패");
        return;
    }

    // colorid 값 추출
    // 

    const colorArr = item.color;    
    const imgArr = item.img;
    


    /* -- -- -- 메인 좌측 - 슬라이더 -- -- -- */
    
    const swiperWrapper1 = document.querySelector('.swiper-wrapper');
    for ( let i=0; i<imgArr[0].length; i++ ) {
        const div1 = document.createElement('div');
        div1.classList.add('swiper-slide');
        div1.classList.add('slide-main');

        const div2 = document.createElement('div');
        div2.classList.add('img-container');

        const img = document.createElement('img');
        img.classList.add('main-img');
        // img.setAttribute( 'src', `assets/img/${paramsArr[0]}${curId}/${imgArr[0][i]}` );

        div2.appendChild(img);
        div1.appendChild(div2);
        
        swiperWrapper1.appendChild(div1);
    }

    const swiper1 = new Swiper('.swiper', {
        direction: 'horizontal',
        loop: true,
      
        pagination: {
          el: '.swiper-pagination',
          clickable: true,
          type:'custom'
        },

        on: {
            init: function() {
                createPagination();
                updatePagination(this.realIndex);
            },
            slideChange: function () {
                updatePagination(this.realIndex);
            }
        },
      
        navigation: {
          nextEl: '.swiper-button-next',
          prevEl: '.swiper-button-prev'
        }
    });

    function createPagination() {
        const paginationContainer = document.querySelector('.swiper-pagination');
        const slides = document.querySelectorAll('.slide-main');

        paginationContainer.innerHTML = '';

        slides.forEach( () => {
            const bar = document.createElement('div');
            bar.classList.add('pagination-bar');
            paginationContainer.appendChild(bar);
        } );
    }

    function updatePagination(activeIndex) {
        const bars = document.querySelectorAll('.pagination-bar');
        bars.forEach( (bar, index) => {
            bar.classList.remove('active');
            if (index == activeIndex) {
                bar.classList.add('active');
            }
        } );
    }


    /* -- -- -- 메인 우측 - 구매관련 -- -- -- */
    
    document.querySelector(".prd-name").innerHTML = item.title;
    
    document.querySelector(".prd-text").innerHTML = item.info;
    
    const camPrice = item.price;
    document.querySelector(".cam-price").innerHTML = camPrice.toLocaleString();

    document.querySelector(".vip-mlg").innerHTML = (camPrice*0.04).toLocaleString();

    document.querySelector(".family-mlg").innerHTML = (camPrice*0.02).toLocaleString();

        /* -- -- 색상 -- -- */
    if ( colorArr.length > 1 ) {         
        const div = document.createElement('div');
        div.classList.add('color-select');
        document.querySelector('.product-cont-color-select').appendChild(div);

        const p = document.createElement('p');
        p.classList.add('tit');
        p.innerHTML = "색상";

        const ul = document.createElement('ul');
        ul.classList.add('circle-color-box');

        for ( let i=0; i<2; i++ ) {
            let clr = colorArr[i];

            const li = document.createElement('li');

            const a = document.createElement('a');
            a.classList.add('color-btn');
            a.classList.add(clr);

            const span1 = document.createElement('span');
            span1.classList.add('circle-color');
            if( i==0 ) span1.classList.add('active');

            const span2 = document.createElement('span');
            span2.classList.add('c-bg');

            span1.appendChild(span2);

            const span3 = document.createElement('span');
            span3.classList.add('color-name');
            if( i==0 ) span3.classList.add('active');
            span3.innerHTML = clr_name(clr);

            a.appendChild(span1);
            a.appendChild(span3);
            
            li.appendChild(a);

            ul.appendChild(li);
        }        

        div.appendChild(p);
        div.appendChild(ul);
        
         
        document.querySelectorAll('.color-btn').forEach( (v,i) => {
            v.addEventListener( 'click', e => {
                e.preventDefault();
                const clickIdx = i;
    
                document.querySelectorAll('.circle-color').forEach( (v1,i1) => {
                    if ( clickIdx == i1 ) {
                        v1.classList.add('active');
                    } else {
                        v1.classList.remove('active');
                    }
                } );
                document.querySelectorAll('.color-name').forEach( (v2,i2) => {
                    if ( clickIdx == i2 ) {
                        v2.classList.add('active');
                    } else {
                        v2.classList.remove('active');
                    }
                } );
                
                document.querySelectorAll(".main-img").forEach( (v3,i3) => {
                    v3.setAttribute( "src", `assets/img/${paramsArr[0]}${curId}/${imgArr[i][i3]}` );
                } );
                
                
            } );
        } );
    }

    /* 제품을 선택하세요 */
    const ulSelectInner = document.querySelector('.select-inner');

    const colorNum = colorArr.length; 
    for ( let i=0; i<colorNum; i++ ) {
        const li = document.createElement('li');
        li.classList.add('inner-list');
        
        const a = document.createElement('a');
        
        li.appendChild(a);
        ulSelectInner.appendChild(li);
    } 
    document.querySelectorAll('.inner-list').forEach( (v,i) => {
        let span1, span2;
        if ( colorNum > 1 ) {
            span1 = document.createElement('span');
            span1.classList.add('circle-prd-clr');
            span1.classList.add('pad');

            circleColor(span1);                                        

            const colorName = colorArr[i];
            span1.classList.add( colorName ); 
            
            span2 = document.createElement('span');
            span2.classList.add('submenu-txt');
            span2.classList.add('pad');
            span2.innerHTML = `${item.title} / ${clr_name(colorName)}`;
        }
        else {
            span2 = document.createElement('span');
            span2.classList.add('submenu-txt');
            span2.classList.add('pad');
            span2.innerHTML = `제품명: ${item.title}`;
        }
        const a = v.children[0];
        if ( span1 ) a.appendChild(span1);
        a.appendChild(span2);
    } );
    
    document.querySelector('.selected-btn').addEventListener( 'click', e => {
        e.preventDefault();
        const current = e.currentTarget;
        current.classList.toggle('active');
        ulSelectInner.classList.toggle('active');       
    } );    

    /* -- -- 제품 선택 - 선택된 제품 박스 -- -- */
    
    const innerList = document.querySelectorAll('.inner-list');     // submenu list  선택할 제품 목록
    //console.log(innerList.length);
    innerList.forEach( (v,i) => {    
        
        v.addEventListener( 'click', e => {
            e.preventDefault();
            const current = e.currentTarget;    // 클릭한 선택한 제품 ex) "ILCE-7CM2 / 실버"
            
            const createBox = () => {
                const div1 = document.createElement('div');
                div1.classList.add('selected-opt');
        
                const div2 = document.createElement('div');
                div2.classList.add('selected-opt-container');
                
                const div3 = document.createElement('div');
                div3.classList.add('selected-prd-top');
        
                const p = document.createElement('p');
                p.classList.add('prd');
                p.innerHTML = '제품';
        
                const a = document.createElement('a');
                a.classList.add('prd-delete');
                a.innerHTML = '구매목록에서 삭제';
        
                div3.appendChild(p);
                div3.appendChild(a);

                const div4 = document.createElement('div');
                div4.classList.add('selected-name');
        
                if ( innerList.length > 1 ) {
                    const circlePrdClr = current.querySelector('.circle-prd-clr');
                    div4.appendChild( circlePrdClr.cloneNode(true) );
                }
                
                const submenuTxt = current.querySelector('.submenu-txt');
                div4.appendChild( submenuTxt.cloneNode(true) );
                //console.log(div4);

                const div5 = document.createElement('div');
                div5.classList.add('selected-bottom');

                const div6 = document.createElement('div');
                div6.classList.add('count-container');

                const btn1 = document.createElement('button');
                btn1.classList.add('minus');
                btn1.classList.add('cnt-box');
                btn1.innerHTML = '-';

                const input = document.createElement('input');
                input.classList.add('cnt-box');
                input.classList.add('count');
                input.setAttribute( 'type', 'text' );
                input.setAttribute( 'value', 1 );
                input.setAttribute('readonly', true);

                const btn2 = document.createElement('button');
                btn2.classList.add('plus');
                btn2.classList.add('cnt-box');
                btn2.innerHTML = '+';

                div6.appendChild(btn1);
                div6.appendChild(input);
                div6.appendChild(btn2);

                const div7 = document.createElement('div');
                div7.classList.add('selected-price');
                div7.innerHTML = `${(item.price).toLocaleString()}원`;

                div5.appendChild(div6);
                div5.appendChild(div7);

                div2.appendChild(div3);
                div2.appendChild(div4);
                div2.appendChild(div5);

                div1.appendChild(div2);
                //console.log(div1);
                
                document.querySelector('.prd-select-box').appendChild(div1);

                //selectedOpts.push(div1);
                //console.log(selectedOpts); // 현재까지 생성된 선택된 옵션 출력
                 
                a.addEventListener('click', (e) => {
                    e.preventDefault();
                    div1.remove(); // 해당 선택된 옵션 삭제                     
                }); 
               
            };  

            //  제품 선택 박스 중복 방지            
            const existingOpt = Array.from(document.querySelectorAll('.selected-opt')).some(opt => {
                if ( innerList.length>1 ) {
                    return opt.querySelector(`.${colorArr[i]}`);
                } else {
                    return true;
                }
            });
    
            if (existingOpt) {
                alert('이미 선택된 옵션입니다.');
            } else {
                createBox();
            }
           
            ulSelectInner.classList.remove('active');
        } );

        
    } );


    /* -- -- 함께 구매하시면 좋은 추천 제품 -- -- */

    /**
     *  현재 제품과 같은 category(camera, video, lens, ...) 인 객체들을 배열로 반환
     * @param {object} data  탐색할 object
     * @param {string} category  category 이름
     * @returns  array
     */
    function getProducts(data, category) {
        const result = [];
        
        // 카테고리에서 데이터 찾기
        const categoryData = data[category];
        if (!categoryData) return result;
    
        // 재귀적으로 탐색하는 내부 함수
        function searchProducts(obj) {
            if (Array.isArray(obj)) {
                result.push(...obj);
            } else if (typeof obj === 'object' && obj !== null) {
                for (const value of Object.values(obj)) {
                    searchProducts(value);
                }
            }
        }
    
        // 카테고리 구조 탐색 시작
        searchProducts(categoryData);
        return result;
    }
    
    const productList = getProducts( response.data, paramsArr[0] );     //console.log(productList);
    
 
    //  현재 제품 제외한 제품 배열 dataArr
    let dataArr;
    productList.some( (v,i) => {
        if ( v.id == curId ) {
            dataArr = arrayHyeon.removeElementAtIndex(productList,i);
            return true;
        }
    } );
    //  some 메서드   탐색을 중단하는 기능 제공
    //  콜백함수가 true 리턴하는 순간 전체 반복 중단 
    //console.log(dataArr);
 
    const randomData = arrayHyeon.shuffleArray(dataArr);
    //console.log(randomData); 

    //  슬라이드 생성
    
    const swiperWrapperRcmd = document.querySelector('.swiper-wrapper-rcmd');

    randomData.forEach( (v,i) => {
        const swiperSlide = document.createElement('div');
        swiperSlide.classList.add('swiper-slide');

        const rcmd_img_container = document.createElement('div');
        rcmd_img_container.classList.add('recommend-img-container');
        
        const a = document.createElement('a');
        const qs = queryStringById( response.data, v );     //console.log(qs);  
        a.setAttribute('href',`view.html?id=${v.id}&${qs}`);

        const img = document.createElement('img');
        img.classList.add('recommend-img');
        img.setAttribute('src',`assets/img/${paramsArr[0]}${v.id}/${imgArr[0][0]}` );

        a.appendChild(img);
        
        const span1 = document.createElement('span');
        span1.classList.add('recommend-title');
        span1.innerHTML = v.title;
        
        const span2 = document.createElement('span');
        span2.classList.add('recommend-desc');
        span2.innerHTML = v.info;
        
        const span3 = document.createElement('span');
        span3.classList.add('recommend-price');
        span3.innerHTML = v.price.toLocaleString() + '원';

        rcmd_img_container.appendChild(a);
        rcmd_img_container.appendChild(span1);
        rcmd_img_container.appendChild(span2);
        rcmd_img_container.appendChild(span3);
        swiperSlide.appendChild(rcmd_img_container);
        swiperWrapperRcmd.appendChild(swiperSlide);
    } );    
    

    const swiper2 = new Swiper('.swiper-2', {
        
        direction: 'horizontal',
        loop: false,

        slidesPerView: 4,

        navigation: {
            nextEl: '.swiper-button-next-2',
            prevEl: '.swiper-button-prev-2',
        },

        on: {
            init: function() {
                updateNavigationButtons();
            },
            slideChange: function() {
                updateNavigationButtons();
            },
            resize: function() {
                updateNavigationButtons();
            }
        }
    });
      
    function updateNavigationButtons() {
        const swiper_rcmd = document.querySelector('.swiper-2').swiper;
        // console.log(swiper);
        const lastSlideIndex = swiper_rcmd.slides.length - swiper_rcmd.params.slidesPerGroup;

        if (swiper_rcmd.isEnd) {
            document.querySelector('.swiper-button-next-2').style.display = 'none';
        } else {
            document.querySelector('.swiper-button-next-2').style.display = '';
        }
        
        if (swiper_rcmd.isBeginning) {
            document.querySelector('.swiper-button-prev-2').style.display = 'none';
        } else {
            document.querySelector('.swiper-button-prev-2').style.display = '';
        }
    }
        
})();


/* -- -- -- -- 총 상품금액 계산 -- -- -- -- */

const totalPrice = document.querySelector('.result-price .num');

function calculateTotal() {
    const selectedOpts = document.querySelectorAll('.selected-opt');
    let total = 0;

    selectedOpts.forEach(opt => {
        const priceText = opt.querySelector('.selected-price').textContent;
        const countInput = opt.querySelector('.cnt-box[type="text"]');

        // if (priceText && countInput) {
            const price = parseInt(priceText.replaceAll(',', ''));
            const count = parseInt(countInput.value);
            total += price;
        // }

    });
    // total = total>0? total.toLocaleString() : '-';
    totalPrice.textContent = total>0? total.toLocaleString() : '-';
}

// MutationObserver 설정
const observer = new MutationObserver(() => {
    calculateTotal();
});

// 관찰할 설정
const config = {
    childList: true,
    subtree: true
};

// prdSelectBox 요소에 대해 관찰 시작
const prdSelectBox = document.querySelector('.prd-select-box');
if (prdSelectBox) {
    observer.observe(prdSelectBox, config);
} else {
    console.error('prd-select-box 요소가 존재하지 않습니다.');
}

// 버튼 클릭 이벤트 설정
document.addEventListener('click', (e) => {
    if (e.target.classList.contains('plus') || e.target.classList.contains('minus')) {
        const inp_cnt = e.target.parentElement.querySelector('.count');
        const selectOpt = e.target.closest('.selected-opt');
        const selectedPrice = selectOpt.querySelector('.selected-price');
        // if (inp_cnt) {
            let count = parseInt(inp_cnt.value);

            if (e.target.classList.contains('plus')) {
                count++;
            } else if (e.target.classList.contains('minus')) {
                count = count > 1 ? count - 1 : 1; // 최소값 1로 설정
            }

            inp_cnt.value = count;

            const price = parseInt(document.querySelector('.cam-price').textContent.replaceAll(',',''));
            selectedPrice.textContent = (count * price).toLocaleString() + '원';

            calculateTotal(); // 총 금액 재계산
        // }
    }
});




/* -- -- -- 제품개요/상세/배송,환불규정  TAB -- -- -- */

document.querySelectorAll('.tab-menu').forEach( (v,i) => {
    v.addEventListener( 'click', e => {
        const current = e.currentTarget;
        current.classList.add('active');
        
        document.querySelectorAll('.tab-menu').forEach( (w,j) => {
            if ( w !== current ) {
                w.classList.remove('active');
            }
        } );

        document.querySelectorAll('.tab-zone').forEach( (v1,i1) => {
            if ( i == i1 ) {
                v1.classList.add('active');
            } else {
                v1.classList.remove('active');
            }
        } );
    } );
} );
