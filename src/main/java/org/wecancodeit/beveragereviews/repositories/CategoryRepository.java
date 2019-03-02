package org.wecancodeit.beveragereviews.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wecancodeit.beveragereviews.models.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}
