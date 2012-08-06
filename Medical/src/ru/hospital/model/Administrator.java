package ru.hospital.model;

import ru.hospital.model.base.HumanEntityExtension;

public class Administrator extends HumanEntityExtension{

	public static final Long PERMISSION_LEVEL_MAIN = 100L;
	public static final Long PERMISSION_LEVEL_HIGH = 80L;
	public static final Long PERMISSION_LEVEL_MEDIUM = 60L;
	public static final Long PERMISSION_LEVEL_LOW = 40L;
	public static final Long PERMISSION_LEVEL_VIEW = 20L;
	public static final Long PERMISSION_LEVEL_BLOCKED = 0L;
	
	public static final String PERMISSION_LEVEL_NAME_MAIN = "Все действия";
	public static final String PERMISSION_LEVEL_NAME_HIGH = "Администратор";
	public static final String PERMISSION_LEVEL_NAME_MEDIUM = "Регистрация врачей";
	public static final String PERMISSION_LEVEL_NAME_LOW = "Регистрация клиентов";
	public static final String PERMISSION_LEVEL_NAME_VIEW = "Только просмотр";
	public static final String PERMISSION_LEVEL_NAME_BLOCKED = "Заблокирован";

	private String password;
	private Long permissionLevel;
	private Long registeredBy;
	private String login;

	public Administrator(){
		
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
	public String toString(){
		return getLastName();
	}
}
