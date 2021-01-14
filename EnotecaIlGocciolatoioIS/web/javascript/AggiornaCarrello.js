$(document).ready(function() {

    /* Set rates */
    var taxRate = 0.05;
    var fadeTime = 300;

    /* Assign actions */
    $('.pass-quantity input').change(function() {
        updateQuantity(this);
    });

    /* Update quantity */
    function updateQuantity(quantityInput) {
        /* Calculate line price */
        var productRow = $(quantityInput).parent().parent();
        //var price = parseFloat(productRow.children('.product-price').text());
        var quantity = $(quantityInput).val();
        //var linePrice = price * quantity;

        /* Update line price display and recalc cart totals */
        productRow.children('.quantity').each(function() {
            $(this).fadeOut(fadeTime, function() {
                $(this).text(linePrice.toFixed(2));
                //recalculateCart();
                $(this).fadeIn(fadeTime);
            });
        });
    }

});

/*
function increaseValue() {
    var value = parseInt(document.getElementById('number').value, 10);
    value = isNaN(value) ? 0 : value;
    value++;
    document.getElementById('number').value = value;
}

function decreaseValue() {
    var value = parseInt(document.getElementById('number').value, 10);
    value = isNaN(value) ? 0 : value;
    value < 1 ? value = 1 : '';
    value--;
    document.getElementById('number').value = value;
} */