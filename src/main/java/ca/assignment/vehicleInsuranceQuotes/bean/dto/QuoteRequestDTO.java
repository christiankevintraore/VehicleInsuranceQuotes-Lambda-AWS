package ca.assignment.vehicleInsuranceQuotes.bean.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang3.builder.CompareToBuilder;

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
public class QuoteRequestDTO implements Serializable, Comparable<QuoteRequestDTO> {

	private static final long serialVersionUID = -2361322474666332320L;


	private String id;
	
	@NotNull
	private Short age;
	
	@NotNull
	private Short drivingExperience;
	
	@NotNull
	private Short driverRecord;
	
	@NotNull
	private Short claims;
	
	@NotNull
	private BigDecimal carValue;
	
	@NotNull
	private Integer annualMileage;
	
	@NotNull
	private Short insuranceHistory;

	@NotEmpty
	private String carId;

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compareTo(QuoteRequestDTO other) {
		return new CompareToBuilder()
	            .append(this.id, other.id)
	            .append(this.age, other.age)
	            .append(this.drivingExperience, other.drivingExperience)
	            .append(this.driverRecord, other.driverRecord)
	            .append(this.claims, other.claims)
	            .append(this.carValue, other.carValue)
	            .append(this.annualMileage, other.annualMileage)
	            .append(this.insuranceHistory, other.insuranceHistory)
	            .append(this.carId, other.carId)
	            .toComparison();
	}
	
}
