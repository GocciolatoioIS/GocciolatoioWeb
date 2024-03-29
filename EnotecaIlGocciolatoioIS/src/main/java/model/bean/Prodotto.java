package model.bean;

public class Prodotto {
    private int id;
    private String nome;
    private String descrizione;
    private String tipo;
    private double prezzo;
    private double sconto;
    private String immagine;
    private String nome_categoria;
    private int anno;
    private String regione;
    private int gradazione;
    private int formato;
    private int quantita_magazzino;



    public Prodotto(int id, String nome,  String tipo, String descrizione, double prezzo, double sconto, String immagine, int anno, String regione, int gradazione, int formato, int quantita_magazzino, String nome_categoria) {
        this.id = id;
        this.nome = nome;
        this.tipo=tipo;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.sconto=sconto;
        this.immagine = immagine;
        this.anno=anno;
        this.regione=regione;
        this.gradazione=gradazione;
        this.formato=formato;
        this.quantita_magazzino=quantita_magazzino;
        this.nome_categoria = nome_categoria;
    }

    public Prodotto(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public double getSconto() {
        return sconto;
    }

    public void setSconto(double sconto) {
        this.sconto = sconto;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    public String getNome_categoria() {
        return nome_categoria;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public String getRegione() {
        return regione;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    public int getGradazione() {
        return gradazione;
    }

    public void setGradazione(int gradazione) {
        this.gradazione = gradazione;
    }

    public int getFormato() {
        return formato;
    }

    public void setFormato(int formato) {
        this.formato = formato;
    }

    public void setNome_categoria(String nome_categoria) { this.nome_categoria = nome_categoria; }

    public int getQuantita_magazzino() {
        return quantita_magazzino;
    }

    public void setQuantita_magazzino(int quantita_magazzino) {
        this.quantita_magazzino = quantita_magazzino;
    }

}
