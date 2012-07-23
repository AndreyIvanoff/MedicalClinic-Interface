package ru.hospital.filter;

/**
 * Модельный класс, его объекты создаются на уровне фильтра
 * путем вытаскивания из кук ID пользователя (или еще какой-нибудь инфы).
 */

//TODO как определимся с куками - решить, какая инфа тут должна быть
public class InjectInfo {

	public Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
