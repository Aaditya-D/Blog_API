package com.bootproject.blog_api.payloads;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	
	private String categoryId;
	
	@NotBlank
	@Size(min=4, message = "Min category size is 4")
	private String categoryTitle;
	
	@NotBlank
	@Size(min=10, message = "Min category size is 10")
	private String categoryDescription;
}
