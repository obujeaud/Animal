package persistance.dao;

import persistance.dao.j22.ProcedureDAO;

public class TestProcedureDAO extends TU_Pere {
	ProcedureDAO prdao = new ProcedureDAO();
	String selectA = "select count(id_animal) from animal";
	String selectS = "select count(id_species) from species";

	public void testCount() throws Exception {
		long nbRes = prdao.count("Poisson");
		assertEquals(3, nbRes);

		assertEquals(0, prdao.count(null));
	}

	public void testListA() throws Exception {
		int realNb = getInserter().select(selectA).getDataAsInt();
		assertEquals(realNb, prdao.listA().size());
	}

	public void testListS() throws Exception {
		int realNb = getInserter().select(selectS).getDataAsInt();
		assertEquals(realNb, prdao.listS().size());
	}
}