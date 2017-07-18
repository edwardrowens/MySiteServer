package com.thestick.resource;

import java.time.LocalDateTime;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import com.thestick.model.CommentThread;
import com.thestick.resource.request.CreateCommentThreadRequestPayload;
import com.thestick.resource.response.CreateCommentThreadResponsePayload;
import com.thestick.service.CommentThreadService;

@Path("/threads")
public class CommentThreadResource {

	private final CommentThreadService commentThreadService;

	@Autowired
	public CommentThreadResource(CommentThreadService commentThreadService) {
		this.commentThreadService = commentThreadService;
	}
	
	@GET
	@Path("/{commentThreadId}")
	@Produces(MediaType.APPLICATION_JSON)
	public CommentThread retrieveCommentThread(@PathParam("commentThreadId") long commentThreadId) {
		return commentThreadService.retrieveCommentThread(commentThreadId);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createCommentThread(CreateCommentThreadRequestPayload request) {
		CommentThread commentThread = CommentThread.create(0, LocalDateTime.now(), request.getCreator());
		long commentThreadId;
		try {
			commentThreadId = commentThreadService.createCommentThread(commentThread);
		} catch (IllegalArgumentException e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		return Response.ok(CreateCommentThreadResponsePayload.create(commentThreadId)).build();
	}
}
