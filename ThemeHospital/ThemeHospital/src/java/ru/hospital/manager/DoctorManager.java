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
import ru.hospital.utils.Const;

public class DoctorManager {

//-------------------------------------------//
	public static Long createDoctor(Doctor doctor, Long creatorId){
		Administrator creator = AdministratorManager.getAdministrator(creatorId);
		if (creator.getPermissionLevel() < Const.PERMISSION_LEVEL_TO_CREATE_DOCTORS){
			throw new IllegalArgumentException("У вас недостаточно прав для регистрации сотрудников клиники.");
		}
		if (!doctor.isInformationFull()){
			throw new IllegalArgumentException("Недостаточно данных для регистрации сотрудника клиники.");
		}
		Entity entity = doctor.convertToEntity();

		Key key = EntityManager.putEntity(entity);
		EntityManager.putInCache(entity, Const.CACHE_ENTITY);

		Event event;
		String tag = null;
		if (key == null) {
			tag = Event.TAG_FAILED;
		}

		if (doctor.getId() == null) {
			event = new Event(Event.TYPE_DOCTOR_CREATED, creator, null, tag);
		} else {
			event = new Event(Event.TYPE_DOCTOR_UPDATED, creator, null, tag);
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
	public static Long updateDoctor(Doctor doctor, Long creatorId){
		return createDoctor(doctor, creatorId);
	}
//-------------------------------------------//
	/**
	 * Ставить null для того параметра, учитывать который не нужно
	 */
	public static List<Doctor> getDoctors(String name, String profession, String tag){
		Query query = new Query(Doctor.class.getSimpleName());
		if (name != null){
			query.addFilter(Doctor.PARAM_LAST_NAMES, Query.FilterOperator.EQUAL, name);
		}
		if (profession != null){
			query.addFilter(Doctor.PARAM_PROFESSIONS, Query.FilterOperator.EQUAL, profession);
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
	public static Doctor getDoctor(Long id){
		Key key = generateKey(id);
		Entity entity = EntityManager.getFromCache(key, true, Const.CACHE_ENTITY);
		return new Doctor(entity);
	}
//-------------------------------------------//
	public static List<Doctor> getByIds(List<Long> ids){
		List<Key> keys = new ArrayList<Key>();
		for (Long id : ids){
			keys.add(generateKey(id));
		}

		return getByKeys(keys);
	}
//-------------------------------------------//
	public static List<Doctor> getByKeys(List<Key> keys){
		List<Doctor> result = new ArrayList<Doctor>();
		List<Entity> entities = EntityManager.getFromCache(keys, true, true, Const.CACHE_ENTITY);

		for (Entity entity: entities){
			result.add(new Doctor(entity));
		}
		return result;
	}
//-------------------------------------------//
	public static Key generateKey(Long id){
		return KeyFactory.createKey(Doctor.class.getSimpleName(), id);
	}
}
