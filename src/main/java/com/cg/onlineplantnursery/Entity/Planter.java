package com.cg.onlineplantnursery.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

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
@ToString
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Planter {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer planterId;
	
	@Column(name = "height")
	private float planterheight;
	
	@Column(name = "capacity")
	@Min(value = 1)
	private int planterCapacity;
	
	@Column(name = "drinage")
	@Min(value = 2)
	private int drinageHoles;
	
	@Column(name = "pcolor")
	@NotBlank(message = "Planter color must be inserted")
	private String planterColor;
	
	@Column(name = "pshape")
	@NotBlank(message = "Planter shape must be inserted")
	private String planterShape;

	@Column(name = "stock")
	@Min(value = 50)
	private int planterStock;
	
	@Column(name = "cost")
	private double planterCost;

	@Column(name = "ImageUrl")
	private String planterImageUrl;
}
