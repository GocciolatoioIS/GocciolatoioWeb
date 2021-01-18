function ricerca(str){
         $.ajax({
             url: 'JsonResponse',
             data:"",
             datatype: "Json",
             type:"Post",

             success: function(responseJson) {

                 //Crea una box in cui usciranno i prodotti
                 if (str.length == 0) {
                     document.getElementById("search-layer").innerHTML = "";
                     document.getElementById("search-layer").style.border = "0px";
                     return;
                 }

                 $("#search-layer").children().remove();//pulisce la livesearch per non creare copie di dati

                 document.getElementById("search-layer").style.border = "1px solid #A5ACB2";
                 //inserisce tutti gli item ritornati dalla servlet nella barra di ricerca con un link che lo reinderizza alla pagina relativa

                 $.each(responseJson, function (index, item) {
                      if (item.nome.indexOf(str.toUpperCase())>=2)
                         var $input = $("<a href='show-product?product=" + item.id + "'>" + item.nome + "</a><br>").appendTo("#search-layer");
                 })
             },
            //se non ha successo viene eseguito un allert
             error: function(responseText){
                    alert("error");
              }

         });
}

