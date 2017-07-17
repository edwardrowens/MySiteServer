package com.thestick.resource;

import java.time.LocalDateTime;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import com.thestick.model.Comment;
import com.thestick.resource.request.CreateCommentRequestPayload;
import com.thestick.resource.response.CreateCommentResponsePayload;
import com.thestick.service.CommentService;

@Path("/{threadId}/comments")
public class CommentResource {
	
	private final CommentService commentService;

	@Autowired	
	public CommentResource(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@GET
	@Path("/{commentId}")
	public Comment getComment(@PathParam("commentId") long commentId) {
		return commentService.retrieveComment(commentId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public CreateCommentResponsePayload createComment(@PathParam("threadId") long threadId, CreateCommentRequestPayload createCommentRequestPayload) {
		Comment comment = Comment.create(0, createCommentRequestPayload.creator(), LocalDateTime.now(), createCommentRequestPayload.contents(), threadId);
		long commentId = commentService.createComment(comment);
		return CreateCommentResponsePayload.create(commentId);
	}
}
