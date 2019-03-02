package org.wecancodeit.beveragereviews;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wecancodeit.beveragereviews.models.Category;
import org.wecancodeit.beveragereviews.models.Review;
import org.wecancodeit.beveragereviews.models.Tag;
import org.wecancodeit.beveragereviews.repositories.CategoryRepository;
import org.wecancodeit.beveragereviews.repositories.ReviewRepository;
import org.wecancodeit.beveragereviews.repositories.TagRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class JpaMappingsTest {

	@Resource
	private CategoryRepository categoryRepo;
	
	@Resource
	private TagRepository tagRepo;
	
	@Resource
	private ReviewRepository reviewRepo;
	
	@Resource
	private TestEntityManager entityManager;

	@Test
	public void shouldSaveAndLoadACategory() {
		Category alcoholic = new Category("Alcoholic");
		categoryRepo.save(alcoholic);
		Long alcoholicId = alcoholic.getId();

		entityManager.flush();// force jpa to hit the db when we try to find it
		entityManager.clear();

		Optional<Category> categoryToFind = categoryRepo.findById(alcoholicId);
		alcoholic = categoryToFind.get();
		assertThat(alcoholic.getName(), is("Alcoholic"));
	}

	@Test
	public void shouldSaveAndLoadATag() {
		Tag hot = new Tag("Hot");
		tagRepo.save(hot);
		Long hotId = hot.getId();
		
		entityManager.flush();// force jpa to hit the db when we try to find it
		entityManager.clear();
		
		Optional<Tag> tagToFind = tagRepo.findById(hotId);
		hot = tagToFind.get();
		assertThat(hot.getName(), is("Hot"));
	}
	
	@Test
	public void shouldHaveNameDescriptionAndCategoryInReview() {
		Category nonAlcoholic = new Category("Non-Alcoholic");
		categoryRepo.save(nonAlcoholic);
		Review coffee = new Review("Coffee", "Black as my soul", nonAlcoholic);
		reviewRepo.save(coffee);
		Long coffeeId = coffee.getId();

		entityManager.flush();// force jpa to hit the db when we try to find it
		entityManager.clear();
		
		Optional<Review> reviewToFind = reviewRepo.findById(coffeeId);
		coffee = reviewToFind.get();
		assertThat(coffee.getName(), is("Coffee"));
	}
	
	
}
