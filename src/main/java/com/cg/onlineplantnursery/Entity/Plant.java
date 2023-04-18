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
public class Plant {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer plantId;
	
	@Column(name = "height")
	private float plantHeight;
	
	@Column(name = "spread")
	private String plantSpread;
	
	@Column(name = "plantname")
	@NotBlank(message = "Plant name is mandatory")
	private String commonName;
	
	@Column(name = "btime")
	private String bloomTime;
	
	@Column(name = "use")
	private String medicinalOrCulinaryUse;
	
	@Column(name = "difficulty")
	private String difficultyLevel;
	
	private String temparature;
	
	@Column(name = "planttype")
	@NotBlank(message = "Plant type is mandatory")
	private String typeOfPlant;
	
	@Column(name = "description")
	private String plantDescription;

	@Column(name = "plantstock")
	@Min(value = 50)
	private int plantsStock;
	
	@Column(name = "plantcost")
	private double plantCost;
	
	@Column(name = "ImageUrl")
	private String plantImageUrl;
	
}
