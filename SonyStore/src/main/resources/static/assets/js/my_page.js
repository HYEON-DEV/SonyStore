/* = = = = = 주문 내역 조회 날짜 = = = = = */

var today = new Date();
const date1 = document.querySelector(".date1");
date1.value = `${today.getFullYear() - 1}-${String(
  today.getMonth() + 1
).padStart(2, "0")}-${String(today.getDate()).padStart(2, "0")}`;
document.querySelector(".date2").value = `${today.getFullYear()}-${String(
  today.getMonth() + 1
).padStart(2, "0")}-${String(today.getDate()).padStart(2, "0")}`;

document.querySelectorAll(".term").forEach((v, i) => {
  v.addEventListener("click", (e) => {
    document.querySelectorAll(".term").forEach((w, j) => {
      if (!v.classList.contains("active")) {
        v.classList.add("active");
      }

      if (i != j) {
        if (w.classList.contains("active")) {
          w.classList.remove("active");
        }
      }
    });

    if (v.classList.contains("active")) {
      switch (i) {
        case 0:
          date1.value = `${today.getFullYear()}-${String(
            today.getMonth() - 2
          ).padStart(2, "0")}-${String(today.getDate()).padStart(
            2,
            "0"
          )}`;
          break;
        case 1:
          date1.value = `${today.getFullYear()}-${String(
            today.getMonth() - 5
          ).padStart(2, "0")}-${String(today.getDate()).padStart(
            2,
            "0"
          )}`;
          break;
        case 2:
          date1.value = `${today.getFullYear() - 1}-${String(
            today.getMonth() + 1
          ).padStart(2, "0")}-${String(today.getDate()).padStart(
            2,
            "0"
          )}`;
          break;
      }
    }
  });
});


/* = = = = = 전체 체크박스 = = = = = */

document.addEventListener('DOMContentLoaded', function() {
  const allCheckBox = document.getElementById('allChk');
  const itemCheckBoxes = document.querySelectorAll('.like_prod .inp_check');

  allCheckBox.addEventListener('change', function() {
    itemCheckBoxes.forEach(function(checkbox) {
      checkbox.checked = allCheckBox.checked;
    });
  });
});


/* = = = = = 진행 중인 주문 건수 = = = = = */

document.querySelectorAll('.order_step_cnt').forEach( (v,i) => {
  if ( v.querySelector('.order_step_cnt_num').textContent > 0 ) {
      v.style.textDecoration = 'underline';
      v.style.color = 'var(--color-blue)';
      v.style.fontSize = '20px';
  }    
} );