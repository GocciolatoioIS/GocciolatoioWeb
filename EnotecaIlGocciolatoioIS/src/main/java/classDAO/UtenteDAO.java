package classDAO;

import model.bean.Utente;
import model.connectionPool.ConPool;

import java.sql.*;
import java.util.ArrayList;

public class UtenteDAO {

    public ArrayList<Utente> retriveAll() {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id, username, pass, email, nome, cognome , data_nascita,ruolo FROM utente");
            ArrayList<Utente> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Utente p = new Utente();
                p.setId(rs.getInt(1));
                p.setUsername(rs.getString(2));
                p.setPass(rs.getString(3));
                p.setEmail(rs.getString(4));
                p.setNome(rs.getString(5));
                p.setCognome(rs.getString(6));
                p.setData_nascita(rs.getDate(7));
                p.setRuolo(rs.getString(8));
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Utente doRetrieveByUsernamePassword(String username, String password) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT id, username, pass, email, nome, cognome , data_nascita,ruolo FROM utente WHERE username=? AND pass=SHA1(?)");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Utente u = new Utente();
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setPass(rs.getString(3));
                u.setEmail(rs.getString(4));
                u.setRuolo(rs.getString(8));
                u.setNome(rs.getString(5));
                u.setCognome(rs.getString(6));
                u.setData_nascita(rs.getDate(7));

                return u;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Utente doRetrieveByUsernameEmail(String username, String email) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT id, username, email FROM utente WHERE username=? AND email=?");
            ps.setString(1, username);
            ps.setString(2, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Utente p = new Utente();
                p.setId(rs.getInt(1));
                p.setUsername(rs.getString(2));
                p.setEmail(rs.getString(3));


                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int doSave(Utente user) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO utente (email, username, pass, ruolo, nome, cognome ,data_nascita) VALUES(?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPass());
            ps.setString(4, user.getRuolo());
            ps.setString(5, user.getNome());
            ps.setString(6,user.getCognome());

            //gestione data con conversione da util a sql di date formato "aaaa-MM-dd"
            java.util.Date utilDate = user.getData_nascita();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            //System.out.println("datautils"+ utilDate + ",datasql" +sqlDate);
            ps.setDate(7,sqlDate);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            user.setId(id);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int doUpdate(Utente u) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("UPDATE utente SET username=?, email=?, nome=?, cognome=?,  ruolo=? WHERE id=?");

            ps.setString(1,u.getUsername());
            ps.setString(2,u.getEmail());
            ps.setString(3, u.getNome());
            ps.setString(4, u.getCognome());
            ps.setString(5,u.getRuolo());
            ps.setInt(6, u.getId());




            ps.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int deleteUser(int id) {
        try (Connection con = ConPool.getConnection()) {
            System.out.println("sONOeNTRATO");
            PreparedStatement ps =
                    con.prepareStatement("DELETE FROM utente WHERE id=?");
            ps.setInt(1,id);
            ps.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Utente retriveById(int n){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id, username, pass, email, nome, cognome , data_nascita, ruolo FROM utente WHERE id=?");
            ps.setInt(1,n);
            ResultSet rs = ps.executeQuery();
            Utente u = new Utente();
            if (rs.next()) {
                u.setId(rs.getInt(1));
                u.setEmail(rs.getString(4));
                u.setUsername(rs.getString(2));
                u.setPass(rs.getString(3));
                u.setRuolo(rs.getString(8));
                u.setNome(rs.getString(5));
                u.setCognome(rs.getString(6));
                u.setData_nascita(rs.getDate(7));

            }
            return u;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Utente deRetriveUsername(String username){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT id, username, email FROM utente WHERE username=?");
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Utente p = new Utente();
                p.setId(rs.getInt(1));
                p.setUsername(rs.getString(2));
                p.setEmail(rs.getString(3));


                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Utente doRetrieveByEmail(String email){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT id, username, email FROM utente WHERE email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Utente p = new Utente();
                p.setId(rs.getInt(1));
                p.setUsername(rs.getString(2));
                p.setEmail(rs.getString(3));


                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
