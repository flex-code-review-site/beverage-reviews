package org.wecancodeit.beveragereviews.controllers;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wecancodeit.beveragereviews.models.Comment;
import org.wecancodeit.beveragereviews.models.Review;
import org.wecancodeit.beveragereviews.repositories.CommentRepository;
import org.wecancodeit.beveragereviews.repositories.ReviewRepository;


@Controller
public class CommentController {

	@Resource
	private ReviewRepository reviewRepo;
	
	@Resource
	private CommentRepository commentRepo;
	
	@RequestMapping("/add-comment")
	public String addComment(String commentContent, String reviewName) {
		Review review = reviewRepo.findByName(reviewName);
		Comment newComment = commentRepo.findByContent(commentContent);
		Long reviewId = 1L;
		if (review != null) {
			reviewId = review.getId();
		}
		if (newComment == null) {
			newComment = new Comment(commentContent, review);
			commentRepo.save(newComment);
		}
		return "redirect:/review?id=" + reviewId;
	}
}
