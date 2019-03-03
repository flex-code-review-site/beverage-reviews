package org.wecancodeit.beveragereviews.controllers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.wecancodeit.beveragereviews.controllers.CategoryController;
import org.wecancodeit.beveragereviews.models.Category;
import org.wecancodeit.beveragereviews.repositories.CategoryRepository;

public class CategoryControllerTest {

	@InjectMocks
	private CategoryController underTest;

	@Mock
	private Category catone;

	@Mock
	private Category cattwo;

	@Mock
	private Model model;

	@Mock
	private CategoryRepository categoryRepo;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldAddAllCategoriesToModel() {
		Collection<Category> allCategories = Arrays.asList(catone, cattwo);
		when(categoryRepo.findAll()).thenReturn(allCategories);

		underTest.findAllCategories(model);
		verify(model).addAttribute("categories", allCategories);
		
	}
	
	@Test
	public void shouldAddOneCategoryToModel() {
		when(categoryRepo.findById(1L)).thenReturn(Optional.of(catone));
		
		underTest.findOneCategory(1L, model);
		verify(model).addAttribute("category", catone);
	}
	
	
}
