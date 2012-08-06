package ru.hospital.model.base;

/**
 * @author a.yakischik
 */
public abstract class EntityExtension {	

	public Long id;
	public String additional;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdditional() {
		return additional;
	}

	public void setAdditional(String additional) {
		this.additional = additional;
	}
}
