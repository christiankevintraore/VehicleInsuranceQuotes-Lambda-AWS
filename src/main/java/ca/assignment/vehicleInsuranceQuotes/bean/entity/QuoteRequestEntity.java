package ca.assignment.vehicleInsuranceQuotes.bean.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.springframework.data.annotation.Id;
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

@Document(collection = "quote_request")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class QuoteRequestEntity implements Serializable, Comparable<QuoteRequestEntity> {

	private static final long serialVersionUID = -2412861607471592401L;


	@Id
	private String id;
	
	@NotNull
	@Field("Age")
	private Short age;
	
	@NotNull
	@Field("Driving_Experience")
	private Short drivingExperience;
	
	@NotNull
	@Field("Driver_Record")
	private Short driverRecord;
	
	@NotNull
	@Field("Claims")
	private Short claims;
	
	@NotNull
	@Field("Car_Value")
	private BigDecimal carValue;
	
	@NotNull
	@Field("Annual_Mileage")
	private Integer annualMileage;
	
	@NotNull
	@Field("Insurance_History")
	private Short insuranceHistory;
	
    @NotEmpty
	@Field("Car_Model_Id")
    private String carId;

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compareTo(QuoteRequestEntity other) {
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
