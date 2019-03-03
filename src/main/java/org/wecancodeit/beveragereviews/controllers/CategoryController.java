package org.wecancodeit.beveragereviews.controllers;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wecancodeit.beveragereviews.models.Category;
import org.wecancodeit.beveragereviews.repositories.CategoryRepository;

@Controller
public class CategoryController {

	@Resource
	private CategoryRepository categoryRepo;
	
	@RequestMapping("/categories")
	public String findAllCategories(Model model) {
		model.addAttribute("categories", categoryRepo.findAll());
		return "categories";
	}

	@RequestMapping("/category")
	public String findOneCategory(@RequestParam(value = "id")long id, Model model) {
		Optional<Category> category = categoryRepo.findById(id);
		if (category.isPresent()) {
			model.addAttribute("category", category.get());

		}
		return "category";

	}

}
