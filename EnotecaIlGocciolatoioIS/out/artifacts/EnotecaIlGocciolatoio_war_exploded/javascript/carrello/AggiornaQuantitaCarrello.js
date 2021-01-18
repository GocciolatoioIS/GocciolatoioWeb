function save(id,quantita) {
    //id = parseInt(document.getElementById('idProdotto').value, 10);
    //quantita = parseInt(document.getElementById('quantProd').value, 10[1]);
    //var prova=document.getElementById("idProdotto").innerHTML;
    $.ajax({
        url: "carrello",
        data: "prodId="+id+"&setNum="+quantita,
        type: 'get',
        success: function (response) {

        }
    });
}