package ca.assignment.vehicleInsuranceQuotes.bean.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang3.builder.CompareToBuilder;

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

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"status", "premium"})
@ToString
public class QuoteDTO implements Serializable, Comparable<QuoteDTO> {

	private static final long serialVersionUID = -7184106104900507179L;


	private String id;
	
	@NotNull
	private QuoteStatus status;

	private BigDecimal premium;
	
    @NotEmpty
    private String requestId;

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compareTo(QuoteDTO other) {
		return new CompareToBuilder()
	            .append(this.id, other.id)
	            .append(this.status, other.status)
	            .append(this.premium, other.premium)
	            .append(this.requestId, other.requestId)
	            .toComparison();
	}
	
}
