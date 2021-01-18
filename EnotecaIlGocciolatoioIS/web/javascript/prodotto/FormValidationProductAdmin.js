function FormValidation(){

    var nome=document.registration.nome;
    var descrizione=document.registration.descrizione;
    var anno=document.registration.anno;
    var regione=document.registration.regione;
    var gradazione=document.registration.gradazione;
    var formato=document.registration.formato;
    var quantita_magazzino=document.registration.quantita_magazzino;
    var tipo=document.registration.tipo;
    var prezzo=document.registration.prezzo;
    var sconto=document.registration.sconto;

    if(allLetterName(nome)){
        if(allLetterDescr(descrizione)){
            if(allNumberDate(anno)){
                if(allLetterRegione(regione)){
                    if(allNumbersGradazione(gradazione)){
                        if(allNumbersFormato(formato)){
                            if(allNumbersQuantita(quantita_magazzino)){
                                if(allLetterTipo(tipo)){
                                    if(allNumbersPrezzo(prezzo)){
                                        if(allNumbersSconto(sconto)){
                                            return true;
                                        }

                                    }

                                }

                            }

                        }

                    }

                }
            }
        }
    }
    return false;
}

function allLetterName(nome){
    var letters = /^[0-9A-Za-z]+$/;
    if(nome.value.match(letters)){
        return true;
    }else{
        alert('Il campo Nome deve essere compilato');
        nome.focus();
        return false;
    }
}

function allLetterDescr(descrizione){
    var letters = /^[0-9A-Za-z]+$/;
    if(descrizione.value.match(letters)){
        return true;
    }else{
        alert('Il campo Descrizione deve essere compilato');
        descrizione.focus();
        return false;
    }
}

function allNumberDate(anno){
    var letters = /^[0-9]+$/;
    if(anno.value.match(letters)){
        return true;
    }else{
        alert('Il campo Anno deve essere compilato con soli numeri');
        anno.focus();
        return false;
    }
}

function allLetterRegione(regione){
    var letters = /^[A-Za-z]+$/;
    if(regione.value.match(letters)){
        return true;
    }else{
        alert('Il campo Regione deve essere compilato con sole lettere');
        regione.focus();
        return false;
    }
}

function allNumbersGradazione(gradazione){
    var letters = /^[0-9]+$/;
    if(gradazione.value.match(letters)){
        return true;
    }else{
        alert('Il campo Gradazione deve essere compilato con soli numeri');
        gradazione.focus();
        return false;
    }
}

function allNumbersFormato(formato){
    var letters = /^[0-9]+$/;
    if(formato.value.match(letters)){
        return true;
    }else{
        alert('Il campo Formato deve essere compilato con soli numeri');
        formato.focus();
        return false;
    }
}

function allNumbersQuantita(quantita_magazzino){
    var letters = /^[0-9]+$/;
    if(quantita_magazzino.value.match(letters)){
        return true;
    }else{
        alert('Il campo Quantita deve essere compilato con soli numeri');
        quantita_magazzino.focus();
        return false;
    }
}

function allLetterTipo(tipo){
    var letters = /^[A-Za-z]+$/;
    if(tipo.value.match(letters)){
        return true;
    }else{
        alert('Il campo Tipo deve essere compilato con sole lettere');
        tipo.focus();
        return false;
    }
}

function allNumbersPrezzo(prezzo){
    var letters = /^[0-9]+$/;
    if(prezzo.value.match(letters)){
        return true;
    }else{
        alert('Il campo Quantita deve essere compilato con soli numeri');
        qprezzo.focus();
        return false;
    }
}

function allNumbersSconto(sconto){
    var letters = /^[0-9]+$/;
    if(sconto.value.match(letters)){
        return true;
    }else{
        alert('Il campo Quantita deve essere compilato con soli numeri');
        sconto.focus();
        return false;
    }
}