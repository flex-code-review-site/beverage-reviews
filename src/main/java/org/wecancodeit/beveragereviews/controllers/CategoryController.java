package org.wecancodeit.beveragereviews.controllers;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.wecancodeit.beveragereviews.models.Category;
import org.wecancodeit.beveragereviews.models.Review;
import org.wecancodeit.beveragereviews.repositories.CategoryRepository;
import org.wecancodeit.beveragereviews.repositories.ReviewRepository;

@Controller
public class CategoryController {

	@Resource
	private CategoryRepository categoryRepo;
	
	@Resource
	private ReviewRepository reviewRepo;

	@RequestMapping("/categories")
	public String findAllCategories(Model model) {
		model.addAttribute("categories", categoryRepo.findAll());

		return "categories";
	}

	@RequestMapping("/category")
	public String findOneCategory(@RequestParam(value = "id") long id, Model model) throws CategoryNotFoundException {
		Optional<Category> category = categoryRepo.findById(id);
		if (category.isPresent()) {
			model.addAttribute("category", category.get());
			model.addAttribute("reviews", category.get().getReviews());
			return "category";
		}
		throw new CategoryNotFoundException();

	}

	@RequestMapping(path = "/categories/{name}", method = RequestMethod.POST)
	public String addCategory(@PathVariable String name, Model model) {
		Category newCategory = categoryRepo.findByName(name);

		if (newCategory == null) {
			newCategory = categoryRepo.save(new Category(name));
		}

		model.addAttribute("categories", categoryRepo.findAll());

		return "partials/categories-list-added";
	}

	@RequestMapping(path = "/categories/remove/{id}", method = RequestMethod.POST)
	public String removeCategory(@PathVariable Long id, Model model) {
		Optional<Category> categoryToRemoveResult = categoryRepo.findById(id);
		Category categoryToRemove = categoryToRemoveResult.get();

		for (Review review : categoryToRemove.getReviews()) {
			reviewRepo.save(review.removeCategory(categoryToRemove));
		}

		categoryRepo.delete(categoryToRemove);

		model.addAttribute("categories", categoryRepo.findAll());

		return "partials/tags-list-removed";
	}
}
