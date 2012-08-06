package ru.hospital.model;

import java.util.List;
import ru.hospital.model.base.HumanEntityExtension;

public class Doctor extends HumanEntityExtension{

	public static final String PARAM_PROFESSIONS = "professions";

	private List<String> professions;

	public Doctor(){
		
	}

	public List<String> getProfessions() {
		return professions;
	}

	public void setProfessions(List<String> professions) {
		this.professions = professions;
	}
}
