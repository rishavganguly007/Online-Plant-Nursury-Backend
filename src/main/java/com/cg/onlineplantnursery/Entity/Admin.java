package com.cg.onlineplantnursery.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Admin {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int adminId;
	
	@NotBlank(message = "Username is mandatory")
	@Size(min = 3, message = "Username must contain 3 characters.")
	private String adminUsername;
	
	@NotBlank(message = "Password is mandatory")
	//@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$", message="Password must contain a lowercase character, "
      //                                  + "a uppercase character and a digit, minimum length must be 6 characters")
	private String adminpassword;
	
}