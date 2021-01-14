$(document).ready(function() {

    var id = parseInt(document.getElementById('idProdotto').value, 10);
    var quantita = parseInt(document.getElementById('quantProd').value, 10);

    /* Set rates */
    var fadeTime = 300;

    /* Assign actions */
    $('.pass-quantity input').change(function() {
        updateQuantity(this);
        asyncRequest = new XMLHttpRequest();
        asyncRequest.open('GET', '/carrello', true);    //   /Test is url to Servlet!
        save_to_db(id,quantita);
    });

    $('.remove-item button').click(function() {
        removeItem(this);
    });

    //Recalculate cart
    function recalculateCart() {
        var subtotal = 0;

        // Sum up row totals
        $('.item').each(function() {
            subtotal += parseInt($(this).children('.product-line-price').text());
        });

        //Calculate totals
        var tax = subtotal * 1;
        var total = subtotal + 7.90;

        //Update totals display
        $('.totals-value').fadeOut(fadeTime, function() {
            $('#cart-subtotal').html(subtotal.toFixed(2));
            $('.cart-total').html(total.toFixed(2));
            if (total == 0) {
                $('.checkout').fadeOut(fadeTime);
            } else {
                $('.checkout').fadeIn(fadeTime);
            }
            $('.totals-value').fadeIn(fadeTime);
            //save_to_db(id,quantita);
        });
    }

    /* Update quantity */
    function updateQuantity(quantityInput) {
        /* Calculate line price */
        var productRow = $(quantityInput).parent().parent();
        var price = parseFloat(productRow.children('.product-price').text());
        var quantity = $(quantityInput).val();
        var linePrice = price * quantity;

        /* Update line price display and recalc cart totals */
        productRow.children('.product-line-price').each(function() {
            $(this).fadeOut(fadeTime, function() {

                $(this).text(linePrice.toFixed(2));
                recalculateCart();
                $(this).fadeIn(fadeTime);

                //save_to_db(id,quantita);

            });
        });
    }



});