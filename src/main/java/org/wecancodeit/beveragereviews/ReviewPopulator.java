package org.wecancodeit.beveragereviews;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.wecancodeit.beveragereviews.models.Category;
import org.wecancodeit.beveragereviews.models.Comment;
import org.wecancodeit.beveragereviews.models.Review;
import org.wecancodeit.beveragereviews.models.Tag;
import org.wecancodeit.beveragereviews.repositories.CategoryRepository;
import org.wecancodeit.beveragereviews.repositories.CommentRepository;
import org.wecancodeit.beveragereviews.repositories.ReviewRepository;
import org.wecancodeit.beveragereviews.repositories.TagRepository;

@Component
public class ReviewPopulator implements CommandLineRunner {

	@Resource
	private CategoryRepository categoryRepo;

	@Resource
	private ReviewRepository reviewRepo;

	@Resource
	private TagRepository tagRepo;

	@Resource
	private CommentRepository commentRepo;

	@Override
	public void run(String... args) throws Exception {
		Category alcoholic = categoryRepo.save(new Category("Alcoholic"));
		Category nonAlcoholic = categoryRepo.save(new Category("Non-Alcoholic"));

		Tag hot = tagRepo.save(new Tag("Hot"));
		Tag cold = tagRepo.save(new Tag("Cold"));
		Tag caffeinated = tagRepo.save(new Tag("Caffeinated"));
		Tag nonCaffeinated = tagRepo.save(new Tag("Non-Caffeinated"));

		Review coffee = new Review("Coffee", "Black as my soul", nonAlcoholic, "./images/coffee.jpg", hot, caffeinated);
		coffee = reviewRepo.save(coffee);
		Review tea = new Review("Tea", "Sweet as my soul", nonAlcoholic, "./images/tea.jpg", hot, caffeinated);
		tea = reviewRepo.save(tea);
		Review moonshine = new Review("Moonshine", "Red neck as my soul", alcoholic, "./images/moonshine.jpg", cold,
				nonCaffeinated);
		moonshine = reviewRepo.save(moonshine);
		Review wine = new Review("Wine", "Sassy as my soul", alcoholic, "./images/wine.jpg", cold, nonCaffeinated);
		wine = reviewRepo.save(wine);

		Comment comment1 = new Comment(
				"tasted burnt",
				coffee);
		commentRepo.save(comment1);
		Comment comment2 = new Comment(
				"nice and hot",
				coffee);
		commentRepo.save(comment2);
		Comment comment3 = new Comment(
				"less caffeine",
				tea);
		commentRepo.save(comment3);

	}

}
