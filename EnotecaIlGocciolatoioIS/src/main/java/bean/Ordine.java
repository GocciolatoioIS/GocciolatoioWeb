package bean;

import java.util.Date;

public class Ordine {

    private int id_ordine;
    private int id_utente;
    private int id_indirizzo;
    private Date data_ordine;
    private Indirizzo indirizzo;
    private Utente utente;

    public Ordine(int id_ordine, int id_utente, int id_indirizzo, Date data_ordine, Indirizzo indirizzo, Utente utente) {
        this.id_ordine = id_ordine;
        this.id_utente = id_utente;
        this.id_indirizzo = id_indirizzo;
        this.data_ordine = data_ordine;
        this.indirizzo = indirizzo;
        this.utente = utente;
    }

    public Ordine(){}

    public int getId_ordine() {
        return id_ordine;
    }

    public void setId_ordine(int id_ordine) {
        this.id_ordine = id_ordine;
    }

    public int getId_utente() {
        return id_utente;
    }

    public void setId_utente(int id_utente) {
        this.id_utente = id_utente;
    }

    public Date getData_ordine() {
        return data_ordine;
    }

    public void setData_ordine(Date data_ordine) {
        this.data_ordine = data_ordine;
    }

    public int getId_indirizzo() {
        return id_indirizzo;
    }

    public void setId_indirizzo(int id_indirizzo) {
        this.id_indirizzo = id_indirizzo;
    }

    public Indirizzo getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(Indirizzo indirizzo) {
        this.indirizzo = indirizzo;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
}


