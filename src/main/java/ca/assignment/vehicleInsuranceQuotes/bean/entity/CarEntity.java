package ca.assignment.vehicleInsuranceQuotes.bean.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "car_model")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CarEntity implements Serializable, Comparable<CarEntity> {

	private static final long serialVersionUID = -8905346320630173026L;


	@Id
	private String id;
	
	@NotEmpty
	@Field("Category")
	private String category;

	@NotEmpty
    @Indexed
	@Field("Make")
	private String make;

	@NotEmpty
    @Indexed
	@Field("Model")
	private String model;

	@NotNull
	@Field("Year")
	private Short year;

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compareTo(CarEntity other) {
		return new CompareToBuilder()
	            .append(this.id, other.id)
	            .append(this.category, other.category)
	            .append(this.make, other.make)
	            .append(this.model, other.model)
	            .append(this.year, other.year)
	            .toComparison();
	}
	
}
