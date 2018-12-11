package persistance.dao;

import business.entities.Animal;
import business.entities.Species;
import persistance.dao.j22.AnimalDAO;
import persistance.dao.j22.SpeciesDAO;

public class TestAnimalDAO extends TU_Pere {
	AnimalDAO anidao = new AnimalDAO();
	SpeciesDAO sdao = new SpeciesDAO();
	long id10 = 10;
	String name10 = "Prout", sex10 = "F", coat10 = "Bliblablou";
	String select = "select count(id_animal) from animal";
	String selectDelete = "select count(id_animal) from animal";
	String update = "Michael";
	Species s;
	Animal a = new Animal();

	public void testCreate() throws Exception {
		s = sdao.findById(2);
		a.setId(id10);
		a.setName(name10);
		a.setSex(sex10);
		a.setCoat_color(coat10);
		a.setS(s);

		anidao.create(a);
		assert (anidao.findById(10)) != null;
		assertEquals(a.getName(), anidao.findById(10).getName());

		assertNull(anidao.create(null));
	}

	public void testFindById() throws Exception {
		a = anidao.findById(1);
		assertEquals(anidao.findById(1).getName(), a.getName());

		assertNull(anidao.findById(0));
	}

	public void testFindList() throws Exception {
		int realNb = getInserter().select(select).getDataAsInt();
		assertEquals(anidao.findList().size(), realNb);
	}

	public void testUpdateById() throws Exception {
		a = anidao.findById(1);
		a.setName(update);
		anidao.updateById(a);
		assertEquals(a.getName(), anidao.findById(1).getName());

		assertNull(anidao.updateById(null));
	}

	public void testDeleteById() throws Exception {
		anidao.deleteById(1);
		int realNb = getInserter().select(selectDelete).getDataAsInt();
		assertEquals(anidao.findList().size(), realNb);

		anidao.deleteById(15);
		realNb = getInserter().select(selectDelete).getDataAsInt();
		assertEquals(anidao.findList().size(), realNb);

		anidao.deleteById(0);
		realNb = getInserter().select(selectDelete).getDataAsInt();
		assertEquals(anidao.findList().size(), realNb);
	}
}