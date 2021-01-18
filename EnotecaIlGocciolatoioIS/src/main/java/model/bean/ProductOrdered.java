package model.bean;

import java.util.Objects;

public class ProductOrdered
{
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
	private int quantita;
	private int id_ordine;

	public ProductOrdered() { }

	public ProductOrdered(int id, String nome, String tipo,String descrizione,  double prezzo, double sconto, String immagine, String nome_categoria, int anno, String regione, int gradazione, int formato, int quantita, int id_ordine) {
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.tipo = tipo;
		this.prezzo = prezzo;
		this.sconto = sconto;
		this.immagine = immagine;
		this.nome_categoria = nome_categoria;
		this.anno = anno;
		this.regione = regione;
		this.gradazione = gradazione;
		this.formato = formato;
		this.quantita = quantita;
		this.id_ordine = id_ordine;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public void setNome_categoria(String nome_categoria) {
		this.nome_categoria = nome_categoria;
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

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita_magazzino) {
		this.quantita = quantita_magazzino;
	}

	public int getId_ordine() {
		return id_ordine;
	}

	public void setId_ordine(int id_ordine) {
		this.id_ordine = id_ordine;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ProductOrdered that = (ProductOrdered) o;
		return id == that.id && Double.compare(that.prezzo, prezzo) == 0 && Double.compare(that.sconto, sconto) == 0 && anno == that.anno && gradazione == that.gradazione && formato == that.formato && quantita == that.quantita && id_ordine == that.id_ordine && Objects.equals(nome, that.nome) && Objects.equals(descrizione, that.descrizione) && Objects.equals(tipo, that.tipo) && Objects.equals(immagine, that.immagine) && Objects.equals(nome_categoria, that.nome_categoria) && Objects.equals(regione, that.regione);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nome, descrizione, tipo, prezzo, sconto, immagine, nome_categoria, anno, regione, gradazione, formato, quantita, id_ordine);
	}

	@Override
	public String toString() {
		return "ProductOrdered{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", descrizione='" + descrizione + '\'' +
				", tipo='" + tipo + '\'' +
				", prezzo=" + prezzo +
				", sconto=" + sconto +
				", immagine='" + immagine + '\'' +
				", nome_categoria='" + nome_categoria + '\'' +
				", anno=" + anno +
				", regione='" + regione + '\'' +
				", gradazione=" + gradazione +
				", formato=" + formato +
				", quantita_magazzino=" + quantita +
				", id_ordine=" + id_ordine +
				'}';
	}
}
