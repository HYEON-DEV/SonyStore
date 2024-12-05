let productByProdid = []; // type에 따른 제품 리스트
let productsByType = []; // prodid에 해당하는 제품 정보
let thumbnails = [];

window.onload = async (e) => {
    let response = null;
    let response2 = null;

    try {
        response = await axios.get(`http://localhost:8080/api/product-view/${prodid}`);
        

        productByProdid = response.data.product; // prodid에 해당하는 제품 정보

        if(productByProdid.type1){
            const type = productByProdid.type1;
            console.log(type);
            response2 = await axios.get(`http://localhost:8080/api/products/${type}`);
            productsByType = response2.data.list; // type에 따른 제품 리스트
            
        }
 
    }
    catch (error) {
        console.error(`[Error Code] ${error.code}`);
        console.error(`[Error message] ${error.message}`);
        let alertMsg = error.message;

        if (error.response !== undefined) {
            const errorMsg = `${error.response.status} ${error.response.statusText}`;
            console.error(`[HTTP Status] ${errorMsg}`);
            alertMsg += `\n${errorMsg}`;
        }

        alert(alertMsg);
        return;
    }



    console.log(productByProdid);
    console.log(productsByType);
    

    /* -- -- -- 메인 좌측 - 슬라이더 -- -- -- */
    
    const swiperWrapper1 = document.querySelector('.swiper-wrapper');
    const images = productByProdid.images;
    
    // pcolor인 colorid
    const primaryColor = productByProdid.colors.find(color => color.pcolor === 'Y' && color.colorid !== null)?.colorid;
    
    // pcolor인 이미지들
    const primaryImages = images.filter(img => img.colorid === primaryColor || img.colorid === null);
    
    // 썸네일 이미지
    const thumbnailImage = primaryImages.find(img => img.thumbnail === 'Y'); 
    
    // const otherImages = primaryImages.filter(img => img.thumbnail !== 'Y');
    thumbnails = images.filter(img => img.thumbnail === 'Y');
    
// console.log(thumbnails);
    if (thumbnailImage) {
        primaryImages[0] = thumbnailImage;
    }

    primaryImages.forEach(img => {
        const div1 = document.createElement('div');
        div1.classList.add('swiper-slide');
        div1.classList.add('slide-main');

        const div2 = document.createElement('div');
        div2.classList.add('img-container');

        const image = document.createElement('img');
        image.classList.add('main-img');
        image.setAttribute('src', img.filepath);

        div2.appendChild(image);
        div1.appendChild(div2);
        
        swiperWrapper1.appendChild(div1);
    });

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

        slides.forEach(() => {
            const bar = document.createElement('div');
            bar.classList.add('pagination-bar');
            paginationContainer.appendChild(bar);
        });
    }

    function updatePagination(activeIndex) {
        const bars = document.querySelectorAll('.pagination-bar');
        bars.forEach((bar, index) => {
            bar.classList.remove('active');
            if (index == activeIndex) {
                bar.classList.add('active');
            }
        });
    }

    /* -- -- -- 메인 우측 - 구매관련 -- -- -- */
    
    document.querySelector(".prd-name").innerHTML = productByProdid.title;
    
    document.querySelector(".prd-text").innerHTML = productByProdid.proddesc;
    
    const camPrice = productByProdid.price;
    document.querySelector(".cam-price").innerHTML = camPrice.toLocaleString();

    document.querySelector(".vip-mlg").innerHTML = (camPrice*0.04).toLocaleString();

    document.querySelector(".family-mlg").innerHTML = (camPrice*0.02).toLocaleString();

    /* -- -- 색상 -- -- */
    const colors = productByProdid.colors.sort((a, b) => b.pcolor.localeCompare(a.pcolor));
    if (colors.length > 1 && colors.some(color => color.colorid !== null)) {         
        const div = document.createElement('div');
        div.classList.add('color-select');
        document.querySelector('.product-cont-color-select').appendChild(div);

        const p = document.createElement('p');
        p.classList.add('tit');
        p.innerHTML = "색상";

        const ul = document.createElement('ul');
        ul.classList.add('circle-color-box');

        colors.forEach((color, i) => {
            if (color.colorid !== null) {
                const li = document.createElement('li');

                const a = document.createElement('a');
                a.classList.add('color-btn');
                a.classList.add(color.color);

                const span1 = document.createElement('span');
                span1.classList.add('circle-color');
                if (i == 0) span1.classList.add('active');

                const span2 = document.createElement('span');
                span2.classList.add('c-bg');

                span1.appendChild(span2);

                const span3 = document.createElement('span');
                span3.classList.add('color-name');
                if (i == 0) span3.classList.add('active');
                span3.innerHTML = color.color;

                a.appendChild(span1);
                a.appendChild(span3);
                
                li.appendChild(a);

                ul.appendChild(li);
            }
        });

        div.appendChild(p);
        div.appendChild(ul);
        
        document.querySelectorAll('.color-btn').forEach((v, i) => {
            v.addEventListener('click', e => {
                e.preventDefault();
                const clickIdx = i;
                const selectedColorId = colors[clickIdx].colorid;
                const selectedImages = images.filter(img => img.colorid === selectedColorId);
                const selectedThumbnailImage = selectedImages.find(img => img.thumbnail === 'Y');
                // const selectedOtherImages = selectedImages.filter(img => img.thumbnail !== 'Y');

                if (selectedThumbnailImage) {
                    selectedImages.unshift(selectedThumbnailImage);
                }

                swiperWrapper1.innerHTML = '';
                selectedImages.forEach(img => {
                    const div1 = document.createElement('div');
                    div1.classList.add('swiper-slide');
                    div1.classList.add('slide-main');

                    const div2 = document.createElement('div');
                    div2.classList.add('img-container');

                    const image = document.createElement('img');
                    image.classList.add('main-img');
                    image.setAttribute('src', img.filepath);

                    div2.appendChild(image);
                    div1.appendChild(div2);
                    
                    swiperWrapper1.appendChild(div1);
                });

                swiper1.update();
                createPagination();

                document.querySelectorAll('.circle-color').forEach((v1, i1) => {
                    if (clickIdx == i1) {
                        v1.classList.add('active');
                    } else {
                        v1.classList.remove('active');
                    }
                });
                document.querySelectorAll('.color-name').forEach((v2, i2) => {
                    if (clickIdx == i2) {
                        v2.classList.add('active');
                    } else {
                        v2.classList.remove('active');
                    }
                });
            });
        });
    }

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

    /* 제품을 선택하세요 */
    const ulSelectInner = document.querySelector('.select-inner');
    
    const colorArr = productByProdid.colors.filter(color => color.colorid).map(color => color.color); // 색상 배열 생성
    const colorNum = colorArr.length;
    if(colorArr == 0){
        const li = document.createElement('li');
        li.classList.add('inner-list');
        
        const a = document.createElement('a');
        
        li.appendChild(a);
        ulSelectInner.appendChild(li);
    }
    for (let i = 0; i < colorNum; i++) {
        const li = document.createElement('li');
        li.classList.add('inner-list');
        
        const a = document.createElement('a');
        
        li.appendChild(a);
        ulSelectInner.appendChild(li);
    } 


    document.querySelectorAll('.inner-list').forEach((v, i) => {
        let span1, span2;
        if (colorNum > 1) {
            span1 = document.createElement('span');
            span1.classList.add('circle-prd-clr');
            span1.classList.add('pad');

            circleColor(span1);                                        

            const colorName = colorArr[i];
            span1.classList.add(colorName); 
            
            span2 = document.createElement('span');
            span2.classList.add('submenu-txt');
            span2.classList.add('pad');
            span2.innerHTML = `${productByProdid.title} / ${colorName}`;
            span2.dataset.index = i;
            span2.dataset.color = colorName;
        } else {
            span2 = document.createElement('span');
            span2.classList.add('submenu-txt');
            span2.classList.add('pad');
            span2.innerHTML = `제품명: ${productByProdid.title}`;
        }
        const a = v.children[0];
        if (span1) a.appendChild(span1);
        a.appendChild(span2);
    });

    document.querySelector('.selected-btn').addEventListener('click', e => {
        e.preventDefault();
        const current = e.currentTarget;
        current.classList.toggle('active');
        ulSelectInner.classList.toggle('active');       
    });

    /* -- -- 제품 선택 - 선택된 제품 박스 -- -- */
    const innerList = document.querySelectorAll('.inner-list'); // submenu list 선택할 제품 목록
    innerList.forEach((v, i) => {    
        v.addEventListener('click', e => {
            e.preventDefault();
            const current = e.currentTarget; // 클릭한 선택한 제품 ex) "ILCE-7CM2 / 실버"
            
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

                
        
                if (innerList.length > 1) {
                    const circlePrdClr = current.querySelector('.circle-prd-clr');
                    div4.appendChild(circlePrdClr.cloneNode(true));
                }
                
                const submenuTxt = current.querySelector('.submenu-txt');
                div4.appendChild(submenuTxt.cloneNode(true));

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
                input.setAttribute('type', 'text');
                input.setAttribute('value', 1);
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
                div7.innerHTML = `${(productByProdid.price).toLocaleString()}원`;

                div5.appendChild(div6);
                div5.appendChild(div7);

                div2.appendChild(div3);
                div2.appendChild(div4);
                div2.appendChild(div5);

                div1.appendChild(div2);
                
                document.querySelector('.prd-select-box').appendChild(div1);

                a.addEventListener('click', (e) => {
                    e.preventDefault();
                    div1.remove(); // 해당 선택된 옵션 삭제                     
                }); 
            };  

            // 제품 선택 박스 중복 방지            
            const existingOpt = Array.from(document.querySelectorAll('.selected-opt')).some(opt => {
                if (innerList.length > 1) {
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
        });
    });


    /* -- -- -- -- 색상이 없는 제품 -- -- -- -- */
    



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


    /* -- -- -- 제품개요 -- -- -- */

    // 제품 개요 이미지 및 동영상 설정
    document.querySelector('.sony-content.pad').innerHTML = `<img src="${productByProdid.detailimage1}" />`;
    document.querySelector('.video-wrap.pad').innerHTML = `<iframe width="960" height="540" src="${productByProdid.youtube}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen=""></iframe>`;
    document.querySelector('.sony-content-moving.pad').innerHTML = `<img src="${productByProdid.detailgif}" alt="제품 개요" />`;
    document.querySelector('.sony-content-detail.pad').innerHTML = `<img src="${productByProdid.detailimage2}" alt="제품 개요" />`;
    document.querySelector('.detail-box').innerHTML = `<img src="${productByProdid.detailspec}" alt="제품 사양" />`;


    /* -- -- 함께 구매하시면 좋은 추천 제품 -- -- */

    const productList = productsByType; // productsByType에서 제품 리스트 가져오기
    
    //  현재 제품 제외한 제품 배열 dataArr
    let dataArr;
    productList.some((v, i) => {
        if (v.prodid == productByProdid.prodid) {
            dataArr = productList.slice(0, i).concat(productList.slice(i + 1));
            return true;
        }
    });
    
    const randomData = dataArr.sort(() => 0.5 - Math.random()).slice(0, 10); // 랜덤으로 10개 제품 선택
    
    //  슬라이드 생성
    const swiperWrapperRcmd = document.querySelector('.swiper-wrapper-rcmd');

    randomData.forEach((v, i) => {
        const swiperSlide = document.createElement('div');
        swiperSlide.classList.add('swiper-slide');

        const rcmd_img_container = document.createElement('div');
        rcmd_img_container.classList.add('recommend-img-container');
        
        const a = document.createElement('a');
        a.setAttribute('href', `http://localhost:8080/product-view/${v.prodid}`);

        const thumbnailImage = v.images.find(img => img.thumbnail === 'Y');
        const img = document.createElement('img');
        img.classList.add('recommend-img');
        img.setAttribute('src', thumbnailImage ? thumbnailImage.filepath : v.detailimage1);

        a.appendChild(img);
        
        const span1 = document.createElement('span');
        span1.classList.add('recommend-title');
        span1.innerHTML = v.title;
        
        const span2 = document.createElement('span');
        span2.classList.add('recommend-desc');
        span2.innerHTML = v.proddesc;
        
        const span3 = document.createElement('span');
        span3.classList.add('recommend-price');
        span3.innerHTML = v.price.toLocaleString() + '원';

        rcmd_img_container.appendChild(a);
        rcmd_img_container.appendChild(span1);
        rcmd_img_container.appendChild(span2);
        rcmd_img_container.appendChild(span3);
        swiperSlide.appendChild(rcmd_img_container);
        swiperWrapperRcmd.appendChild(swiperSlide);
    });

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
    
};



/* == == == == == 장바구니 담기 == == == == == */

document.querySelector('.cart.btn-icon-container').addEventListener('click', async e => {
    e.preventDefault();

    if( !document.querySelector('.selected-opt-container') ) {
        alert('제품을 선택해주세요.');
        return;
    } else if (!document.querySelector('#memberid')) {
        alert('로그인 후 이용해주세요.');
        return;
    } else {
        const formData = new FormData();

        document.querySelectorAll('.selected-opt').forEach( (v,i) => {
            const idx = v.querySelector('.submenu-txt.pad').dataset.index;

            formData.append('prodid', productByProdid.prodid);
            formData.append('color', v.querySelector('.submenu-txt.pad').dataset.color? v.querySelector('.submenu-txt.pad').dataset.color : null);    
            formData.append('count', parseInt(v.querySelector('.cnt-box.count').value));
        } );

        let data = await axiosHelper.post( 'http://localhost:8080/api/cart/add', formData );

        window.location = '/cart';
    }
});


/* == == == == == 바로 구매하기 == == == == == */

document.querySelector('.buy_now').addEventListener( 'click', async e => {
    e.preventDefault();

    if( !document.querySelector('.selected-opt-container') ) {
        alert('제품을 선택해주세요.');
        return;
    } else if (!document.querySelector('#memberid')) {
        alert('로그인 후 주문해주세요.');
        return;
    } else {
        let totalCount = 0;
        const formData = new FormData();

        document.querySelectorAll('.selected-opt').forEach( (v,i) => {
            const idx = v.querySelector('.submenu-txt.pad').dataset.index;

            formData.append('prodid', productByProdid.prodid);
            formData.append('prodthumbnail', idx? thumbnails[idx].filepath : thumbnails[0].filepath);
            formData.append('prodtitle', productByProdid.title);
            formData.append('prodcolor', v.querySelector('.submenu-txt.pad').dataset.color? v.querySelector('.submenu-txt.pad').dataset.color : null);    
            const cnt = parseInt(v.querySelector('.cnt-box.count').value.replaceAll(',', ''));
            formData.append('count', cnt);
            formData.append('prodprice', productByProdid.price);
            totalCount += cnt;
        } );
        
        formData.append('totalcount', totalCount);
        formData.append('total', parseInt(document.querySelector('.result-price .num').innerText.replaceAll(',', '')));

        let data = await axiosHelper.post( 'http://localhost:8080/api/order', formData );
        // let data = await axiosHelper.post( '[[@{/api/order_by_detail}]]', formData );

        window.location = `/order/sheet?orderSheetNo=${data.item.payid}`;
    }
} );