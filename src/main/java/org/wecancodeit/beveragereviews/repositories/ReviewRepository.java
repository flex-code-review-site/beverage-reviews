package org.wecancodeit.beveragereviews.repositories;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.wecancodeit.beveragereviews.models.Review;

public interface ReviewRepository extends CrudRepository<Review, Long> {

	Review findByName(String name);

	Collection<Review> findAllByOrderByName();

}


