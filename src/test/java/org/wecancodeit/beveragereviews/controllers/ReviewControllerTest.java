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
import org.wecancodeit.beveragereviews.models.Review;
import org.wecancodeit.beveragereviews.repositories.ReviewRepository;

public class ReviewControllerTest {
	
	@InjectMocks
	private ReviewController underTest;
	
	@Mock
	private Model model;
	
	@Mock
	private ReviewRepository reviewRepo;
	
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
	public void shouldAddOneReviewToModel() {
		Long arbitraryId = 1L;
		when(reviewRepo.findById(arbitraryId)).thenReturn(Optional.of(review1));
		
		underTest.findOneReview(arbitraryId, model);
		verify(model).addAttribute("review",review1);
	}
}
