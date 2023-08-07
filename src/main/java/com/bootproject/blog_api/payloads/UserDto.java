package com.bootproject.blog_api.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	
	private int id;
	
	@NotEmpty
	@Size(min = 4 , message = "Username should be min of 4 charachters!")
	private String name;
	
	@Email(message = "Invalid Email Address!")
	private String email;
	
	@NotEmpty
	@Size(min = 3, max = 10, message = "Password must be in range of 3 to 10 character")
	private String password;
	
	@NotNull
	private String about;
	
}
