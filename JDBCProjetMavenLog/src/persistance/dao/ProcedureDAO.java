package persistance.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
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

public class ProcedureDAO {
	private String proc = "call myA(?, ?)";
	private String procAnimal = "call myAnimal";
	private String procSpecies = "call myProc";
	private static Log log = LogFactory.getLog(PersonDAO.class);

	public long count(String searchingName) throws Exception {
		if (searchingName == null) {
			return 0;
		}

		Connection con = null;
		long nb = 0;
		try {
			con = JDBCManager.getInstance().openConection();
			CallableStatement prep = con.prepareCall(proc);
			prep.setString(1, searchingName);
			prep.registerOutParameter(2, java.sql.Types.INTEGER);
			prep.execute();
			nb = prep.getInt(2);
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
		log.info("Procedure count launched");
		return nb;
	}

	public List<Animal> listA() throws Exception {
		Connection con = null;
		List<Animal> list = new ArrayList<>();
		Species esp;
		try {
			con = JDBCManager.getInstance().openConection();
			CallableStatement prep = con.prepareCall(procAnimal);
			ResultSet s = prep.executeQuery();
			while (s.next()) {
				SpeciesDAO sdao = new SpeciesDAO();
				esp = sdao.findById(s.getLong(5));
				Animal a = new Animal(s.getLong(1), s.getString(2), s.getString(3), s.getString(4), esp);
				System.out.println(a.toString());
				list.add(a);
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
		System.out.println();
		log.info("Procedure listA launched");
		return list;
	}

	public List<Species> listS() throws Exception {
		Connection con = null;
		List<Species> list = new ArrayList<>();
		try {
			con = JDBCManager.getInstance().openConection();
			CallableStatement prep = con.prepareCall(procSpecies);
			ResultSet s = prep.executeQuery();
			while (s.next()) {
				Species sp = new Species(s.getLong(1), s.getString(2), s.getString(3));
				System.out.println(sp.toString());
				list.add(sp);
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
		System.out.println();
		log.info("Procedure listS launched");
		return list;
	}
}