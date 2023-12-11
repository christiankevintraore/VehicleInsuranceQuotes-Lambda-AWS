package ca.assignment.vehicleInsuranceQuotes.dataloading.dto;

import java.io.Serializable;
import java.util.List;

import ca.assignment.vehicleInsuranceQuotes.bean.dto.CarDTO;
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
public class CarResultsDTO implements Serializable {

	private static final long serialVersionUID = 921750302692006859L;
	
	
	private List<CarDTO> results;
}
