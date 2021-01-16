package classDAO;

import bean.Prodotto;
import connectionPool.ConPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProdottoDAO {

    public Prodotto retriveOne(int n){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id, nome, tipo, descrizione,prezzo, sconto, immagine, anno, regione, gradazione, formato, quantita_magazzino, nome_categoria FROM prodotto_catalogo WHERE id=?");
            ps.setInt(1,n);
            ResultSet rs = ps.executeQuery();
            Prodotto p = new Prodotto();
            if (rs.next()) {
                p.setId(rs.getInt(1));
                p.setNome(rs.getString(2));
                p.setTipo(rs.getString(3));
                p.setDescrizione(rs.getString(4));
                p.setPrezzo(rs.getDouble(5));
                p.setSconto(rs.getDouble(6));
                p.setImmagine(rs.getString(7));
                p.setAnno(rs.getInt(8));
                p.setRegione(rs.getString(9));
                p.setGradazione(rs.getInt(10));
                p.setFormato(rs.getInt(11));
                p.setQuantita_magazzino(rs.getInt(12));

                if(p.getSconto()>0){
                    p.setPrezzo(p.getPrezzo()-(p.getPrezzo()/100*p.getSconto()));
                }
            } else {
                return null;
            }
            return p;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int retriveQuant(int n){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT quantita_magazzino FROM prodotto_catalogo WHERE id=?");
            ps.setInt(1,n);
            ResultSet rs = ps.executeQuery();
            Prodotto p = new Prodotto();
            while (rs.next()) {
                p.setQuantita_magazzino(rs.getInt(1));
            }
            return p.getQuantita_magazzino();
        } catch (SQLException e) {
            return 0;
        }
    }

    public List<Prodotto> retriveBySearch(String s){
        try (Connection con = ConPool.getConnection()) {

            String search = "%";
            String replaced="";
            for(int i=0;i<s.length();i++) {
                char var=s.charAt(i);
                //System.out.println(var);
                if(var==' ') {
                    replaced+="%";
                    //System.out.println("space");
                }else {
                    replaced+=var;
                }
            }
            search += replaced + "%";
            //search += s + "%";
            PreparedStatement ps =
                    con.prepareStatement("SELECT id, nome, tipo, descrizione,prezzo, sconto, immagine, anno, regione, gradazione, formato, quantita_magazzino, nome_categoria FROM prodotto_catalogo WHERE nome LIKE ?");
            ps.setString(1,search);
            ResultSet rs = ps.executeQuery();

            ArrayList<Prodotto> list=new ArrayList<>();

            while (rs.next()) {
                Prodotto p = new Prodotto();
                p.setId(rs.getInt(1));
                p.setNome(rs.getString(2));
                p.setTipo(rs.getString(3));
                p.setDescrizione(rs.getString(4));
                p.setPrezzo(rs.getDouble(5));
                p.setSconto(rs.getDouble(6));
                p.setImmagine(rs.getString(7));
                p.setAnno(rs.getInt(8));
                p.setRegione(rs.getString(9));
                p.setGradazione(rs.getInt(10));
                p.setFormato(rs.getInt(11));
                p.setQuantita_magazzino(rs.getInt(12));
                if(p.getSconto()>0){
                    p.setPrezzo(p.getPrezzo()-(p.getPrezzo()/100*p.getSconto()));
                }
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Prodotto> retriveAll() {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT nome, descrizione,prezzo, immagine, anno, regione, gradazione, formato, quantita_magazzino, nome_categoria, id FROM prodotto_catalogo");
            ArrayList<Prodotto> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Prodotto p = new Prodotto();
                p.setNome(rs.getString(1).toUpperCase());
                p.setDescrizione(rs.getString(2));
                p.setPrezzo(rs.getDouble(3));
                p.setImmagine(rs.getString(4));
                p.setAnno(rs.getInt(5));
                p.setRegione(rs.getString(6));
                p.setGradazione(rs.getInt(7));
                p.setFormato(rs.getInt(8));
                p.setQuantita_magazzino(rs.getInt(9));
                p.setNome_categoria(rs.getString(10));
                p.setId(rs.getInt(11));
                list.add(p);
            }

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Prodotto> retriveCategory(String cat) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT nome,immagine,prezzo, sconto, id FROM prodotto_catalogo WHERE nome_categoria=?");

            ps.setString(1,cat);
            ArrayList<Prodotto> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Prodotto p=new Prodotto();
                p.setNome(rs.getString(1));
                p.setImmagine(rs.getString(2));
                p.setPrezzo(rs.getDouble(3));
                p.setSconto(rs.getDouble(4));
                p.setId(rs.getInt(5));

                if(p.getSconto()>0)
                    p.setPrezzo(p.getPrezzo()-(p.getPrezzo()/100*p.getSconto()));
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
            return null;
        }
    }

    public int doSave(Prodotto p) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO prodotto_catalogo (nome, tipo, descrizione, prezzo, sconto, immagine, anno, regione, gradazione, formato, quantita_magazzino, nome_categoria) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, p.getNome());
            ps.setString(2, p.getTipo());
            ps.setString(3, p.getDescrizione());
            ps.setDouble(4, p.getPrezzo());
            ps.setDouble( 5,p.getSconto());
            ps.setString( 6,"./img/"+p.getImmagine());
            ps.setInt( 7,p.getAnno());
            ps.setString( 8,p.getRegione());
            ps.setInt( 9, (int) p.getGradazione());
            ps.setInt( 10,p.getFormato());
            ps.setInt( 11,p.getQuantita_magazzino());
            ps.setString( 12,p.getNome_categoria());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id=rs.getInt(1);
            p.setId(id);
            return 1;
        } catch (SQLException e) {
            return 0;
        }
    }

    public int deleteProduct(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("DELETE FROM prodotto_catalogo WHERE id=?");
            ps.setInt(1,id);
            ps.executeUpdate();
            return 1;
        } catch (SQLException e) {
            return 0;
        }
    }

    public int doUpdateQuantity(int idP, int quantitaAcq) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("UPDATE prodotto_catalogo SET quantita_magazzino=? WHERE id=?");

            ps.setInt(1, retriveQuant(idP)-quantitaAcq);
            ps.setInt(2, idP);

            ps.executeUpdate();
            return 1;
        } catch (SQLException e) {
            return 0;
        }
    }

    public int doUpdate(Prodotto p) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("UPDATE prodotto_catalogo SET nome=?, tipo=?, descrizione=?, prezzo=?, sconto=?, immagine=?, anno=?, regione=?, gradazione=?, formato=?, quantita_magazzino=?, nome_categoria=? WHERE id=?");

            ps.setString(1,p.getNome());
            ps.setString(2,p.getTipo());
            ps.setString(3,p.getDescrizione());
            ps.setDouble(4,p.getPrezzo());
            ps.setDouble(5, p.getSconto());
            ps.setString(6, "./img/"+p.getImmagine());
            ps.setInt(7, p.getAnno());
            ps.setString(8,p.getRegione());
            ps.setInt(9, (int) p.getGradazione());
            ps.setInt(10,p.getFormato());
            ps.setInt(11,p.getQuantita_magazzino());
            ps.setString(12,p.getNome_categoria());

            ps.setInt(13,p.getId());

            ps.executeUpdate();
            return 1;
        } catch (SQLException e) {
            return 0;
        }
    }
}