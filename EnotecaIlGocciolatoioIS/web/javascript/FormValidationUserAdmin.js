function FormValidation(){

    var username = document.registration.username;
    var email = document.registration.email;
    var password = document.registration.pass;
    var citta = document.registration.citta;
    var cap = document.registration.cap;
    var via = document.registration.via;
    var ncivico = document.registration.ncivico;

    if(allLetterUsername(username)){
        if(password_validation(password,7,12)){
            if(allLetterCitta(citta)){
                if(allNumbersCap(cap)){
                    if(allLetterVia(via)){
                        if(allNumbersCivico(ncivico)){
                            return true;
                        }
                    }
                }
            }
        }
    }
    return false;
}

function allLetterUsername(username){
    var letters = /^[0-9A-Za-z]+$/;
    if(username.value.match(letters)){
        return true;
    }else{
        alert('Il campo Username deve essere compilato');
        username.focus();
        return false;
    }
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

function password_validation(password,mx,my){
    var password_len = password.value.length;
    if (password_len == 0 ||password_len >= my || password_len < mx){
        alert("La lunghezza deve essere compresa tra "+mx+" to "+my);
        password.focus();
        return false;
    }else{
        alert('Form Succesfully Submitted');
        window.location.reload()
        return true;
    }
}