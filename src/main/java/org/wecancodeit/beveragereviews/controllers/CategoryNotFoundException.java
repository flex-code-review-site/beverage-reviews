
package org.wecancodeit.beveragereviews.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CategoryNotFoundException extends Exception {

	private static final long serialVersionUID = -5672898804549428389L;
    
	
}


