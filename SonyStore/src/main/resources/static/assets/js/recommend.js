const swiperWrapper = document.querySelector(".swiper-wrapper");

const swiper = new Swiper(".swiper", {
  // Swiper 옵션 설정
  direction: "horizontal",
  loop: false,
  slidesPerView: 2,
  spaceBetween: 10,
  navigation: {
    nextEl: ".custom-next",
    prevEl: ".custom-prev",
  },
  on: {
    init: function () {
      nonDisplayNavigationButtons(this);
    },
    slideChange: function () {
      nonDisplayNavigationButtons(this);
    },
  },
});

function nonDisplayNavigationButtons(swiper) {
  const nextButton = document.querySelector('.custom-next');
  const prevButton = document.querySelector('.custom-prev');

  if (swiper.isEnd) {
    nextButton.style.display = 'none';
  } else {
    nextButton.style.display = 'block';
  }

  if (swiper.isBeginning) {
    prevButton.style.display = 'none';
  } else {
    prevButton.style.display = 'block';
  }
}