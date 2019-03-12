package org.wecancodeit.beveragereviews;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wecancodeit.beveragereviews.models.Category;
import org.wecancodeit.beveragereviews.models.Comment;
import org.wecancodeit.beveragereviews.models.Review;
import org.wecancodeit.beveragereviews.models.Tag;
import org.wecancodeit.beveragereviews.repositories.CategoryRepository;
import org.wecancodeit.beveragereviews.repositories.ReviewRepository;
import org.wecancodeit.beveragereviews.repositories.TagRepository;
import org.wecancodeit.beveragereviews.repositories.CommentRepository;

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
	
	@Resource
	private CommentRepository commentRepo;

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

	@Test
	public void shouldFindNonAlcoholicReviews() {
		Category nonAlcoholic = new Category("Non-Alcoholic");
		categoryRepo.save(nonAlcoholic);
		Review coffee = new Review("Coffee", "Black as my soul", nonAlcoholic);
		reviewRepo.save(coffee);

		Review tea = new Review("Tea", "Sweet as my soul ", nonAlcoholic);
		reviewRepo.save(tea);

		Category alcoholic = new Category("Alcoholic");
		categoryRepo.save(alcoholic);

		Review moonshine = new Review("Moonshine", "Redneck as my soul ", alcoholic);
		reviewRepo.save(moonshine);

		entityManager.flush();// force jpa to hit the db when we try to find it
		entityManager.clear();

		Long nonAlcoholicId = nonAlcoholic.getId();
		Optional<Category> categoryToFind = categoryRepo.findById(nonAlcoholicId);
		nonAlcoholic = categoryToFind.get();

		Collection<Review> foundReviews = nonAlcoholic.getReviews();
		assertThat(foundReviews, containsInAnyOrder(coffee, tea));
	}

	@Test
	public void shouldBeAbleToFindAllReviewsByTag() {
		Category nonAlcoholic = new Category("Non-Alcoholic");
		categoryRepo.save(nonAlcoholic);
		Tag hot = new Tag("hot");
		tagRepo.save(hot);
		Tag cold = new Tag("cold");
		tagRepo.save(cold);

		Review coffee = new Review("Coffee", "Black as my soul", nonAlcoholic, hot);
		reviewRepo.save(coffee);

		Review tea = new Review("Tea", "Sweet as my soul ", nonAlcoholic, hot, cold);
		reviewRepo.save(tea);

		entityManager.flush();// force jpa to hit the db when we try to find it
		entityManager.clear();

		Long hotId = hot.getId();
		Optional<Tag> tagToFind = tagRepo.findById(hotId);
		hot = tagToFind.get();

		Collection<Review> foundReviews = hot.getReviews();
		assertThat(foundReviews, containsInAnyOrder(coffee, tea));

	}

	@Test
	public void shouldBeAbleToFindAllTagsByReview() {
		Category nonAlcoholic = new Category("Non-Alcoholic");
		categoryRepo.save(nonAlcoholic);
		Tag hot = new Tag("hot");
		tagRepo.save(hot);
		Tag cold = new Tag("cold");
		tagRepo.save(cold);

		Review tea = new Review("Tea", "Sweet as my soul ", nonAlcoholic, hot, cold);
		reviewRepo.save(tea);

		entityManager.flush();// force jpa to hit the db when we try to find it
		entityManager.clear();

		Long teaId = tea.getId();
		Optional<Review> reviewToFind = reviewRepo.findById(teaId);
		tea = reviewToFind.get();

		Collection<Tag> foundTags = tea.getTags();
		assertThat(foundTags, containsInAnyOrder(hot, cold));
	}
	
	@Test
	public void shouldBeAbleToSaveAndFindImageAddress() {
		Category nonAlcoholic = new Category("Non-Alcoholic");
		categoryRepo.save(nonAlcoholic);
		Review coffee = new Review("Coffee", "Black as my soul", nonAlcoholic, "Image Address");
		reviewRepo.save(coffee);
		Long coffeeId = coffee.getId();

		entityManager.flush();// force jpa to hit the db when we try to find it
		entityManager.clear();

		String image = reviewRepo.findById(coffeeId).get().getImageAddress();
		assertThat(image, is("Image Address"));
	}
	
	@Test
	public void shouldSaveAndLoadAComment() {
		Category nonAlcoholic = new Category("Non-Alcoholic");
		categoryRepo.save(nonAlcoholic);
		Review coffee = new Review("Coffee", "Black as my soul", nonAlcoholic, "Image Address");
		reviewRepo.save(coffee);
		Comment comment1 = new Comment("tasted burnt", coffee);
		commentRepo.save(comment1);
		Long comment1Id = comment1.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Comment> commentToFind = commentRepo.findById(comment1Id);
		comment1 = commentToFind.get();
		assertThat(comment1.getContent(), is("tasted burnt"));
	}
	
	@Test
	public void shouldReturnAllCommentsForAReview() {
		Category nonAlcoholic = new Category("Non-Alcoholic");
		categoryRepo.save(nonAlcoholic);
		Review coffee = new Review("Coffee", "Black as my soul", nonAlcoholic, "Image Address");
		reviewRepo.save(coffee);
		Review tea = new Review("Tea", "Sweet as my soul ", nonAlcoholic, "image address");
		reviewRepo.save(tea);
		Comment comment1 = new Comment("tasted burnt", coffee);
		commentRepo.save(comment1);
		Comment comment2 = new Comment("nice and hot", coffee);
		commentRepo.save(comment2);
		Comment comment3 = new Comment("less caffine", tea);
		commentRepo.save(comment3);
		Long coffeeId = coffee.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		coffee = reviewRepo.findById(coffeeId).get();
		Collection<Comment> comments = coffee.getComments();
		assertThat(comments, containsInAnyOrder(comment1, comment2));
		assertThat(comments, not(containsInAnyOrder(comment3)) );
	}
	
	@Test 
	public void shouldSortReviewsByName(){
		Category nonAlcoholic = new Category("Non-Alcoholic");
		categoryRepo.save(nonAlcoholic);
		Review coffee = new Review("Coffee", "Black as my soul", nonAlcoholic, "Image Address");
		reviewRepo.save(coffee);
		Review tea = new Review("Tea", "Sweet as my soul ", nonAlcoholic, "image address");
		reviewRepo.save(tea);
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Review> sortedReviews = reviewRepo.findAllByOrderByName();
		assertThat(sortedReviews,contains(coffee,tea));
		
	
	}
}
