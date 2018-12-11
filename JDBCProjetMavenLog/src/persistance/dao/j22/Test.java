package persistance.dao.j22;

import persistance.dao.j22.PersonDAO;

public class Test {

	public static void main(String[] args) throws Exception {
		// System.setProperty("EXTERNAL_CFG_FILE", "U:\\Partage\\Olivier\\cfg.ini");
		System.setProperty("OTHER_CFG_FILE", "files/cfg.ini");
		SpeciesDAO sp = new SpeciesDAO();
		AnimalDAO anidao = new AnimalDAO();
		PersonDAO persdao = new PersonDAO();
		persdao.findList();
		sp.findList();
		anidao.findList();
	}
}