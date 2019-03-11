package org.wecancodeit.beveragereviews.controllers;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wecancodeit.beveragereviews.models.Category;
import org.wecancodeit.beveragereviews.models.Review;
import org.wecancodeit.beveragereviews.models.Tag;
import org.wecancodeit.beveragereviews.repositories.CategoryRepository;
import org.wecancodeit.beveragereviews.repositories.ReviewRepository;
import org.wecancodeit.beveragereviews.repositories.TagRepository;

@Controller
public class ReviewController {

	@Resource
	private ReviewRepository reviewRepo;
	
	@Resource
	private TagRepository tagRepo;
	
	@Resource
	private CategoryRepository categoryRepo;

	@RequestMapping("/reviews")
	public String findAllReviews(Model model) {
		model.addAttribute("reviews", reviewRepo.findAll());
		return "reviews";
	}

	@RequestMapping("/review")
	public String findOneReview(@RequestParam(value = "id")long id, Model model) throws ReviewNotFoundException {
		Optional<Review> review = reviewRepo.findById(id);
		if (review.isPresent()) {
			model.addAttribute("reviews", review.get());
			return "review";
		}
	    throw new ReviewNotFoundException();
	}
    
	@RequestMapping("/add-review")
	public String addReview(String name, String description, String categoryName, String tagName) {
		Tag newTag = tagRepo.findByName(tagName);
		
		if(newTag == null) {
			newTag = tagRepo.save(new Tag(tagName));
		}
		
		Category newCategory = categoryRepo.findByName(categoryName);
		
	    if(newCategory == null) {
	    	newCategory = categoryRepo.save(new Category(categoryName));
	    }
	    
		Review newReview = reviewRepo.findByName(name);
		
		if(newReview == null) {
			newReview = reviewRepo.save(new Review(name,description,newCategory,newTag));
		}
		
		return "redirect:/reviews";
	}

}
