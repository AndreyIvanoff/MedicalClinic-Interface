package ru.hospital.manager;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import org.apache.geronimo.mail.util.Base64;
import ru.hospital.model.Administrator;
import ru.hospital.model.Event;
import ru.hospital.utils.Const;
import ru.hospital.utils.Encrypter;

public class AdministratorManager {

//-------------------------------------------//
	public static Long registerAdministrator(Administrator admin, Long creatorId){
		Administrator creator = getAdministrator(creatorId);
		if (!admin.isInformationFull()){
			throw new IllegalArgumentException("Личные данные администратора указаны не полностью.");
		}

		//пароль храним кешированый
		String password = new String(Base64.decode(Encrypter.encrypt(admin.getPassword())));

		if (creator.getPermissionLevel() < admin.getPermissionLevel()){
			throw new IllegalArgumentException("Вы не можете зарегистрировать нового администратора с правами выше ваших.");
		}

		if (admin.getPermissionLevel().equals(Administrator.PERMISSION_LEVEL_MAIN)){
			throw new IllegalArgumentException("Может быть только один главный Администратор");
		}

		admin.setPassword(password);
		admin.setRegisteredBy(creatorId);

		Entity entity = admin.convertToEntity();
		Key key = EntityManager.putEntity(entity);

		Event event;
		String tag = null;

		//если произошла ошибка записи - добавляем в запись события тэг "ОБЛОМ"
		if (key == null) {
			tag = Event.TAG_FAILED;
		}

		//если у сущности не было id - значит она создавалась, иначе - обновлялась
		if (admin.getId() == null) {
			event = new Event(Event.TYPE_ADMIN_CREATED, creator, null, tag);
		} else {
			event = new Event(Event.TYPE_ADMIN_UPDATED, creator, null, tag);
		}

		//записываем в регистратор событий - кто чего сделал
		EventManager.registerEvent(event);

		if (key != null){
			EntityManager.putInCache(entity, Const.CACHE_ENTITY);
			return entity.getKey().getId();
		} else {
			//не удалось создать администратора
			return null;
		}
	}
//-------------------------------------------//
	public static Long updateAdministrator(Administrator admin, Long updaterId){
		if (admin.getId() == null){
			throw new IllegalArgumentException("Не указан ID админитратора, обновление невозможно");
		}

		Administrator updater = getAdministrator(updaterId);
		Administrator oldEntity = getAdministrator(admin.getId());

		if (!updaterId.equals(admin.getId())){
			if (updater.getPermissionLevel() < oldEntity.getPermissionLevel() && updater.getPermissionLevel() < admin.getPermissionLevel()){
				throw new IllegalArgumentException("Вы не можете редактировать учетную запись администратора с правами выше ваших.");
			}
		} else {
			if (!oldEntity.getPermissionLevel().equals(admin.getPermissionLevel())){
				throw new IllegalArgumentException("Обратитесь к старшему администратору для изменения уровня доступа");
			}
		}

		Long id = registerAdministrator(admin, updaterId);
		return id;
	}
//-------------------------------------------//
	public static Administrator getAdministrator(Long id){
		Key key = generateKey(id);
		Entity entity = EntityManager.getFromCache(key, true, Const.CACHE_ENTITY);
		if (entity != null){
			return new Administrator(entity);
		} else {
			return null;
		}

	}
//-------------------------------------------//
	private static Key generateKey(Long id){
		return KeyFactory.createKey(Administrator.class.getSimpleName(), id);
	}

}
