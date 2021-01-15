function validaFormLogin() {

    var email = document.getElementById('username');
    var emailReg = /^(([^<>()[]\.,;:\s@"]+(.[^<>()[]\.,;:\s@"]+))|(".+"))@(([[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}])|(([a-zA-Z-0-9]+.)+[a-zA-Z]{2,}))$/;
    var password = document.getElementById('pass');
    // var passwordReg =
    // /^(?=.[a-z])(?=.[A-Z])(?=.\d)(?=.[@$!%?&])[A-Za-z\d@$!%*?&]{8,}$/;

    if(email.value == null || email.value == ""){
        $("span").text("");
        $("#checkEmail").text("Campo email vuoto");
        $("#checkEmail").css("color", "red");
        return false;
    }else if (!emailReg.test(email.value)) {
        $("#checkEmail").text("");
        $("#checkEmail").text("Formato email errato");
        $("#checkEmail").css("color", "red");
        console.log("Failure");
        return false;
    } else {
        if (password.value == null|| password.value == "") {
            $("span").text("");
            $("#checkPassword").text("Campo password vuoto");
            $("#checkPassword").css("color", "red");
            return false;
        }
    }
}