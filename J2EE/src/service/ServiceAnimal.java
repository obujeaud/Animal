package service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import business.entities.Animal;
import business.entities.Person;
import persistance.dao.j22.PersonDAO;

public class ServiceAnimal {
	List<Animal> listA = new ArrayList<>();
	PersonDAO pdao = new PersonDAO();

	public void ajoutAnimal(Animal a) throws Exception {
		listA.add(a);
	}

	public void maj(Person p) throws Exception {
		for (Animal a : listA) {
			p.setA(a);
		}
		pdao.updateById(p);
	}

	public void removeAnimal(HttpSession session, Animal a) throws Exception {
		Person p = (Person)session.getAttribute("idPers");
		pdao.removeAnimal(p.getId(), a.getId());
		session.removeAttribute("idPers");
	}
}