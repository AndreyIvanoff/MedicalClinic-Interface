package ru.hospital.provider;

import com.sun.jersey.api.client.ClientResponse.Status;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Класс при возникновении IllegalArgumentException возвращает статус
 * Status.BAD_REQUEST (400).
 */

@Provider
public class IllegalArgumentExceptionMapper implements ExceptionMapper<IllegalArgumentException> {
	private static final Logger LOG = Logger.getLogger(IllegalArgumentExceptionMapper.class.getName());

	@Override
	public Response toResponse(IllegalArgumentException exception) {
		LOG.warning(exception.getLocalizedMessage());
		return Response.status(Status.BAD_REQUEST).entity(exception.getLocalizedMessage()).build();
	}
}
