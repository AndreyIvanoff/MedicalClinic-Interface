package ru.hospital.model;

import com.google.appengine.api.datastore.Entity;
import ru.hospital.model.base.HumanEntityExtension;

public class Administrator extends HumanEntityExtension{

	public static final Long PERMISSION_LEVEL_MAIN = 100L;
	public static final Long PERMISSION_LEVEL_HIGH = 80L;
	public static final Long PERMISSION_LEVEL_MEDIUM = 60L;
	public static final Long PERMISSION_LEVEL_LOW = 40L;
	public static final Long PERMISSION_LEVEL_VIEW = 20L;
	public static final Long PERMISSION_LEVEL_BLOCKED = 0L;

	private String password;
	private Long permissionLevel;
	private Long registeredBy;
	private String login;

	public Administrator(Entity entity){
		super(entity);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getPermissionLevel() {
		return permissionLevel;
	}

	public void setPermissionLevel(Long permissionLevel) {
		this.permissionLevel = permissionLevel;
	}

	public Long getRegisteredBy() {
		return registeredBy;
	}

	public void setRegisteredBy(Long registeredBy) {
		this.registeredBy = registeredBy;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public boolean isInformationFull() {
		if (!super.isInformationFull()){
			return false;
		}
		return (permissionLevel != null && password != null);
	}


}
