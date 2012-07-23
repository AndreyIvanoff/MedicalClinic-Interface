package ru.hospital.model;

import java.util.List;
import ru.hospital.model.base.EntityExtension;

public class Service extends EntityExtension{

	//Подразумевается набор строк, обозначающих иерархию услуги/процедуры
	private List<String> marks;

	private String name;
	private List<String> searchName;


	public List<String> getMarks() {
		return marks;
	}

	public void setMarks(List<String> marks) {
		this.marks = marks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getSearchName() {
		return searchName;
	}

	public void setSearchName(List<String> searchName) {
		this.searchName = searchName;
	}
}
