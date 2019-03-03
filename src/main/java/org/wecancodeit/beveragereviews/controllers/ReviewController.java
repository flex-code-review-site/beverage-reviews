package org.wecancodeit.beveragereviews.controllers;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wecancodeit.beveragereviews.models.Review;
import org.wecancodeit.beveragereviews.repositories.ReviewRepository;

@Controller
public class ReviewController {

	@Resource
	private ReviewRepository reviewRepo;

	@RequestMapping("/reviews")
	public String findAllReviews(Model model) {
		model.addAttribute("reviews", reviewRepo.findAll());
		return "reviews";
	}

	@RequestMapping("/review")
	public String findOneReview(@RequestParam(value = "id")long id, Model model) {
		Optional<Review> review = reviewRepo.findById(id);
		if (review.isPresent()) {
			model.addAttribute("review", review.get());
			return "review";
		}
			return null;
	}

}
