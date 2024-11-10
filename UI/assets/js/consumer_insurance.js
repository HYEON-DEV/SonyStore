document.addEventListener("DOMContentLoaded", function () {
  const insuranceLink = document.getElementById("insurance-link");

  insuranceLink.addEventListener("click", function (event) {
    event.preventDefault();
    Swal.fire({
      html: `
        <div class="layer_container" style="padding: 20px; background-color: #fff; border-radius: 8px;">
          <div class="layer_title">
            <h1 style="font-size: 28px; font-weight: 700; color: #222; margin-right: auto; margin-bottom: 20px;">소비자 피해 보상보험</h1>
          </div>
          <div class="layer_content" style="margin-top: 20px;">
            <div class="consumer_logo" style="width: 556px; height: 81px; margin-bottom: 35px; border: 1px solid #ddd; display: flex; align-items: center; justify-content: center;">
              <img src="/assets/img/consumer_insurance/img_consumerlogo.png" alt="로고" />
            </div>
            <p style="color: #444; font-size: 16px; line-height: 24px; margin-right: 0; display: flex; align-items: center; justify-content: center;">
              고객님은 안전거래를 위해 현금 결제 시 저희 쇼핑몰이 가입한 구매안전서비스 소비자 피해 보상보험서비스를 이용하실 수 있습니다.
            </p>
            <p style="color: #444; font-size: 16px; line-height: 24px; margin-right: 0;">보상대상 : 미배송/반품, 환불거부/쇼핑몰부도</p>
            <div class="btn_box" style="margin-top: 20px;">
              <a href="https://mall.sgic.co.kr/csh/iutf/sh/shop/CSHINFO004VM0.mvc?tm=3&amp;q_sk=2&amp;q_sv=1068123810" target="_blank" title="새창열림" rel="noreferrer" style="display: inline-block; padding: 15px 34px; background-color: black; color: #fff; font-weight: 700; text-decoration: none;">
                서비스 가입 사실 확인하기
              </a>
            </div>
          </div>
          <button class="layer_close close" title="팝업창 닫기" style="display: none;">
            <span>팝업창 닫기</span>
          </button>
        </div>
      `,
      showCloseButton: true, // 오른쪽 상단 x 버튼 유무
      width: "636px", // SweetAlert 창의 너비 설정
      showConfirmButton: false, // 확인 버튼 유무
    });
  });
});