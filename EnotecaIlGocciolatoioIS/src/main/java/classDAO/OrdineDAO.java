package classDAO;

import bean.Indirizzo;
import bean.Ordine;
import bean.Utente;
import connectionPool.ConPool;

import java.sql.*;
import java.util.ArrayList;

public class OrdineDAO {
    public void doSave(Ordine o, int idIndirizzo) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO ordine (id_utente,data_ordine,id_indirizzo ) VALUES(?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,o.getId_utente());

            //gestione data con conversione da util a sql di date formato "aaaa-MM-dd"
            java.util.Date utilDate = o.getData_ordine();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            ps.setDate(2,sqlDate);
            ps.setInt(3,idIndirizzo);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            o.setId_ordine(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Ordine> retriveAll() {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id, id_utente,data_ordine,id_indirizzo FROM ordine ORDER BY data_ordine");
            ArrayList<Ordine> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            ResultSet rs2;
            ResultSet rs3;
            while (rs.next()) {
                Ordine o = new Ordine();
                o.setId_ordine(rs.getInt(1));
                o.setId_utente(rs.getInt(2));
                o.setData_ordine(rs.getDate(3));
                o.setId_indirizzo(rs.getInt(4));
                //trovo l'indirizzo
                PreparedStatement ps2 =
                        con.prepareStatement("SELECT id,citta,cap,via,ncivico,nazione FROM indirizzo_ordine WHERE id=?");
                ps2.setInt(1,o.getId_indirizzo());
                rs2=ps2.executeQuery();
                Indirizzo indirizzo= new Indirizzo();
                if(rs2.next()){
                    indirizzo.setId(rs2.getInt(1));
                    indirizzo.setCitta(rs2.getString(2));
                    indirizzo.setCap(rs2.getInt(3));
                    indirizzo.setVia(rs2.getString(4));
                    indirizzo.setNumCivico(rs2.getInt(5));
                    indirizzo.setNazione(rs2.getString(6));
                }
                o.setIndirizzo(indirizzo);
                //trovo l'utente
                PreparedStatement ps3 =
                        con.prepareStatement("SELECT id,email,username,ruolo,nome,cognome,data_nascita FROM utente WHERE id=?");
                ps3.setInt(1,o.getId_utente());
                rs3=ps3.executeQuery();
                Utente u= new Utente();
                if(rs3.next()){
                    u.setId(rs3.getInt(1));
                    u.setEmail(rs3.getString(2));
                    u.setUsername(rs3.getString(3));
                    u.setRuolo(rs3.getString(4));
                    u.setNome(rs3.getString(5));
                    u.setCognome(rs3.getString(6));
                    u.setData_nascita(rs3.getDate(7));
                }

                o.setUtente(u);

                list.add(o);
            }

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Ordine> retriveByIdUser(int id){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id, id_utente,data_ordine,id_indirizzo FROM ordine WHERE id_utente=? ORDER BY data_ordine");
            ps.setInt(1,id);
            ArrayList<Ordine> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            ResultSet rs2;
            while (rs.next()) {
                Ordine o = new Ordine();
                o.setId_ordine(rs.getInt(1));
                o.setId_utente(rs.getInt(2));
                o.setData_ordine(rs.getDate(3));
                o.setId_indirizzo(rs.getInt(4));
                //trovo l'indirizzo
                PreparedStatement ps2 =
                        con.prepareStatement("SELECT id,citta,cap,via,ncivico,nazione FROM indirizzo_ordine WHERE id=?");
                ps2.setInt(1,o.getId_indirizzo());
                rs2=ps2.executeQuery();
                Indirizzo indirizzo= new Indirizzo();
                if(rs2.next()){
                    indirizzo.setId(rs2.getInt(1));
                    indirizzo.setCitta(rs2.getString(2));
                    indirizzo.setCap(rs2.getInt(3));
                    indirizzo.setVia(rs2.getString(4));
                    indirizzo.setNumCivico(rs2.getInt(5));
                    indirizzo.setNazione(rs2.getString(6));
                    o.setIndirizzo(indirizzo);
                }

                list.add(o);
            }


            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteOrder(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("DELETE FROM ordine WHERE id=?");
            ps.setInt(1,id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int addAddressToOrder(Indirizzo indirizzo){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO indirizzo_ordine (citta,cap,via,ncivico,nazione) VALUES(?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,indirizzo.getCitta());
            ps.setInt(2,indirizzo.getCap());
            ps.setString(3,indirizzo.getVia());
            ps.setInt(4,indirizzo.getNumCivico());
            ps.setString(5,indirizzo.getNazione());


            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
