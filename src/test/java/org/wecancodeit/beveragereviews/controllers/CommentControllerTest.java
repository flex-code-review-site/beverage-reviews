package org.wecancodeit.beveragereviews.controllers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.wecancodeit.beveragereviews.models.Comment;
import org.wecancodeit.beveragereviews.models.Review;
import org.wecancodeit.beveragereviews.repositories.CommentRepository;
import org.wecancodeit.beveragereviews.repositories.ReviewRepository;

public class CommentControllerTest {
	
	@InjectMocks
	private CommentController underTest;
	
	@Mock
	private ReviewRepository reviewRepo;
	
	@Mock
	private CommentRepository commentRepo;
	
	@Mock
	private Comment comment;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldAddAdditionalCommentToReview() {
		String reviewName = "review name";
		Review newReview = reviewRepo.findByName(reviewName);
		String commentContent = "new content";
		underTest.addComment(commentContent, reviewName);
		Comment newComment = new Comment(commentContent, newReview);
		when(commentRepo.save(newComment)).thenReturn(newComment);
	}
	
	@Test
	public void shouldRemoveCommentFromReviewById() {
		Long commentId = comment.getId();
		when(commentRepo.findById(commentId)).thenReturn(Optional.of(comment));
		underTest.deleteCommentById(commentId);
		verify(commentRepo).delete(comment);
	}
}
