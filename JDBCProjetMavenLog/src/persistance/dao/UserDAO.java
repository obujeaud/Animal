package persistance.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import business.entities.User;
import persistance.exceptions.DAOException;
import persistance.manager.JDBCManager;

public class UserDAO {
	private String verifyUser = "SELECT * FROM users WHERE name=? AND password=?";
	
	public User check(User u) throws Exception {
		Connection con;
		User userCo = null;
		try {
			con = JDBCManager.getInstance().openConection();
			PreparedStatement prep = con.prepareStatement(verifyUser, PreparedStatement.RETURN_GENERATED_KEYS);
			prep.setString(1, u.getLogin());
			prep.setString(2, u.getMdp());
			ResultSet res = prep.executeQuery();
			if (res.next()) {
				userCo = new User(res.getString(2), res.getString(3), res.getInt(4));
			}
			return userCo;
		} catch (ClassNotFoundException | SQLException | IOException e) {
			throw new DAOException(e);
		} finally {
			try {
				JDBCManager.getInstance().closeConnection();
			} catch (Exception e) {
				throw new DAOException(e);
			}
		}
	}

	public boolean isAdmin(User e) {
		if (e.getIsAdmin() == 1) {
			return true;
		} else {
			return false;
		}
	}
}
