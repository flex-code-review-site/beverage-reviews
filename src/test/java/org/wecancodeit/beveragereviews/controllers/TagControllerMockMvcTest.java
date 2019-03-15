package org.wecancodeit.beveragereviews.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.wecancodeit.beveragereviews.models.Tag;
import org.wecancodeit.beveragereviews.repositories.ReviewRepository;
import org.wecancodeit.beveragereviews.repositories.TagRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(TagController.class)
public class TagControllerMockMvcTest {

	@Resource
	private MockMvc mvc;

	@Mock
	private Tag hot;

	@Mock
	private Tag cold;

	@MockBean
	private TagRepository tagRepo;

	@MockBean
	ReviewRepository reviewRepo;

	@Test
	public void shouldBeOkayForOneTag() throws Exception {

		long tagId = 1;
		when(tagRepo.findById(tagId)).thenReturn(Optional.of(hot));

		mvc.perform(get("/tag?id=1")).andExpect(status().isOk());
	}

	@Test
	public void shouldBeOkayForAllTags() throws Exception {
		mvc.perform(get("/tags")).andExpect(status().isOk());
	}

	@Test
	public void shouldRouteToOneTagView() throws Exception {
		long tagId = 1;
		when(tagRepo.findById(tagId)).thenReturn(Optional.of(hot));

		mvc.perform(get("/tag?id=1")).andExpect(view().name(is("tag")));

	}

	@Test
	public void shouldRouteToAllTagsView() throws Exception {
		mvc.perform(get("/tags")).andExpect(view().name(is("tags")));

	}

	@Test
	public void shouldPutOneTagIntoModel() throws Exception {
		long tagId = 1;
		when(tagRepo.findById(tagId)).thenReturn(Optional.of(hot));

		mvc.perform(get("/tag?id=1")).andExpect(model().attribute("tag", hot));
	}

	@Test
	public void shouldPutAllTagsIntoModel() throws Exception {
		Collection<Tag> allTags = Arrays.asList(hot, cold);
		when(tagRepo.findAll()).thenReturn(allTags);

		mvc.perform(get("/tags")).andExpect(model().attribute("tags", allTags));

	}

	@Test
	public void shpouldBeNotFoundForOneTag() throws Exception {
		mvc.perform(get("/tag?id=1")).andExpect(status().isNotFound());
	}

	@Test
	public void shouldBeFoundWhenTagAdded() throws Exception {
		mvc.perform(get("/add-tag")).andExpect(status().isFound());
	}
	
	@Test
	public void shouldRedirectToReviewWhenTagAdded() throws Exception {
		mvc.perform(get("/add-tag")).andExpect(redirectedUrlPattern("/review?id=*" ));
	}
}
