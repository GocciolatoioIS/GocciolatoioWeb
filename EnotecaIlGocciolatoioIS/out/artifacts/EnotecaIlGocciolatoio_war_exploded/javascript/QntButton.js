function increaseValue() {
  var value = parseInt(document.getElementById('number').value, 10);
  var quanta = document.getElementById('quantity').innerHTML;


  value = isNaN(value) ? 0 : value;
  value++;
  value > quanta? value = quanta: "";
  if(value === quanta){
    alert("Ulteriori quantità al momento non disponibili!");
  }
  document.getElementById('number').value = value;
}

function decreaseValue() {
  var value = parseInt(document.getElementById('number').value, 10);
  value = isNaN(value) ? 0 : value;
  value < 1 ? value = 1 : '';
  value--;
  document.getElementById('number').value = value;
}

