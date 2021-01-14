function FormValidation(){

    var username = document.registration.username;
    var email = document.registration.email;
    var password = document.registration.pass;

    if(allLetterUsername(username)){
        if(password_validation(password,7,12))
        {
            return true;
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

function password_validation(password,mx,my){
    var password_len = password.value.length;
    if (password_len == 0 ||password_len >= my || password_len < mx){
        alert("La lunghezza deve essere compresa tra "+mx+" to "+my);
        password.focus();
        return false;
    }else{
        window.location.reload()
        return true;
    }
}