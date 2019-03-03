package org.wecancodeit.beveragereviews.controllers;

import java.util.Optional;

import org.springframework.ui.Model;
import org.wecancodeit.beveragereviews.models.Category;
import org.wecancodeit.beveragereviews.repositories.CategoryRepository;

public class CategoryController {

	private CategoryRepository categoryRepo;

	public String findAllCategories(Model model) {
		model.addAttribute("categories", categoryRepo.findAll());
		return "categories";

	}

	public String findOneCategory(long id, Model model) {
		Optional<Category> category = categoryRepo.findById(id);
		if (category.isPresent()) {
			model.addAttribute("category", category.get());

		}
		return "category";

	}

}
