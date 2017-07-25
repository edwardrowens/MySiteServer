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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.thestick.model.CommentThread;
import com.thestick.model.ErrorResponsePayload;
import com.thestick.resource.request.CreateCommentThreadRequestPayload;
import com.thestick.resource.response.CreateCommentThreadResponsePayload;
import com.thestick.service.CommentThreadService;

@Path("/threads")
public class CommentThreadResource {
	
	private static final Logger logger = LoggerFactory.getLogger(CommentThreadResource.class.getName());
	
	private final CommentThreadService commentThreadService;

	@Autowired
	public CommentThreadResource(CommentThreadService commentThreadService) {
		this.commentThreadService = commentThreadService;
	}
	
	@GET
	@Path("/{commentThreadId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveCommentThread(@PathParam("commentThreadId") long commentThreadId) {
		logger.info("Retrieving comment thread with ID <{}>", commentThreadId);
		try {
			CommentThread commentThread =  commentThreadService.retrieveCommentThread(commentThreadId);
			logger.info("Sending comment thread <{}>", commentThread);
			return  Response.ok(commentThread).build();
		} catch (IllegalArgumentException e) {
			logger.info("Could not find comment thread with ID <{}>", commentThreadId);
			return Response.status(Status.NOT_FOUND).entity(ErrorResponsePayload.create("Invalid thread ID")).build();
		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createCommentThread(CreateCommentThreadRequestPayload request) {
		CommentThread commentThread = CommentThread.create(0, LocalDateTime.now(), request.getCreator());
		logger.info("Creating comment thread <{}>", commentThread);
		long commentThreadId;
		try {
			commentThreadId = commentThreadService.createCommentThread(commentThread);
		} catch (IllegalArgumentException e) {
			logger.info("Could not creat comment thread due to <{}>", e.getMessage());
			return Response.status(Status.BAD_REQUEST).entity(ErrorResponsePayload.create(e.getMessage())).build();
		}
		logger.info("Created comment thread <{}>", commentThread);
		return Response.ok(CreateCommentThreadResponsePayload.create(commentThreadId)).build();
	}
}
