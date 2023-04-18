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
public class Seed {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer seedId;
	
	@Column(name = "cname")
	@NotBlank(message = "Seed common name must be inserted")
	private String commonName;
	
	@Column(name = "bloomtime")
	@NotBlank(message = "Bloom time must be specified")
	private String bloomTime;
	
	private String watering;
	
	@Column(name = "difficulty")
	private String difficultyLevel;
	
	private String temparature;
	
	@Column(name = "type")
	@NotBlank(message = "Seed type must be inserted")
	private String typeOfSeeds;
	
	@Column(name = "description")
	private String seedsDescription;
	
	@Column(name = "stock")
	@Min(value = 50)
	private int seedsStock;
	
	@Column(name = "scost")
	private double seedsCost;
	
	@Column(name = "quantity")
	@Min(value = 10)
	private int seedsPerPacket;
	

	@Column(name = "ImageUrl")
	private String seedImageUrl;
}
