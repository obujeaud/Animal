package service;

import java.util.List;

import business.entities.Person;
import business.entities.User;
import persistance.dao.PersonDAO;
import persistance.dao.UserDAO;

public class MyService {
	private PersonDAO pdao = new PersonDAO();
	private UserDAO udao = new UserDAO();
	public List<Person> personList() throws Exception {
		return pdao.findList();
	}
	
	public User verifyUser(User e) throws Exception {
		return udao.check(e);
	}
	
	public User checkUser(User e) throws Exception {
		return udao.check(e);
	}
	
	public boolean isAdmin(User e) {
		return udao.isAdmin(e);
	}
}