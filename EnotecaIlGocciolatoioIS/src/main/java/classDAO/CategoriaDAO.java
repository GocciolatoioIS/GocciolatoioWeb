package classDAO;

import bean.Categoria;
import connectionPool.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriaDAO {

    //Usata nella initcategorie iniziale
    public ArrayList<Categoria> retriveAll() {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id, nome, descrizione FROM categoria");
            ArrayList<Categoria> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Categoria c = new Categoria();
                c.setId(rs.getInt(1));
                c.setNome(rs.getString(2));
                c.setDescrizione(rs.getString(3));
                list.add(c);
            }

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

