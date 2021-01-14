package bean;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class Utente {
    private int id;
    private String email;
    private String username;
    private String pass;
    private String ruolo;
    private String nome;
    private String cognome;
    private Date data_nascita ;
    private List<Indirizzo> indirizzoList;
    //private List<MetodoPagamento> metodoPagamentosList;
    public Utente(){}

    public Utente(int id, String email, String username, String pass, String ruolo, String nome, String cognome, String data_nascita,List<Indirizzo> indirizzoList) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.pass = pass;
        this.ruolo = ruolo;
        this.nome = nome;
        this.cognome=cognome;
        this.indirizzoList=indirizzoList;
        //this.metodoPagamentosList=metodoPagamentosList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String password) {
        //password è  inserita dall’utente
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(password.getBytes(StandardCharsets.UTF_8));
            this.pass = String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (NoSuchAlgorithmException e)
        { throw new RuntimeException(e); }
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }



    public String getCognome() {
        return cognome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setData_nascita(Date data_nascita){
        this.data_nascita=data_nascita;
    }

    public Date getData_nascita(){return  data_nascita;}

    public String getNome() {
        return nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public List<Indirizzo> getIndirizzoList() {
        return indirizzoList;
    }

    public void setIndirizzoList(List<Indirizzo> indirizzoList) {
        this.indirizzoList = indirizzoList;
    }

    public void addIndirizzoToList(Indirizzo i){
        this.indirizzoList.add(i);
    }

    public void removeIndirizzoToList(Indirizzo i){
        this.indirizzoList.remove(i);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utente utente = (Utente) o;
        return id == utente.id && Objects.equals(email, utente.email) && Objects.equals(username, utente.username) && Objects.equals(pass, utente.pass) && Objects.equals(ruolo, utente.ruolo) && Objects.equals(nome, utente.nome) && Objects.equals(cognome, utente.cognome) && Objects.equals(data_nascita, utente.data_nascita) && Objects.equals(indirizzoList, utente.indirizzoList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, username, pass, ruolo, nome, cognome, data_nascita, indirizzoList);
    }

    @Override
    public String toString() {
        return "Utente{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", pass='" + pass + '\'' +
                ", ruolo='" + ruolo + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", data_nascita=" + data_nascita +
                ", indirizzoList=" + indirizzoList +
                '}';
    }
}
