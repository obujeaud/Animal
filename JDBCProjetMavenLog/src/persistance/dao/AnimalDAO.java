package persistance.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import business.entities.Animal;
import business.entities.Species;
import persistance.exceptions.DAOException;
import persistance.manager.JDBCManager;

public class AnimalDAO implements IDAO<Animal> {
	private String createSQL = "INSERT INTO animal values (?, ?, ?, ?, ?)";
	private String selectSolo = "SELECT * FROM animal WHERE id_animal = ?";
	private String update = "UPDATE animal SET name = ?, sex = ?, coat_color = ?, id_species = ? WHERE id_animal = ?";
	private String delete = "DELETE FROM animal WHERE id_animal = ?";
	private String deleteAP = "DELETE FROM animal__person WHERE id_animal = ?";
	private Species esp = new Species();
	private static Log log = LogFactory.getLog(AnimalDAO.class);

	@Override
	public Animal create(Animal pT) throws Exception {
		if (pT == null) {
			return null;
		}
		Connection con;
		try {
			con = JDBCManager.getInstance().openConection();
			PreparedStatement prep = con.prepareStatement(createSQL, PreparedStatement.RETURN_GENERATED_KEYS);
			SpeciesDAO spedao = new SpeciesDAO();
			esp = spedao.findById(pT.getS().getId());
			prep.setLong(1, pT.getId());
			prep.setString(2, pT.getName());
			prep.setString(3, "" + pT.getSex());
			prep.setString(4, pT.getCoat_color());
			prep.setLong(5, esp.getId());
			prep.execute();
			ResultSet rs1 = prep.getGeneratedKeys();

			while (rs1.next()) {
				long id = rs1.getLong("GENERATED_KEY");
				System.out.println("new key for " + pT.getName() + " is " + id);
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e);
		} finally {
			try {
				JDBCManager.getInstance().closeConnection();
			} catch (Exception e) {
				throw new DAOException(e);
			}
		}
		log.info("Animal created");
		return pT;
	}

	@Override
	public Animal findById(long pId) throws Exception {
		if (pId == 0) {
			return null;
		}
		Connection con;
		Animal sp = null;
		try {
			con = JDBCManager.getInstance().openConection();
			PreparedStatement prep = con.prepareStatement(selectSolo);
			prep.setLong(1, pId);
			ResultSet s = prep.executeQuery();
			s.next();
			SpeciesDAO spedao = new SpeciesDAO();
			esp = spedao.findById(s.getLong(5));
			sp = new Animal(s.getLong(1), s.getString(2), s.getString(3), s.getString(4), esp);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e);
		} finally {
			try {
				JDBCManager.getInstance().closeConnection();
			} catch (Exception e) {
				throw new DAOException(e);
			}
		}
		log.info("Animal found");
		return sp;
	}

	@Override
	public List<Animal> findList() throws Exception {
		Connection con;
		List<Animal> listAnimal = new ArrayList<Animal>();
		try {
			con = JDBCManager.getInstance().openConection();
			PreparedStatement prep = con.prepareStatement("SELECT * FROM animal");
			ResultSet s = prep.executeQuery();
			System.out.println("\nList of animals and their related species : \n");
			while (s.next()) {
				SpeciesDAO spedao = new SpeciesDAO();
				esp = spedao.findById(s.getLong(5));
				Animal a = new Animal(s.getLong(1), s.getString(2), s.getString(3), s.getString(4), esp);
				System.out.println(a.toString());
				listAnimal.add(a);
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e);
		} finally {
			try {
				JDBCManager.getInstance().closeConnection();
			} catch (Exception e) {
				throw new DAOException(e);
			}
		}
		log.info("Animals found");
		return listAnimal;
	}

	@Override
	public Animal updateById(Animal pT) throws Exception {
		if (pT == null) {
			return null;
		}
		Connection con;
		try {
			con = JDBCManager.getInstance().openConection();
			PreparedStatement prep = con.prepareStatement(update);
			SpeciesDAO spedao = new SpeciesDAO();
			esp = spedao.findById(pT.getS().getId());
			prep.setString(1, pT.getName());
			prep.setString(2, pT.getSex());
			prep.setString(3, pT.getCoat_color());
			prep.setLong(4, esp.getId());
			prep.setLong(5, pT.getId());
			prep.execute();
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e);
		} finally {
			try {
				JDBCManager.getInstance().closeConnection();
			} catch (Exception e) {
				throw new DAOException(e);
			}
		}
		log.info("Animal updated");
		return pT;
	}

	@Override
	public void deleteById(long pId) throws Exception {
		Connection con;
		try {
			con = JDBCManager.getInstance().openConection();
			PreparedStatement prepDelete = con.prepareStatement(deleteAP);
			prepDelete.setLong(1, pId);
			prepDelete.execute();

			PreparedStatement prep = con.prepareStatement(delete);
			prep.setLong(1, pId);
			prep.execute();
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e);
		} finally {
			try {
				JDBCManager.getInstance().closeConnection();
			} catch (Exception e) {
				throw new DAOException(e);
			}
		}
		log.info("Animal deleted");
	}
}