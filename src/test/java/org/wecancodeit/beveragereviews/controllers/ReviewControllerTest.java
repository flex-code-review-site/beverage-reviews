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
import org.wecancodeit.beveragereviews.models.Category;
import org.wecancodeit.beveragereviews.models.Review;
import org.wecancodeit.beveragereviews.models.Tag;
import org.wecancodeit.beveragereviews.repositories.CategoryRepository;
import org.wecancodeit.beveragereviews.repositories.ReviewRepository;
import org.wecancodeit.beveragereviews.repositories.TagRepository;

public class ReviewControllerTest {
	
	@InjectMocks
	private ReviewController underTest;
	
	@Mock
	private Model model;
	
	@Mock
	private ReviewRepository reviewRepo;
	
	@Mock
	private TagRepository tagRepo;
	
	@Mock
	private CategoryRepository categoryRepo;
	
	@Mock
	private Review review1;
	
	@Mock
	private Review review2;
	
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void shouldAddAllReviewsToModel() {
		Collection<Review> allReviews = Arrays.asList(review1, review2);
		when(reviewRepo.findAll()).thenReturn(allReviews);

		underTest.findAllReviews(model);
		verify(model).addAttribute("reviews", allReviews);

	}
	@Test 
	public void shouldAddOneReviewToModel() throws ReviewNotFoundException {
		Long arbitraryId = 1L;
		when(reviewRepo.findById(arbitraryId)).thenReturn(Optional.of(review1));
		
		underTest.findOneReview(arbitraryId, model);
		verify(model).addAttribute("review",review1);
	}
	@Test
	public void shouldAddAdditionalReviewsToModel() {
		String name =  "Coca-Cola";
		String description = "As bubbly as my soul!";
		String categoryName = "Caffeinated";
		Category newCategory = categoryRepo.findByName(categoryName);
		String tagName = "Soft Drink";
		Tag newTag = tagRepo.findByName(tagName);
	    underTest.addReview(name,description,categoryName,tagName);
	    Review cocaCola = new Review(name,description,newCategory,newTag);
	    when(reviewRepo.save(cocaCola)).thenReturn(cocaCola);
	}
}
