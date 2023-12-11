package ca.assignment.vehicleInsuranceQuotes.bean.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.CompareToBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CarDTO implements Serializable, Comparable<CarDTO> {

	private static final long serialVersionUID = -162446326766561239L;


	private String id;

	@NotEmpty
	@JsonProperty("Category")
	private String category;

	@NotEmpty
	@JsonProperty("Make")
	private String make;

	@NotEmpty
	@JsonProperty("Model")
	private String model;

	@NotNull
	@JsonProperty("Year")
	private Short year;

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compareTo(CarDTO other) {
		return new CompareToBuilder()
	            .append(this.id, other.id)
	            .append(this.category, other.category)
	            .append(this.make, other.make)
	            .append(this.model, other.model)
	            .append(this.year, other.year)
	            .toComparison();
	}
	
}
