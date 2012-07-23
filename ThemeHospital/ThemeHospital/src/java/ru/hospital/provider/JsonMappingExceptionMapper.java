package ru.hospital.provider;

import com.sun.jersey.api.client.ClientResponse.Status;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.codehaus.jackson.map.JsonMappingException;

/**
 * Класс при возникновении JsonMappingException возвращает статус
 * Status.BAD_REQUEST.
 */
@Provider
public class JsonMappingExceptionMapper implements ExceptionMapper<JsonMappingException> {
	private static final Logger LOG = Logger.getLogger(JsonMappingExceptionMapper.class.getName());

	@Override
	public Response toResponse(JsonMappingException exception) {
		LOG.warning(exception.getLocalizedMessage());
		return Response.status(Status.BAD_REQUEST).entity(exception.getLocalizedMessage()).build();
	}
}
