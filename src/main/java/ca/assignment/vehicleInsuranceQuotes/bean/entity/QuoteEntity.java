package ca.assignment.vehicleInsuranceQuotes.bean.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import ca.assignment.vehicleInsuranceQuotes.bean.common.QuoteStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "quote")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"status", "premium"})
@ToString
public class QuoteEntity implements Serializable, Comparable<QuoteEntity> {

	private static final long serialVersionUID = 2821141769696496525L;


	@Id
	private String id;
	
	@NotNull
	@Field("Status")
	private QuoteStatus status;

	@Field("Premium")
	private BigDecimal premium;
	
	@NotEmpty
	@Field("Quote_Request_Id")
    private String requestId;

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compareTo(QuoteEntity other) {
		return new CompareToBuilder()
	            .append(this.id, other.id)
	            .append(this.status, other.status)
	            .append(this.premium, other.premium)
	            .append(this.requestId, other.requestId)
	            .toComparison();
	}
	
}
