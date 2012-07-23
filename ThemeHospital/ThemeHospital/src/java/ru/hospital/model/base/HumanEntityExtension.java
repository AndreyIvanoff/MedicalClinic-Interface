package ru.hospital.model.base;

import com.google.appengine.api.datastore.Entity;
import java.util.Date;
import java.util.List;
import ru.hospital.utils.StringUtils;

/**
 * Класс для всех человеческих сущностей (Администраторы, Пациенты, Доктора).
 */
public abstract class HumanEntityExtension extends EntityExtension{

	public static final String PARAM_FIRST_NAME = "firstName";
	public static final String PARAM_LAST_NAME = "lastName";
	public static final String PARAM_FATHER_NAME = "fatherName";
	public static final String PARAM_LAST_NAMES = "lastNames";
	public static final String PARAM_BIRTH_DAY = "birthDaty";
	public static final String PARAM_TAG = "tag";

	private String firstName;
	private String lastName;
	private String fatherName;

	private List<String> lastNames;
	private List<String> tags;

	private Date birthDay;

	public HumanEntityExtension(Entity entity){
		super(entity);
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
		setLastNames(StringUtils.toSearchArrays(lastName));
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDaty) {
		this.birthDay = birthDaty;
	}

	public List<String> getLastNames() {
		return lastNames;
	}

	public void setLastNames(List<String> lastNames) {
		this.lastNames = lastNames;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public boolean isInformationFull(){
		if ((firstName != null && !firstName.isEmpty())
			&& (lastName != null && !lastName.isEmpty())
			&& (fatherName != null && !fatherName.isEmpty())) {
			return true;
		}
		return false;
	}
}
