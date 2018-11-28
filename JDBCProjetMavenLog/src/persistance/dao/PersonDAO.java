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
import business.entities.Person;
import persistance.exceptions.DAOException;
import persistance.manager.JDBCManager;

public class PersonDAO implements IDAO<Person> {
	private String createSQL = "INSERT INTO person values (?, ?, ?, ?)";
	private String createSQLAniPers = "INSERT INTO animal__person values (?, ?)";
	private String selectSolo = "SELECT * FROM person WHERE id_person = ?";
	private String selectAll = "SELECT * FROM person";
	private String selectAllAP = "SELECT * FROM animal__person";
	private String selectAPID = "SELECT * FROM animal__person WHERE id_person = ?";
	private String update = "UPDATE person SET nom = ?, prenom = ?, age = ? WHERE id_person = ?";
	private String delete = "DELETE FROM person WHERE id_person = ?";
	private String deleteAnimalPerson = "DELETE FROM animal__person WHERE id_person = ?";
	private String deleteAniPers = "DELETE FROM animal__person WHERE id_person=? AND id_animal=?";
	private Person per;
	AnimalDAO a = new AnimalDAO();
	private static Log log = LogFactory.getLog(PersonDAO.class);

	// Create a person
	@Override
	public Person create(Person pT) throws Exception {
		if (pT == null) {
			return null;
		}
		Connection con;
		try {
			con = JDBCManager.getInstance().openConection();
			PreparedStatement prep = con.prepareStatement(createSQL, PreparedStatement.RETURN_GENERATED_KEYS);

			prep.setLong(1, pT.getId());
			prep.setString(2, pT.getName());
			prep.setString(3, pT.getPrenom());
			prep.setLong(4, pT.getAge());
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
		log.info("Person created");
		return pT;
	}

	// Create an association Animal_Person
	public Person createAniPers(Person pT) throws Exception {
		Connection con;

		if (pT == null) {
			return null;
		}

		try {
			con = JDBCManager.getInstance().openConection();
			PreparedStatement prepAni = con.prepareStatement(createSQLAniPers, PreparedStatement.RETURN_GENERATED_KEYS);
			for (Animal a : pT.getA()) {
				prepAni.setLong(1, pT.getId());
				prepAni.setLong(2, a.getId());
				prepAni.execute();
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
		log.info("Person_Animal created");
		return pT;
	}

	//Create a person thanks to its id
	@Override
	public Person findById(long pId) throws Exception {

		if (pId == 0) {
			return null;
		}

		Connection con;
		try {
			con = JDBCManager.getInstance().openConection();
			PreparedStatement prep = con.prepareStatement(selectSolo);
			PreparedStatement prepAP = con.prepareStatement(selectAPID);
			prep.setLong(1, pId);
			ResultSet s = prep.executeQuery();
			s.next();
			per = new Person();
			per.setName(s.getString(2));
			per.setPrenom(s.getString(3));
			per.setId(s.getLong(1));
			per.setAge(s.getInt(4));
			prepAP.setLong(1, pId);
			ResultSet resAP = prepAP.executeQuery();
			while(resAP.next()) {
				per.setA(a.findById(resAP.getLong(2)));
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
		log.info("Person found");
		return per;
	}
	
	public void removeAnimal(long idPers, long idAni)throws Exception {
		Connection con;
		try {
			con = JDBCManager.getInstance().openConection();
			PreparedStatement prep = con.prepareStatement(deleteAniPers);
			prep.setLong(1, idPers);
			prep.setLong(2, idAni);
			prep.execute();
		}catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e);
		} finally {
			try {
				JDBCManager.getInstance().closeConnection();
			} catch (Exception e) {
				throw new DAOException(e);
			}
		}
	}
	
	//Show all person's animals
	public List<Animal> findListAnimalP(long id) throws Exception {
		List<Animal> lA = new ArrayList<>();
		Connection con;
		try {
			con = JDBCManager.getInstance().openConection();
			PreparedStatement prep = con.prepareStatement(selectAPID);
			prep.setLong(1, id);
			ResultSet res = prep.executeQuery();
			while(res.next()) {
				Animal ani;
				ani = a.findById(res.getLong(2));
				lA.add(ani);
			}
		}catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e);
		} finally {
			try {
				JDBCManager.getInstance().closeConnection();
			} catch (Exception e) {
				throw new DAOException(e);
			}
		}
		return lA;
	}

	// List all association person_animal in DataBase
	public List<Person> findListAP() throws Exception {
		List<Person> lp = new ArrayList<>();
		Connection con;
		try {
			con = JDBCManager.getInstance().openConection();
			PreparedStatement prep = con.prepareStatement(selectAllAP);
			ResultSet res = prep.executeQuery();
			while (res.next()) {
				per = new Person();
				per = findById(res.getLong(1));
				lp.add(per);
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
		log.info("PersonA found");
		return lp;
	}

	// Show all persons in database
	@Override
	public List<Person> findList() throws Exception {

		List<Person> lp = new ArrayList<>();
		Connection con;
		try {
			con = JDBCManager.getInstance().openConection();
			PreparedStatement prep = con.prepareStatement(selectAll);
			PreparedStatement prepAP = con.prepareStatement(selectAPID);
			ResultSet res = prep.executeQuery();
			while (res.next()) {
				per = new Person();
				per.setId(res.getLong(1));
				per.setName(res.getString(2));
				per.setPrenom(res.getString(3));
				per.setId(res.getLong(1));
				per.setAge(res.getInt(4));
				prepAP.setLong(1, per.getId());
				ResultSet resAP = prepAP.executeQuery();
				while(resAP.next()) {
					Animal ani;
					ani = a.findById(resAP.getLong(2));
					per.setA(ani);
				}
				lp.add(per);
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
		log.info("PersonsA found");
		return lp;
	}

	// Update a person (including associations person_animal)
	@Override
	public Person updateById(Person pT) throws Exception {
		if (pT == null) {
			return null;
		}
		Connection con;
		try {
			con = JDBCManager.getInstance().openConection();
			PreparedStatement prepDelete = con.prepareStatement(deleteAnimalPerson);
			prepDelete.setLong(1, pT.getId());
			prepDelete.execute();

			PreparedStatement prep = con.prepareStatement(update);
			prep.setString(1, pT.getName());
			prep.setString(2, pT.getPrenom());
			prep.setLong(3, pT.getAge());
			prep.setLong(4, pT.getId());
			prep.execute();

			createAniPers(pT);
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
		log.info("Person updated");

		return pT;
	}

	// Delete a person (including associations)
	@Override
	public void deleteById(long pId) throws Exception {
		Connection con;
		try {
			con = JDBCManager.getInstance().openConection();
			PreparedStatement deleteAP = con.prepareStatement(deleteAnimalPerson);
			deleteAP.setLong(1, pId);
			deleteAP.execute();

			PreparedStatement deleteP = con.prepareStatement(delete);
			deleteP.setLong(1, pId);
			deleteP.execute();
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
		log.info("Person deleted");
	}
}