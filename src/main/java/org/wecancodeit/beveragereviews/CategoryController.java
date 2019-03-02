package org.wecancodeit.beveragereviews;

import org.springframework.ui.Model;
import org.wecancodeit.beveragereviews.repositories.CategoryRepository;

public class CategoryController {

	
	private CategoryRepository categoryRepo;

	public String findAllCategories(Model model) {
	model.addAttribute("categories", categoryRepo.findAll());
	return "categories";
		
	}

}
