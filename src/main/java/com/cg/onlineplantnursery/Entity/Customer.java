package com.cg.onlineplantnursery.Entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@Table(name = "customer")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(updatable = false)
	private Integer customerId;
	
	@NotBlank(message = "Name is mandatory")
	private String customerName;
	
	@Email(message = "Email should be valid")
	private String customerEmail;	
	
	@Column(unique = true, updatable = false)
	@NotBlank(message = "Username is mandatory")
	@Size(min = 3, message = "Username must contain 3 characters.")
	private String username; 
	
	@NotBlank(message = "Password is mandatory")
//	@Pattern(regexp="\"^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$\"", message="Password must contain a lowercase character, "
//			                                     + "a uppercase character and a digit, minimum length must be 6 characters")  
	private String password;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ca_fk", referencedColumnName = "addressId" )
	private Address address;	
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Orders> orders;
    
}