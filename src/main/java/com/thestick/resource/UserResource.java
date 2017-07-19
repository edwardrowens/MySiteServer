package com.thestick.resource;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import com.thestick.model.User;
import com.thestick.resource.request.CreateUserRequestPayload;
import com.thestick.resource.request.LoginRequestPayload;
import com.thestick.service.UserService;

@Path("/users")
public class UserResource {
	
	private final UserService userService;

	@Autowired
	public UserResource(UserService userService) {
		this.userService = userService;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUser(CreateUserRequestPayload createUserRequestPayload) {
		User user;
		try {
			user = userService.createUser(createUserRequestPayload.getUsername(), createUserRequestPayload.getPassword());
		} catch (IllegalArgumentException e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		return Response.ok(user).build();
	}
	
	@PUT
	@Path("/{username}/login")
	public Response login(@PathParam("username") String username, LoginRequestPayload loginRequestPayload) {
		boolean auth = false;
		try {
			auth = userService.authenticateUser(username, loginRequestPayload.getPassword());
		} catch (IllegalArgumentException e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		return Response.status(auth ? Status.OK : Status.BAD_REQUEST).build();
	}
	
}
