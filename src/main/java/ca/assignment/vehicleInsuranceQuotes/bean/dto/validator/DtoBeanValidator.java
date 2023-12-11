package ca.assignment.vehicleInsuranceQuotes.bean.dto.validator;

public interface DtoBeanValidator {

	/**
	 * Uses Hibernate validator to validate a DTO POJO.
	 * 
	 * @param dto {@link T} : the POJO to validate.
	 * 
	 * @return {@link String} : the validation error message, null if valid.
	 * 
	 */
    public <T> String validate(final T dto);
	
}
