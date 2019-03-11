package org.wecancodeit.beveragereviews.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wecancodeit.beveragereviews.models.Review;

public interface ReviewRepository extends CrudRepository<Review, Long> {

	Review findByName(String name);

}


