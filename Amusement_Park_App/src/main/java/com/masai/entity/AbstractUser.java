package com.masai.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class AbstractUser {

	@NotBlank(message = "username can't be blank")
	private String username;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	@NotBlank(message = "Address should be there")
	private String Address;
	private String mobileNumber;
	@Column(unique=true)
	private String email;

}
