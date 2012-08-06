package ru.hospital.model.base;

import java.util.Date;
import java.util.List;

/**
 * Класс для всех человеческих сущностей (Администраторы, Пациенты, Доктора).
 */
public abstract class HumanEntityExtension extends EntityExtension{

	public static final String PARAM_FIRST_NAME = "firstName";
	public static final String PARAM_LAST_NAME = "lastName";
	public static final String PARAM_FATHER_NAME = "fatherName";
	public static final String PARAM_BIRTH_DAY = "birthDaty";
	public static final String PARAM_TAG = "tags";

	private String firstName;
	private String lastName;
	private String fatherName;
	private Date birthDay;

	private List<String> tags;


	public HumanEntityExtension(){

	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDaty) {
		this.birthDay = birthDaty;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

}
