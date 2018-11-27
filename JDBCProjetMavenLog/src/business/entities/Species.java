package business.entities;

public class Species {
	private long id;
	private String common_name, latin_name;

	public Species(long lId, String leCommon, String leLatin) {
		setId(lId);
		setCommon_name(leCommon);
		setLatin_name(leLatin);
	}

	public Species() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCommon_name() {
		return common_name;
	}

	public void setCommon_name(String common_name) {
		this.common_name = common_name;
	}

	public String getLatin_name() {
		return latin_name;
	}

	public void setLatin_name(String latin_name) {
		this.latin_name = latin_name;
	}

	@Override
	public String toString() {
		return "Species [id=" + id + ", common_name=" + common_name + ", latin_name=" + latin_name + "]";
	}
}