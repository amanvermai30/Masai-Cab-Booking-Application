package com.masai.cabbooking.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Driver {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer driverId;

	@NotNull(message = "first name  cannot set as null")
	@NotEmpty(message = "first name cannot set as empty")
	@NotBlank(message = "first name cannot set as blank")
	@Pattern(regexp = "[A-Za-z]")
	private String firstName;

	@NotNull(message = "last name  cannot set as null")
	@NotEmpty(message = "last name cannot set as empty")
	@NotBlank(message = "last name cannot set as blank")
	@Pattern(regexp = "[A-Za-z]")
	private String lastName;

	@NotNull(message = "Email  cannot set as null")
	@NotEmpty(message = "Email cannot set as empty")
	@NotBlank(message = "Email cannot set as blank")
	@Email(message = "email format is incorrect")
	@Column(unique = true)
	private String email;

	@NotNull(message = "mobileNumber cannot set as null")
	@Pattern(regexp = "^[789]\\d{9}$")
	@Column(unique = true)
	private String mobileNumber;

	@NotNull(message = "password cannot set as null")
	@NotEmpty(message = "password cannot set as empty")
	@NotBlank(message = "password cannot set as blank")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\\\S+$).{6, 12}$")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	@Size(min = 2, max = 2, message = "array size must be 2")
	private Integer[] currentPosition;

	@OneToOne(cascade = CascadeType.ALL)
	private Cab cab;

}
