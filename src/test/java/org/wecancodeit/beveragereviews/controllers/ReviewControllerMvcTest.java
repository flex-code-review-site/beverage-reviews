package org.wecancodeit.beveragereviews.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
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
import org.wecancodeit.beveragereviews.models.Review;
import org.wecancodeit.beveragereviews.repositories.CategoryRepository;
import org.wecancodeit.beveragereviews.repositories.ReviewRepository;
import org.wecancodeit.beveragereviews.repositories.TagRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)
public class ReviewControllerMvcTest {


	@Resource
	private MockMvc mvc;
	
	
	@Mock 
	private Review review1;
	
	@Mock 
	private Review review2;
	
	@Mock 
	private Category category1;
	
	@MockBean
	private ReviewRepository reviewRepo;
	
	@MockBean
	private CategoryRepository categoryRepo;
	
	@MockBean
	private TagRepository tagRepo;

	@Test
	public void shouldBeOkForSingleReview() throws Exception {
		long id = 1;
		when(review1.getCategory()).thenReturn(category1);
		when(reviewRepo.findById(id)).thenReturn(Optional.of(review1));
		
		mvc.perform(get("/review?id=1")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldNotBeOkayForSingleReview() throws Exception {
		mvc.perform(get("/review?id=1")).andExpect(status().isNotFound());
	}
	
	@Test
	public void shouldRouteToSingleReviewView() throws Exception {
		long id = 1;
		when(review1.getCategory()).thenReturn(category1);
		when(reviewRepo.findById(id)).thenReturn(Optional.of(review1));
		
		mvc.perform(get("/review?id=1")).andExpect(view().name("review"));
	}
	@Test
	public void shouldPutASingleReviewIntoModel() throws Exception {
		long id = 1;
		when(review1.getCategory()).thenReturn(category1);
		when(reviewRepo.findById(id)).thenReturn(Optional.of(review1));
		
		mvc.perform(get("/review?id=1")).andExpect(model().attribute("reviews", review1));
	}
	
	@Test
	public void shouldBeOkForAllReviews() throws Exception{
		mvc.perform(get("/reviews")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldRouteToAllReviewsView() throws Exception {
		List<Review> reviews = Arrays.asList(review1,review2);
		when(reviewRepo.findAll()).thenReturn(reviews);
		mvc.perform(get("/reviews")).andExpect(view().name(is("reviews")));
	}
	@Test
	public void shouldAddAllCoursesIntoModel() throws Exception {
		List<Review> reviews = Arrays.asList(review1,review2);
		when(reviewRepo.findAll()).thenReturn(reviews);
		mvc.perform(get("/reviews")).andExpect(model().attribute("reviews", reviews));
	}
	
	
	
}
