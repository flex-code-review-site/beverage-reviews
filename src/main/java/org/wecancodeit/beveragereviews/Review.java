package org.wecancodeit.beveragereviews;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.wecancodeit.beveragereviews.models.Category;

@Entity
public class Review {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String description;
	@ManyToOne
	private Category category;
	
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

	protected Review() {//Whyyy????
		
	}
	
	public Review(String name, String description, Category category) {
		this.name = name;
		this.description = description;
		this.category = category;
	}

}
