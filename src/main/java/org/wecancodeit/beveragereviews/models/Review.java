package org.wecancodeit.beveragereviews.models;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Review {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String description;
	@ManyToOne
	private Category category;
	@ManyToMany
	private Collection<Tag> tags;
	private String imageAddress;
	@OneToMany(mappedBy = "review")
	private Collection<Comment> comments;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Category getCategory() {
		return category;
	}

	public Collection<Tag> getTags() {
		return tags;
	}

	public String getImageAddress() {

		return imageAddress;
	}

	public Collection<Comment> getComments() {
		return comments;
	}

	protected Review() {// Whyyy???? //default constructor required for entities

	}

	public Review(String name, String description, Category category, Tag... tags) {
		this.name = name;
		this.description = description;
		this.category = category;
		this.tags = new HashSet<>(Arrays.asList(tags));
	}

	public Review(String name, String description, Category category, String imageAddress, Tag... tags) {
		this.name = name;
		this.description = description;
		this.category = category;
		this.imageAddress = imageAddress;
		this.tags = new HashSet<>(Arrays.asList(tags));
	}

	public void addTag(Tag tag) {
		Boolean tagExists = false;
		for (Tag existingTag : tags) {
			if (existingTag.getName().equalsIgnoreCase(tag.getName())) {
				tagExists = true;
			}
		}
		if (tagExists == false) {
			tags.add(tag);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Review removeTag(Tag tagToRemove) {
		tags.remove(tagToRemove);
		return this;
	}

}
