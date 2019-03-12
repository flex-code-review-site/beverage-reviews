package org.wecancodeit.beveragereviews.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wecancodeit.beveragereviews.models.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {

	Comment findByContent(String content);



}
