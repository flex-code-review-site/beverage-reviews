package org.wecancodeit.beveragereviews.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.wecancodeit.beveragereviews.models.Review;
import org.wecancodeit.beveragereviews.repositories.ReviewRepository;

@Controller
public class ReviewController {

	private ReviewRepository reviewRepo;

	public String findAllReviews(Model model) {
		model.addAttribute("reviews", reviewRepo.findAll());
		return "reviews";
	}

	public String findOneReview(Long id, Model model) {
		Optional<Review> review = reviewRepo.findById(id);
		if (review.isPresent()) {
			model.addAttribute("review", review.get());
		}
		return "review";

	}

}
