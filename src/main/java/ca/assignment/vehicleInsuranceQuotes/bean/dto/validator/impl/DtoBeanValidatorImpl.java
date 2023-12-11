package ca.assignment.vehicleInsuranceQuotes.bean.dto.validator.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ca.assignment.vehicleInsuranceQuotes.bean.dto.validator.DtoBeanValidator;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DtoBeanValidatorImpl implements DtoBeanValidator {

	@Autowired
	protected Validator validator;


	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> String validate(T dto) {
		var constraintViolations = validator.validate(dto);
		if (!constraintViolations.isEmpty()) {
			var errorMessageBuilder = new StringBuilder()
					.append("Validation failed for bean : {")
					.append(dto).append("}\nConstraint violations :\n");
			constraintViolations.stream().forEach(violation -> {
				errorMessageBuilder.append("- ")
				.append(violation.getPropertyPath()).append(" {")
				.append(violation.getInvalidValue()).append("} : ")
				.append(violation.getMessage()).append("\n");
			});
			String errorMessage = errorMessageBuilder.toString();

			log.error(errorMessage);
			
			return errorMessage;
		}
		
		return null;
	}
	
	
}
