package ru.hospital.manager;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import java.util.ArrayList;
import java.util.List;
import ru.hospital.model.Administrator;
import ru.hospital.model.Doctor;
import ru.hospital.model.Event;
import ru.hospital.model.Pacient;
import ru.hospital.utils.Const;

public class PacientManager {

//-------------------------------------------//
	public static Long createPacient(Pacient pacient, Long creatorId){
		Administrator creator = AdministratorManager.getAdministrator(creatorId);
		if (creator.getPermissionLevel() < Const.PERMISSION_LEVEL_TO_CREATE_PACIENTS){
			throw new IllegalArgumentException("У вас недостаточно прав для регистрации нового пациента.");
		}
		if (!pacient.isInformationFull()){
			throw new IllegalArgumentException("Недостаточно данных для регистрации нового пациента.");
		}
		Entity entity = pacient.convertToEntity();

		Key key = EntityManager.putEntity(entity);
		EntityManager.putInCache(entity, Const.CACHE_ENTITY);

		Event event;
		String tag = null;
		if (key == null) {
			tag = Event.TAG_FAILED;
		}

		if (pacient.getId() == null) {
			event = new Event(Event.TYPE_PACIENT_CREATED, creator, null, tag);
		} else {
			event = new Event(Event.TYPE_PACIENT_UPDATED, creator, null, tag);
		}

		EventManager.registerEvent(event);

		if (key != null){
			EntityManager.putInCache(entity, Const.CACHE_ENTITY);
			return entity.getKey().getId();
		} else {
			//не удалось создать доктора
			return null;
		}
	}
//-------------------------------------------//

	public static Long updatePacient(Pacient pacient, Long updaterId){
		return createPacient(pacient, updaterId);
	}
//-------------------------------------------//

	/**
	 * Ставить null для того параметра, учитывать который не нужно
	 */
	public static List<Pacient> getPacients(String name, String tag){
		Query query = new Query(Pacient.class.getSimpleName());
		if (name != null){
			query.addFilter(Doctor.PARAM_LAST_NAMES, Query.FilterOperator.EQUAL, name);
		}
		if (tag != null){
			query.addFilter(Doctor.PARAM_TAG, Query.FilterOperator.EQUAL, tag);
		}
		query.setKeysOnly();
		List<Entity> entities = EntityManager.getEntities(query);
		List<Key> keys = new ArrayList<Key>();
		for (Entity entity: entities){
			keys.add(entity.getKey());
		}
		return getByKeys(keys);
	}
//-------------------------------------------//
	public static Pacient getPacient(Long id){
		Key key = generateKey(id);
		Entity entity = EntityManager.getFromCache(key, true, Const.CACHE_ENTITY);
		return new Pacient(entity);
	}
//-------------------------------------------//
	public static List<Pacient> getByIds(List<Long> ids){
		List<Key> keys = new ArrayList<Key>();
		for (Long id : ids){
			keys.add(generateKey(id));
		}

		return getByKeys(keys);
	}
//-------------------------------------------//
	public static List<Pacient> getByKeys(List<Key> keys){
		List<Pacient> result = new ArrayList<Pacient>();
		List<Entity> entities = EntityManager.getFromCache(keys, true, true, Const.CACHE_ENTITY);

		for (Entity entity: entities){
			result.add(new Pacient(entity));
		}
		return result;
	}
//-------------------------------------------//
	public static Key generateKey(Long id){
		return KeyFactory.createKey(Pacient.class.getSimpleName(), id);
	}
}
