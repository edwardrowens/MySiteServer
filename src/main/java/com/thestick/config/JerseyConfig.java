package com.thestick.config;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.thestick.model.ErrorResponsePayload;

@Configuration
public class JerseyConfig extends ResourceConfig {
	@Autowired
	public JerseyConfig(ObjectMapper objectMapper) {
		packages("com.thestick");
		register(new ObjectMapperContextResolver(objectMapper));
		
		// Required to get rid of jersey's default exception mappers
		register(JacksonJaxbJsonProvider.class);
		this.property(CommonProperties.FEATURE_AUTO_DISCOVERY_DISABLE, true);
	}

	@Provider
	public static class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {

		private final ObjectMapper mapper;

		public ObjectMapperContextResolver(ObjectMapper mapper) {
			this.mapper = mapper;
		}

		@Override
		public ObjectMapper getContext(Class<?> type) {
			return mapper;
		}
	}
	
	@Provider
	public static class JsonMappingExceptionMapper implements ExceptionMapper<JsonMappingException> {
		@Override
		public Response toResponse(JsonMappingException exception) {
			return Response.status(Status.BAD_REQUEST).entity(ErrorResponsePayload.create("Incorrect request payload format")).type(MediaType.APPLICATION_JSON).build();
		}
	}
}
