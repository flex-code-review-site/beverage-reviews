package org.wecancodeit.beveragereviews.controllers;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.wecancodeit.beveragereviews.models.Review;
import org.wecancodeit.beveragereviews.models.Tag;
import org.wecancodeit.beveragereviews.repositories.ReviewRepository;
import org.wecancodeit.beveragereviews.repositories.TagRepository;

@Controller
public class TagController {

	@Resource
	private TagRepository tagRepo;

	@Resource
	private ReviewRepository reviewRepo;

	@RequestMapping("/tags")
	public String findAllTags(Model model) {
		model.addAttribute("tags", tagRepo.findAll());
		return "tags";
	}

	@RequestMapping("/tag")
	public String findOneTag(@RequestParam(value = "id") Long id, Model model) throws TagNotFoundException {
		Optional<Tag> tag = tagRepo.findById(id);
		if (tag.isPresent()) {
			model.addAttribute("tag", tag.get());
			return "tag";
		}
		throw new TagNotFoundException();
	}

	@RequestMapping("/add-tag")
	public String addTag(String tagName, Long reviewId) {
		Optional<Review> review = reviewRepo.findById(reviewId);
		Tag tag = tagRepo.findByNameIgnoreCase(tagName);
		if (tag == null) {
			tag = tagRepo.save(new Tag(tagName));
		}
		if (review.isPresent()) {
			review.get().addTag(tag);
			reviewRepo.save(review.get());
		}
		return "redirect:/review?id=" + reviewId;
	}
	
	@RequestMapping(path = "/tags/{name}", method = RequestMethod.POST)
	public String addTag(@PathVariable String name , Model model )
	{
     Tag newTag = tagRepo.findByName(name);
     
     if(newTag == null) {
    	 newTag = tagRepo.save(new Tag(name));
     }
     
	 model.addAttribute("tags", tagRepo.findAll());
	 
     return "partials/tags-list-added";
	}
	
	@RequestMapping(path = "/tags/remove/{id}", method = RequestMethod.POST)
	public String removeTag(@PathVariable Long id , Model model )
	{
     Optional<Tag> tagToRemoveResult = tagRepo.findById(id);
     Tag tagToRemove = tagToRemoveResult.get();
     
     for(Review review:tagToRemove.getReviews()) {
        reviewRepo.save(review.removeTag(tagToRemove));
     }
     
     tagRepo.delete(tagToRemove);
     
	 model.addAttribute("tags", tagRepo.findAll());
	 
     return "partials/tags-list-removed";
	}
	

}
