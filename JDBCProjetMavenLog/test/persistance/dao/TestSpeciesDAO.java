package persistance.dao;

import business.entities.Species;
import persistance.dao.j22.SpeciesDAO;

public class TestSpeciesDAO extends TU_Pere {
	SpeciesDAO sdao = new SpeciesDAO();
	Species s = new Species(10, "Ant", "Antus");
	String selectList = "select count(id_species) from species";
	String selectDelete = "select count(id_species) from species";
	String updateName = "Poiscaille";

	public void testFindById() throws Exception {
		s = sdao.findById(2);
		assertEquals(sdao.findById(2).getCommon_name(), s.getCommon_name());

		assertNull(sdao.findById(0));
	}

	public void testCreate() throws Exception {
		sdao.create(s);
		assert (sdao.findById(10)) != null;
		assertEquals(s.getCommon_name(), sdao.findById(10).getCommon_name());

		assertNull(sdao.create(null));
	}

	public void testFindList() throws Exception {
		int realNb = getInserter().select(selectList).getDataAsInt();
		assertEquals(sdao.findList().size(), realNb);
	}

	public void testUpdateById() throws Exception {
		s = sdao.findById(2);
		s.setCommon_name(updateName);
		sdao.updateById(s);
		assertEquals(s.getCommon_name(), sdao.findById(2).getCommon_name());

		assertNull(sdao.updateById(null));
	}

	public void testDeleteById() throws Exception {
		sdao.deleteById(2);
		int realNb = getInserter().select(selectDelete).getDataAsInt();
		assertEquals(sdao.findList().size(), realNb);

		sdao.deleteById(15);
		realNb = getInserter().select(selectDelete).getDataAsInt();
		assertEquals(sdao.findList().size(), realNb);

		sdao.deleteById(0);
		realNb = getInserter().select(selectDelete).getDataAsInt();
		assertEquals(sdao.findList().size(), realNb);
	}
}