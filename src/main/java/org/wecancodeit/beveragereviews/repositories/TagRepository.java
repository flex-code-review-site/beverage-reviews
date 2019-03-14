package org.wecancodeit.beveragereviews.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wecancodeit.beveragereviews.models.Tag;

public interface TagRepository extends CrudRepository<Tag, Long> {

	Tag findByName(String tag);

	Tag findByNameIgnoreCase(String tagName);

}
