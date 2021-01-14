function FormValidation(){

    var citta = document.registration.citta;
    var cap = document.registration.cap;
    var via = document.registration.via;
    var ncivico = document.registration.ncivico;

            if(allLetterCitta(citta)){
                if(allNumbersCap(cap)){
                    if(allLetterVia(via)){
                        if(allNumbersCivico(ncivico)){
                            return true;
                        }
                    }
                }
            }
    return false;
}

function allLetterCitta(citta){
    var letters = /^[A-Za-z]+$/;
    if(citta.value.match(letters)){
        return true;
    }else{
        alert('Il campo Citta deve essere compilato con sole lettere');
        citta.focus();
        return false;
    }
}

function allNumbersCap(cap){
    var letters = /^[0-9]+$/;
    if(cap.value.match(letters)){
        return true;
    }else{
        alert('Il campo Cap deve essere compilato con soli numeri');
        cap.focus();
        return false;
    }
}

function allLetterVia(via){
    var letters = /^[0-9A-Za-z]+$/;
    if(via.value.match(letters)){
        return true;
    }else{
        alert('Il campo Via deve essere compilato');
        via.focus();
        return false;
    }
}

function allNumbersCivico(ncivico){
    var letters = /^[0-9A-Za-z]+$/;
    if(ncivico.value.match(letters)){
        return true;
    }else{
        alert('Il campo Numero Civico deve essere compilato');
        ncivico.focus();
        return false;
    }
}