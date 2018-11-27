package business.entities;

public class Animal {
	private long id;
	private String name, sex, coat_color;
	private Species s;

	public Animal(long lId, String aName, String aSex, String aCoat_color, Species ls) {
		setId(lId);
		setName(aName);
		setSex(aSex);
		setCoat_color(aCoat_color);
		setS(ls);
	}

	public Animal() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String aSex) {
		this.sex = aSex;
	}

	public String getCoat_color() {
		return coat_color;
	}

	public void setCoat_color(String coat_color) {
		this.coat_color = coat_color;
	}

	public Species getS() {
		return s;
	}

	public void setS(Species s) {
		this.s = s;
	}

	@Override
	public String toString() {
		return "Animal [id=" + id + ", name=" + name + ", sex=" + sex + ", coat_color=" + coat_color + "\n     s=" + s
				+ "]";
	}
}