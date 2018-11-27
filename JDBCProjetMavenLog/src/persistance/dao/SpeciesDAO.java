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

import business.entities.Species;
import persistance.exceptions.DAOException;
import persistance.manager.JDBCManager;

public class SpeciesDAO implements IDAO<Species> {
	private String createSQL = "INSERT INTO species values (?, ?, ?)";
	private String selectSolo = "SELECT * FROM species WHERE id_species = ?";
	private String update = "UPDATE species SET common_name = ?, latin_name = ? WHERE id_species = ?";
	private String delete = "DELETE FROM species WHERE id_species = ?";
	private static Log log = LogFactory.getLog(PersonDAO.class);

	@Override
	public Species create(Species pT) throws Exception {
		if (pT == null) {
			return null;
		}
		Connection con;
		try {
			con = JDBCManager.getInstance().openConection();
			PreparedStatement prep = con.prepareStatement(createSQL, PreparedStatement.RETURN_GENERATED_KEYS);
			prep.setLong(1, pT.getId());
			prep.setString(2, pT.getCommon_name());
			prep.setString(3, pT.getLatin_name());
			prep.execute();
			ResultSet rs1 = prep.getGeneratedKeys();

			while (rs1.next()) {
				long id = rs1.getLong("GENERATED_KEY");
				System.out.println("new key for " + pT.getCommon_name() + " is " + id);
			}
		} catch (SQLException | ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e);
		} finally {
			try {
				JDBCManager.getInstance().closeConnection();
			} catch (Exception e) {
				throw new DAOException(e);
			}
		}
		log.info("Specie created");

		return pT;
	}

	@Override
	public Species findById(long pId) throws Exception {
		if (pId == 0) {
			return null;
		}
		Connection con;
		Species sp = null;
		try {
			con = JDBCManager.getInstance().openConection();
			PreparedStatement prep = con.prepareStatement(selectSolo);
			prep.setLong(1, pId);
			ResultSet s = prep.executeQuery();
			s.next();
			sp = new Species(s.getLong(1), s.getString(2), s.getString(3));
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
		log.info("Specie found");
		return sp;
	}

	@Override
	public List<Species> findList() throws Exception {
		Connection con;
		List<Species> listSpecies = new ArrayList<Species>();
		try {
			con = JDBCManager.getInstance().openConection();
			PreparedStatement prep = con.prepareStatement("SELECT * FROM species");
			ResultSet s = prep.executeQuery();
			System.out.println("\nList of species : \n");
			while (s.next()) {
				Species sp = new Species(s.getLong(1), s.getString(2), s.getString(3));
				listSpecies.add(sp);
				System.out.println(sp.toString());
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
		log.info("Species found");
		return listSpecies;
	}

	@Override
	public Species updateById(Species pT) throws Exception {
		if (pT == null) {
			return null;
		}
		Connection con;
		try {
			con = JDBCManager.getInstance().openConection();
			PreparedStatement prep = con.prepareStatement(update);
			prep.setString(1, pT.getCommon_name());
			prep.setString(2, pT.getLatin_name());
			prep.setLong(3, pT.getId());
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
		log.info("Specie updated");
		return pT;
	}

	@Override
	public void deleteById(long pId) throws Exception {
		Connection con;
		try {
			con = JDBCManager.getInstance().openConection();
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
		log.info("Specie deleted");
	}
}