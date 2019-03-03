package org.wecancodeit.beveragereviews.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.wecancodeit.beveragereviews.models.Category;
import org.wecancodeit.beveragereviews.repositories.CategoryRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoryController.class)
public class CategoryControllerMvcTest {
	
	@Resource
	private MockMvc mvc;
	
	@MockBean
	private CategoryRepository categoryRepo;
	
	@Mock
	private Category category1;
	
	@Mock
	private Category category2;
		
	@Test
	public void shouldBeOkForSingleCategory() throws Exception {
		long id = 1;
		when(categoryRepo.findById(id)).thenReturn(Optional.of(category1));
		mvc.perform(get("/category?id=1")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldRouteToSingleCategoryView() throws Exception {
		long categoryId =1;
		when(categoryRepo.findById(categoryId)).thenReturn(Optional.of(category1));
		mvc.perform(get("/category?id=1")).andExpect(view().name(is("category")));
	}
	
	@Test
	public void shouldRouteToAllCategoriesView() throws Exception{
		List<Category> categories= Arrays.asList(category1,category2);
		when(categoryRepo.findAll()).thenReturn(categories);
		mvc.perform(get("/categories")).andExpect(view().name(is("categories")));
	}
	
	
}
