package ru.hospital.manager;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ru.hospital.model.Event;

public class EventManager {

	public static void registerEvent(Event event){
		EntityManager.putEntity(event.convertToEntity());
	}
//---------------------------------//
	public static List<Event> getEvents(String type, Date start, Date end){
		return getEvents(type, null, null, null, null);
	}
//---------------------------------//
	public static List<Event> getEvents(String type, Long initiatorId, Date start, Date end){
		return getEvents(type, initiatorId, start, end, null);
	}
//---------------------------------//
	public static List<Event> getEvents(Long initiatorId, Date start, Date end){
		return getEvents(null, initiatorId, start, end, null);
	}
//---------------------------------//
	public static List<Event> getEvents(String type, String tag){
		return getEvents(type, null, null, null, tag);
	}
//---------------------------------//
	public static List<Event> getEvents(Long initiatorId, String tag){
		return getEvents(null, initiatorId, null, null, tag);
	}
//---------------------------------//
	public static List<Event> getEvents(String type, Long initiatorId, String tag){
		return getEvents(type, initiatorId, null, null, tag);
	}
//---------------------------------//
	private static List<Event> getEvents(String type, Long initiatorId, Date start, Date end, String tag){
		Query query = new Query(Event.class.getSimpleName());
		if (type != null){
			query.addFilter(Event.PARAM_TYPE, Query.FilterOperator.EQUAL, type);
		}
		if (initiatorId != null){
			query.addFilter(Event.PARAM_INITIATOR_ID, Query.FilterOperator.EQUAL, initiatorId);
		}
		if (start != null){
			query.addFilter(Event.PARAM_DATE, Query.FilterOperator.GREATER_THAN_OR_EQUAL, start);
		}
		if (end != null){
			query.addFilter(Event.PARAM_DATE, Query.FilterOperator.LESS_THAN_OR_EQUAL, end);
		}
		if (tag != null){
			query.addFilter(Event.PARAM_TAGS, Query.FilterOperator.LESS_THAN_OR_EQUAL, tag.toLowerCase());
		}

		List<Entity> entities = EntityManager.getEntities(query);
		List<Event> result = new ArrayList<Event>();

		for (Entity entity : entities){
			result.add(new Event(entity));
		}
		return result;
	}

}
