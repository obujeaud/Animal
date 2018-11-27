package service;

import java.util.ArrayList;
import java.util.List;

import business.entities.Animal;
import business.entities.Person;
import persistance.dao.PersonDAO;

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
}