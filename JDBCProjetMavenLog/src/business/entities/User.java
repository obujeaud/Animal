package business.entities;

public class User {
	private String login;
	private String mdp;
	private int isAdmin;
	
	public User(String leLog, String leMdp, int admin) throws Exception {
		login = leLog;
		mdp = leMdp;
		setIsAdmin(admin);
	}
	
	public User(String leLog, String leMdp) throws Exception {
		login = leLog;
		mdp = leMdp;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
}
