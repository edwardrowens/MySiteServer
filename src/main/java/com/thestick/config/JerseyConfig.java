package com.thestick.config;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.google.auto.value.AutoValue;
import com.thestick.model.ErrorResponsePayload;

@Configuration
public class JerseyConfig extends ResourceConfig {
	
	@Autowired
	public JerseyConfig(ObjectMapper objectMapper) {
		addResources();
		
		property(ServletProperties.FILTER_FORWARD_ON_404, true);
		
		// Required to get rid of jersey's default exception mappers
		register(JacksonJaxbJsonProvider.class);
		property(CommonProperties.FEATURE_AUTO_DISCOVERY_DISABLE, true);
	}

	@Provider
	public static class JsonMappingExceptionMapper implements ExceptionMapper<JsonMappingException> {
		@Override
		public Response toResponse(JsonMappingException exception) {
			return Response.status(Status.BAD_REQUEST).entity(ErrorResponsePayload.create("Incorrect request payload format")).type(MediaType.APPLICATION_JSON).build();
		}
	}
	
	private void addResources() {
		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
		scanner.addIncludeFilter(new AnnotationTypeFilter(Provider.class));
		scanner.addIncludeFilter(new AnnotationTypeFilter(Path.class));
		scanner.addIncludeFilter(new AnnotationTypeFilter(AutoValue.class));
		for (BeanDefinition beanDefinition : scanner.findCandidateComponents("com.thestick")) {
			registerClasses(ClassUtils.resolveClassName(beanDefinition.getBeanClassName(), getClassLoader()));
		}
	}
}
