package classDAO;

import bean.Indirizzo;
import connectionPool.ConPool;

import java.sql.*;
import java.util.ArrayList;

public class IndirizzoDAO {

    public ArrayList<Indirizzo> retriveAllbyID(int iD) {
        try(Connection con = ConPool.getConnection())
        {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id,citta,cap,via,ncivico,nazione,id_utente FROM indirizzo WHERE id_utente=?");
            ps.setInt(1,iD);
            ArrayList<Indirizzo> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Indirizzo i = new Indirizzo();
                i.setId(rs.getInt(1));
                i.setCitta(rs.getString(2));
                i.setCap(rs.getInt(3));
                i.setVia(rs.getString(4));
                i.setNumCivico(rs.getInt(5));
                i.setNazione(rs.getString(6));
                list.add(i);
            }

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int doSave(Indirizzo i,int iD){

        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO indirizzo (citta,cap,via,ncivico,nazione,id_utente) VALUES(?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,i.getCitta());
            ps.setInt(2,i.getCap());
            ps.setString(3,i.getVia());
            ps.setInt(4,i.getNumCivico());
            ps.setString(5,i.getNazione());
            ps.setInt(6,iD);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            i.setId(id);
            return 1;
        } catch (SQLException e) {
            return 0;
        }
    }

    public int deleteIndirizzo(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("DELETE FROM indirizzo WHERE id=?");
            ps.setInt(1,id);
            ps.executeUpdate();
            return 1;
        } catch (SQLException e) {
            return 0;
        }
    }

    public int doUpdate(Indirizzo i){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("UPDATE indirizzo SET citta=?,cap=?,via=?,ncivico=?,nazione=?  WHERE id=?");

            ps.setString(1,i.getCitta());
            ps.setInt(2,i.getCap());
            ps.setString(3,i.getVia());
            ps.setInt(4,i.getNumCivico());
            ps.setString(5,i.getNazione());
            ps.setInt(6,i.getId());

            ps.executeUpdate();
            return 1;

        } catch (SQLException e) {
            return 0;
        }


    }

    public Indirizzo retriveByID(int id){
        try(Connection con = ConPool.getConnection())
        {
            Indirizzo i = new Indirizzo();
            PreparedStatement ps =
                    con.prepareStatement("SELECT id,citta,cap,via,ncivico,nazione,id_utente FROM indirizzo WHERE id=?");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                i.setId(rs.getInt(1));
                i.setCitta(rs.getString(2));
                i.setCap(rs.getInt(3));
                i.setVia(rs.getString(4));
                i.setNumCivico(rs.getInt(5));
                i.setNazione(rs.getString(6));
            }

            return i;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int removeInd(int id){

        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("DELETE FROM indirizzo WHERE id=?");
            ps.setInt(1,id);
            ps.executeUpdate();

            return 1;
        } catch (SQLException e) {
            return 0;
        }

    }
    public Indirizzo retriveIndirizzoOrdineByID(int id){
        try(Connection con = ConPool.getConnection())
        {
            Indirizzo i = new Indirizzo();
            PreparedStatement ps =
                    con.prepareStatement("SELECT id,citta,cap,via,ncivico,nazione FROM indirizzo_ordine WHERE id=?");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                i.setId(rs.getInt(1));
                i.setCitta(rs.getString(2));
                i.setCap(rs.getInt(3));
                i.setVia(rs.getString(4));
                i.setNumCivico(rs.getInt(5));
                i.setNazione(rs.getString(6));
            }

            return i;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
