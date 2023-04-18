package com.cg.onlineplantnursery.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Address {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(updatable = false)
	private Integer addressId;
	
	@NotBlank(message = "House no is mandatory")
	private String houseNo;
	
	private String colony;
	
	@NotBlank(message = "City is mandatory")
	private String city;
	
	@NotBlank(message = "State is mandatory")
	private String state;
	
	@NotNull(message = "Pincode is mandatory")
	private Integer pincode;   
	
}
