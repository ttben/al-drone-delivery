package fr.unice.polytech.si5.al.projet.s3.central;

public class Item {

	private String name;

	public Item() {
		this("DefaultItem");
	}

	public Item(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Item{" +
				"name='" + name + '\'' +
				'}';
	}
}