package ca.assignment.vehicleInsuranceQuotes.dataloading.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class CarModelListsDTO implements Serializable {

	private static final long serialVersionUID = 921750302692006859L;
	
	
	@JsonProperty("car_Model_Lists")
	private CarResultsDTO carModelLists;
}
