package ca.assignment.vehicleInsuranceQuotes.bean.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor(access=AccessLevel.PRIVATE)
@ToString
public enum QuoteStatus {

	SUCCESS("Success", "SUCCESS"),
	UNKNOWN_PREMIUM("Unknown Premium", "UNKNOWN_PREMIUM");


    @Getter
    private final String label;

    @Getter
    private final String code;

}
