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
import org.wecancodeit.beveragereviews.repositories.CategoryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class JpaMappingsTest {

	@Resource
	private CategoryRepository categoryRepo;
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

}
