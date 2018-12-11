package persistance.dao;

import business.entities.Animal;
import business.entities.Person;
import persistance.dao.j22.AnimalDAO;
import persistance.dao.j22.PersonDAO;

public class TestPersonDAO extends TU_Pere {
	String selectAniPer = "select count(*) from animal__person";
	String selectPer = "select count(*) from person";
	String selectIdPer = "select count(id_person) from person";
	String newName = "CouilleMole";
	String name7 = "Clavier", prenom7 = "Christian";
	long id7 = 7, age7 = 60;
	PersonDAO pdao = new PersonDAO();
	AnimalDAO anidao = new AnimalDAO();
	Animal a;
	Person p = new Person();

	public void testCreate() throws Exception {
		a = anidao.findById(2);
		p.setId(id7);
		p.setName(name7);
		p.setPrenom(prenom7);
		p.setAge((int) age7);
		p.setA(a);

		pdao.create(p);
		assert (pdao.findById(7)) != null;
		assertEquals(p.getName(), pdao.findById(7).getName());

		assertNull(pdao.create(null));
	}

	public void testCreateAniPer() throws Exception {
		Animal a1 = anidao.findById(1), a2 = anidao.findById(2), a3 = anidao.findById(3);
		p.setId(id7);
		p.setName(name7);
		p.setPrenom(prenom7);
		p.setAge((int) age7);
		p.setA(a1);
		p.setA(a2);
		p.setA(a3);
		pdao.create(p);
		pdao.createAniPers(p);
		int realNb = getInserter().select(selectAniPer).getDataAsInt();
		assertEquals(realNb, 13);

		assertNull(null);
	}

	public void testFindById() throws Exception {
		p = pdao.findById(3);
		assertEquals(pdao.findById(3).getName(), p.getName());

		assertNull(pdao.findById(0));
	}

	public void testFindListAP() throws Exception {
		int realNb = getInserter().select(selectAniPer).getDataAsInt();
		assertEquals(pdao.findListAP().size(), realNb);
	}

	public void testFindList() throws Exception {
		int realNb = getInserter().select(selectPer).getDataAsInt();
		assertEquals(pdao.findList().size(), realNb);
	}

	public void testUpdateById() throws Exception {
		p = pdao.findById(1);
		p.setName(newName);
		pdao.updateById(p);
		assertEquals(newName, pdao.findById(1).getName());

		assertNull(pdao.updateById(null));
	}

	public void testDeleteById() throws Exception {
		pdao.deleteById(1);
		int realNb = getInserter().select(selectIdPer).getDataAsInt();
		assertEquals(pdao.findList().size(), realNb);

		pdao.deleteById(15);
		realNb = getInserter().select(selectIdPer).getDataAsInt();
		assertEquals(pdao.findList().size(), realNb);

		pdao.deleteById(0);
		realNb = getInserter().select(selectIdPer).getDataAsInt();
		assertEquals(pdao.findList().size(), realNb);

	}
}