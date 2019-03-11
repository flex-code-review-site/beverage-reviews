package org.wecancodeit.beveragereviews.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Actor Not Found")
public class TagNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2285405401945477506L;

//	@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Actor Not Found")
//	public class ActorNotFoundException extends Exception {
//	    // ...
//	}
	
}
