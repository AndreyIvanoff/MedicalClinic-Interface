package ru.hospital.model.base;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Text;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import ru.hospital.utils.DateUtils;

/**
 * Класс, реализующий функционал перевода модели в сущность и обратно.
 * Любой класс, наследуемый от него, способен самостоятельно
 * преобразовываться в Entity и обратно при соблюдении следующих условий:
 *
 * <li>Индексируемые поля имеют типы String, Long, Double или Date или их списки</li>
 * <li>Неиндексируемые поля имеют любые примитивные типы, списочные не поддреживаются</li>
 *
 * @author a.yakischik
 */
public abstract class EntityExtension {

	protected static final Logger LOG = Logger.getLogger(EntityExtension.class.getName());

	private static final String GET = "get";
	private static final String SET = "set";
	private static final String FROM_TEXT = "FromText";
	private static final String TO_TEXT = "FromText";

	protected Entity entity;

	public Long id;
	public String additional;

	public Entity convertToEntity() {
		if (id == null){
			entity = new Entity(this.getClass().getSimpleName());
		} else {
			entity = new Entity(this.getClass().getSimpleName(), id);
		}
		List<Method> getters = getGetters();
		try {
			for (Method method: getters){
				String name = method.getName().substring(3,4).toLowerCase() + method.getName().substring(4);
				Object value = method.invoke(this);
				Field field = this.getClass().getField(name);
				if (!field.isAnnotationPresent(TextField.class)){
					entity.setProperty(name, value);
				} else {
					Method setter = this.getClass().getMethod(SET + method.getReturnType().getSimpleName() + TO_TEXT);
					setter.invoke(this, name, value);
				}
			}
		} catch (Exception e) {
			LOG.severe("Can't convert TO entity: " + e.getMessage());
		}
		return entity;
	}

	public final void convertFromEntity(){
		if (entity == null){
			return;
		}
		List<Method> setters = getSetters();
		try {
			for (Method method: setters){
				String name = method.getName().substring(3,4).toLowerCase() + method.getName().substring(4);
				Field field = this.getClass().getField(name);
				if (!field.isAnnotationPresent(TextField.class)){
					Object value = entity.getProperty(name);
					method.invoke(this, value);
				} else {
					Method getter = this.getClass().getMethod(GET + method.getReturnType().getSimpleName() + FROM_TEXT);
					Object value = getter.invoke(name);
					method.invoke(this, value);
				}
			}
		} catch (Exception e) {
			LOG.severe("Can't convert TO entity: " + e.getMessage());
		}
		if (entity.getKey().isComplete()){
			this.setId(entity.getKey().getId());
		}
	}
//----------------------------//
	public EntityExtension(){
		entity = new Entity(this.getClass().getSimpleName());
	}

	public EntityExtension(Entity entity){
		this.entity = entity;
		convertFromEntity();
	}

	private List<Method> getSetters(){
		return getMethodsWithPrefix(SET);
	}

	private List<Method> getGetters(){
		return getMethodsWithPrefix(GET);
	}

	private List<Method> getMethodsWithPrefix(String preffix){
		List<Method> result = new ArrayList<Method>();
		for (Method method: this.getClass().getMethods()){
			if (method.getName().startsWith(preffix)){
				result.add(method);
			}
 		}
		return result;
	}

//----------------------------//
	protected Date getDateFromText(String name){
		Text text = (Text) entity.getProperty(name);

		if (text == null ||text == null || text.getValue().isEmpty()){
			return null;
		}
		try {
			Date date = DateUtils.getFullDate(text.getValue());
			return date;
		} catch (Exception e){
			return null;
		}
	}

	protected void setDateToText(String name, Date date){
		Text text = new Text("");
		if (date != null){
			text = new Text(DateUtils.getFullDate(date));
		}
			this.entity.setProperty(name, text);
	}

//----------------------------//
	protected Double getDoubleFromText(String name){
		Text text = (Text) entity.getProperty(name);
		if (text == null || text.getValue() == null || text.getValue().isEmpty()){
			return null;
		}
		return Double.parseDouble(text.getValue());
	}

	protected void setDoubleToText(String name, Double value){
		Text text = new Text("");
		if (value != null){
			text = new Text(value.toString());
		}
		this.entity.setProperty(name, text);
	}
//----------------------------//
	protected Long getLongFromText(String name){
		Text text = (Text) entity.getProperty(name);
		if (text == null || text.getValue() == null || text.getValue().isEmpty()){
			return null;
		}
		return Long.parseLong(text.getValue());
	}

	protected void setLongToText(String name, Long value){
		Text text = new Text("");
		if (value != null){
			text = new Text(value.toString());
		}
		this.entity.setProperty(name, text);
	}
//----------------------------//
	protected String getStringFromText(String name){
		Text text = (Text) entity.getProperty(name);
		if (text == null || text == null || text.getValue() == null || text.getValue().isEmpty()){
			return null;
		}
		return text.getValue();
	}

	protected void setStringToText(String name, String value){
		Text text = new Text("");
		if (value != null)
			text = new Text(value);
		this.entity.setProperty(name, text);
	}
//----------------------------//
	protected Float getFloatFromText(String name){
		Double temp = getDoubleFromText(name);
		return temp.floatValue();
	}

	protected void setFloatToText(String name, Float value){
		Double temp = (double)value.floatValue();
		setDoubleToText(name, temp);
	}
//----------------------------//
	protected Integer getIntegerToText(String name){
		Long temp = getLongFromText(name);
		return temp.intValue();
	}

	protected void setIntegerToText(String name, Integer value){
		Long temp = (long)value.intValue();
		setLongToText(name, temp);
	}
//----------------------------//
	protected Short getShortToText(String name){
		Long temp = getLongFromText(name);
		return temp.shortValue();
	}

	protected void setShortToText(String name, Short value){
		Long temp = (long)value.shortValue();
		setLongToText(name, temp);
	}
//----------------------------//
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
