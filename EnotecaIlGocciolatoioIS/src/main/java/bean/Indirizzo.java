package bean;

import java.util.Objects;

public class Indirizzo {
    private int id;
    private String citta;
    private int cap;
    private String via;
    private int numCivico;
    private String nazione;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public int getCap() {
        return cap;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public int getNumCivico() {
        return numCivico;
    }

    public void setNumCivico(int numCivico) {
        this.numCivico = numCivico;
    }

    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    public Indirizzo(int id, String citta, int cap, String via, int numCivico, String nazione) {
        this.id = id;
        this.citta = citta;
        this.cap = cap;
        this.via = via;
        this.numCivico = numCivico;
        this.nazione = nazione;
    }

    public Indirizzo() {
        this.id = -1;
        this.citta = null;
        this.cap = -1;
        this.via = null;
        this.numCivico = -1;
        this.nazione = null;
    }

    public String getFullIndirizzo(){
        return "("+this.citta+")"+this.getVia()+" "+this.getNumCivico()+"("+this.getNazione()+")"+this.getCap();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Indirizzo indirizzo = (Indirizzo) o;
        return id == indirizzo.id && cap == indirizzo.cap && numCivico == indirizzo.numCivico && Objects.equals(citta, indirizzo.citta) && Objects.equals(via, indirizzo.via) && Objects.equals(nazione, indirizzo.nazione);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, citta, cap, via, numCivico, nazione);
    }

    @Override
    public String toString() {
        return "Indirizzo{" +
                "id=" + id +
                ", citta='" + citta + '\'' +
                ", cap=" + cap +
                ", via='" + via + '\'' +
                ", numCivico=" + numCivico +
                ", nazione='" + nazione + '\'' +
                '}';
    }
}
