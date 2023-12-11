package ca.assignment.vehicleInsuranceQuotes.dataloading.dto;

import java.io.Serializable;

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
public class CarDataDTO implements Serializable {

	private static final long serialVersionUID = -5222525332355859103L;
	
	
	private CarModelListsDTO data;
}
