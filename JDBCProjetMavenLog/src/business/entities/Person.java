package business.entities;

import java.util.ArrayList;
import java.util.List;

public class Person {
	private long id;
	private String nom, prenom;
	private int age;
	private List<Animal> a = new ArrayList<>();

	public Person(long lId, String aName, String aPrenom, int aAge, Animal lA) {
		setId(lId);
		setName(aName);
		setPrenom(aPrenom);
		setAge(aAge);
		setA(lA);
	}

	public Person() {
	}

	public String getName() {
		return nom;
	}

	public void setName(String name) {
		this.nom = name;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Animal> getA() {
		return a;
	}

	public void setA(Animal la) {
		a.add(la);
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", " + "\n     a=" + a
				+ "]";
	}
}