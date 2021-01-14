package classDAO;

import bean.ProductOrdered;
import connectionPool.ConPool;

import java.sql.*;
import java.util.ArrayList;


public class ProductOrderedDao {


	public ArrayList<ProductOrdered> retriveByOrderId(int id){
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps =
					con.prepareStatement("SELECT nome, tipo, descrizione, prezzo, sconto, immagine, anno, regione, gradazione, formato, quantita, nome_categoria FROM prodotto_ordinato WHERE order_id=? ");
			ps.setInt(1,id);
			ArrayList<ProductOrdered> list = new ArrayList<>();
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ProductOrdered po= new ProductOrdered();
				po.setNome(rs.getString(1));
				po.setTipo(rs.getString(2));
				po.setDescrizione(rs.getString(3));
				po.setPrezzo(rs.getInt(4));
				po.setSconto(rs.getInt(5));
				po.setImmagine(rs.getString(6));
				po.setAnno(rs.getInt(7));
				po.setRegione(rs.getString(8));
				po.setGradazione(rs.getInt(9));
				po.setFormato(rs.getInt(10));
				po.setQuantita(rs.getInt(11));
				po.setNome_categoria(rs.getString(12));
				list.add(po);
			}
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void doSave(ProductOrdered p,int idOrdine) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO prodotto_ordinato (nome, tipo, descrizione, prezzo, sconto, immagine, anno, regione, gradazione, formato, nome_categoria, quantita,order_id) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)",
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
			ps.setString( 11,p.getNome_categoria());
			ps.setInt( 12,p.getQuantita());
			ps.setInt(13,idOrdine);

			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("INSERT error.");
			}
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int id=rs.getInt(1);
			p.setId(id);



		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}



}
