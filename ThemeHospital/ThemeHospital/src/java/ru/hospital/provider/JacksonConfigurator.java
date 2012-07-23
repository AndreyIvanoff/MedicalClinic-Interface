package ru.hospital.provider;

import javax.ws.rs.Produces;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import ru.hospital.utils.ConstLiteral;

/**
 * Класс для настройки Jackson.
 */
@Provider
@Produces(ConstLiteral.FORMAT_APPLICATION_JSON_UTF8)
public class JacksonConfigurator implements ContextResolver<ObjectMapper> {
	private final transient ObjectMapper mapper = new ObjectMapper();

	public JacksonConfigurator() {
		SerializationConfig serConfig = mapper.getSerializationConfig();

		// поля имеющие значение null, не будут попадать в JSON.
		serConfig.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);

		// даты по умолчанию будут записываться как timestamp
		mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, true);
	}

	@Override
	public ObjectMapper getContext(Class<?> arg0) {
		return mapper;
	}
}
