const mapButton = document.querySelector('.map_open_button');
const mapDiv = document.getElementById('map');

mapButton.addEventListener('click', function() {
  if (mapDiv.style.display === 'none') {
    mapDiv.style.display = 'block';
    mapButton.textContent = '지도 닫기';
  } else {
    mapDiv.style.display = 'none';
    mapButton.textContent = '지도 보기';
  }
});