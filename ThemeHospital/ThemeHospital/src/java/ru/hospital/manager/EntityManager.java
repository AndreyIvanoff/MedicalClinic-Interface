package ru.hospital.manager;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.memcache.Expiration;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import java.util.*;
import java.util.logging.Logger;

/**
 * Обертка для работы с сущностями и кэшем.
 */
public class EntityManager {

	private static final Logger LOG = Logger.getLogger(EntityManager.class.getName());

	private static final DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	private static final MemcacheService memcache = MemcacheServiceFactory.getMemcacheService();

//---------------------------------------//
	public static Entity getEntity(Key key){
		Entity result = null;
		try {
			result = datastore.get(key);
		} catch (EntityNotFoundException ex) {
			LOG.warning("WARNING: Entity \"" + key.getKind() +"\"-\"" + key.getName() + "\" not found in datastore");
		}
		return result;
	}
//---------------------------------------//
	public static Entity getEntity(String kind, String name){
		Key key = KeyFactory.createKey(kind, name);
		return getEntity(key);
	}
//---------------------------------------//
	public static Entity getEntity(String kind, Long id){
		Key key = KeyFactory.createKey(kind, id);
		return getEntity(key);
	}
//---------------------------------------//
	public static Entity getEntity(String kind, String name, Key parent){
		Key key = KeyFactory.createKey(parent, kind, name);
		return getEntity(key);
	}
//---------------------------------------//
	public static Entity getEntity(String kind, Long id, Key parent){
		Key key = KeyFactory.createKey(parent, kind, id);
		return getEntity(key);
	}
//---------------------------------------//
	public static Entity getEntity(Query query){
		try {
			return datastore.prepare(query).asSingleEntity();
		} catch (Exception e) {
			LOG.warning("ERROR: request cannot be executed as single entity - results more than 1");
			return null;
		}
	}
//---------------------------------------//
	public static List<Entity> getEntities(Query query){
		return getEntities(query, FetchOptions.Builder.withDefaults());
	}
//---------------------------------------//
	public static List<Entity> getEntities(Query query, FetchOptions options){
		try {
			List<Entity> result = datastore.prepare(query).asList(options);
			return result;
		} catch (Exception e) {
			LOG.warning("ERROR: cannot get entities by query \"" + query.toString() + "\" from datastore, " + e.getMessage());
			return null;
		}
	}
//---------------------------------------//
	public static List<Entity> getEntities(Iterable<Key> keys){
		try {
			return new ArrayList(datastore.get(keys).values());
		} catch (Exception e){
			LOG.warning("ERROR: cannot get entities by keys from datastore, " + e.getMessage());
			return null;
		}
	}
//---------------------------------------//
	public static Integer getCount(Query query){
		int result = datastore.prepare(query).countEntities(FetchOptions.Builder.withDefaults());
		return result;
	}
//---------------------------------------//
	public static Key putEntity(Entity entity){
		Transaction transaction = datastore.beginTransaction();
		Key key = null;
		try {
			key = datastore.put(entity);
			transaction.commit();
		} catch (Exception e){
			LOG.warning("ERROR: cannot put entity in datastore, " + e.getMessage());
			if (transaction.isActive()) {
				transaction.rollback();
			}
		}
		return key;
	}
//---------------------------------------//
	/**
	 *  Работает только для сущностей одной группы (НЕ для корневых!)
	 */
	public static List<Key> putEntities(Iterable<Entity> entities){
		List<Key> result = null;
		Transaction transaction = datastore.beginTransaction();
		try {
			result = new ArrayList<Key>(datastore.put(entities));
			transaction.commit();
		} catch (Exception e){
			LOG.warning("ERROR: cannot put entities in datastore, " + e.getMessage());
			if (transaction.isActive()) {
				transaction.rollback();
			}
		}
		return result;
	}
//---------------------------------------//
	/**
	 *  Работает только для сущностей одной группы (НЕ для корневых!)
	 */
	public static boolean deleteEntities(Collection<Key> keys){
		memcache.deleteAll(keys);
		Transaction transaction = datastore.beginTransaction();
		try {
			datastore.delete(keys);
			transaction.commit();
			return true;
		} catch (Exception e){
			LOG.warning("ERROR: cannot delete entities from datastore, " + e.getMessage());
			if (transaction.isActive()) {
				transaction.rollback();
			}
			return false;
		}
	}
//---------------------------------------//
	public static boolean deleteEntity(String kind, String name){
		Key key = KeyFactory.createKey(kind, name);
		return deleteEntity(key);
	}
//---------------------------------------//
	public static boolean deleteEntity(String kind, Long id){
		Key key = KeyFactory.createKey(kind, id);
		return deleteEntity(key);
	}
//---------------------------------------//
	public static boolean deleteEntity(Key key){
		Transaction transaction = datastore.beginTransaction();
		try {
			datastore.delete(key);
			transaction.commit();
			return true;
		} catch (Exception e){
			LOG.warning("ERROR: cannot delete entity from datastore, " + e.getMessage());
			if (transaction.isActive()) {
				transaction.rollback();
			}
			return false;
		}
	}
//---------------------------------------//
	public static void putInCache(Entity entity, Key key, Integer expiration){
		if (entity == null){
			return;
		}
		try {
			memcache.put(key, entity, Expiration.byDeltaSeconds(expiration), MemcacheService.SetPolicy.SET_ALWAYS);
		} catch (Exception e) {
			LOG.info("WARNING: " + e.getMessage());
		}
	}
//---------------------------------------//
	public static void putInCache(Entity entity, Integer expiration){
		if (entity == null){
			return;
		}
		Key key = entity.getKey();
		try {
			memcache.put(key, entity, Expiration.byDeltaSeconds(expiration), MemcacheService.SetPolicy.SET_ALWAYS);
		} catch (Exception e){
			LOG.info("WARNING: " + e.getMessage());
		}
	}
//---------------------------------------//
	public static void putListInCache(List<Entity> entities, Integer expiration){
		HashMap<Key, Entity> map = new HashMap<Key, Entity>();
		for (Entity entity: entities){
			if (entity != null){
				map.put(entity.getKey(), entity);
			}
		}
		memcache.putAll(map, Expiration.byDeltaSeconds(expiration), MemcacheService.SetPolicy.SET_ALWAYS);

	}
//---------------------------------------//
	public static Entity getFromCache(String kind, Long id){
		Key key = KeyFactory.createKey(kind, id);
		return getFromCache(key);
	}

	public static Entity getFromCache(String kind, Long id, boolean refreshCache, Integer expiration){
		Key key = KeyFactory.createKey(kind, id);
		return getFromCache(key,refreshCache,expiration);
	}
//---------------------------------------//
	public static Entity getFromCache(String kind, String name){
		Key key = KeyFactory.createKey(kind, name);
		return getFromCache(key);
	}
//---------------------------------------//
	public static Entity getFromCache(Key key){
		return (Entity)memcache.get(key);
	}
//---------------------------------------//
	public static Entity getFromCache(Key key, boolean refreshCache, Integer expiration){
		Entity entity = (Entity)memcache.get(key);
		if (entity == null){
			entity = getEntity(key);
			if (entity != null && refreshCache){
				putInCache(entity, expiration);
			}
		}
		return entity;
	}
//---------------------------------------//
	public static List<Entity> getFromCache(List<Key> keys, boolean fillFromDatastore, boolean refreshCache, Integer expiration){
		Map<Key,Object> result = memcache.getAll(keys);
		List<Key> notFound = new ArrayList<Key>();
		List<Entity> fromDatastore = null;
		List<Entity> answer = new ArrayList<Entity>();
		if (fillFromDatastore){
			for (Key key : keys){
				if (!result.containsKey(key))
					notFound.add(key);
			}
			fromDatastore = getEntities(notFound);
			if (refreshCache && fromDatastore != null){
				putListInCache(fromDatastore, expiration);
			}
		}
		int i = 0;
		for (Key key : keys){
			if (result.containsKey(key)){
				answer.add((Entity)result.get(key));
			} else {
				if (fromDatastore != null && i < fromDatastore.size()){
					answer.add(fromDatastore.get(i));
					i++;
				}
			}
		}
		return answer;
	}
//---------------------------------------//
	public static void deleteFtomCache(Key key){
		memcache.delete(key);
	}
}


