package ca.assignment.vehicleInsuranceQuotes.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ca.assignment.vehicleInsuranceQuotes.bean.dto.QuoteRequestDTO;
import ca.assignment.vehicleInsuranceQuotes.bean.entity.QuoteRequestEntity;
import ca.assignment.vehicleInsuranceQuotes.mapper.CarMapper;
import ca.assignment.vehicleInsuranceQuotes.mapper.QuoteRequestMapper;

@Component
public class QuoteRequestMapperImpl implements QuoteRequestMapper {
	
	@Autowired
	protected CarMapper carMapper;


	@Override
	public QuoteRequestDTO entityToDto(QuoteRequestEntity entityType) {
		if (entityType == null) {
            return null;
        }

		return QuoteRequestDTO.builder().id(entityType.getId())
				.age(entityType.getAge())
				.drivingExperience(entityType.getDrivingExperience())
				.driverRecord(entityType.getDriverRecord())
				.claims(entityType.getClaims())
				.carValue(entityType.getCarValue())
				.annualMileage(entityType.getAnnualMileage())
				.insuranceHistory(entityType.getInsuranceHistory())
				.carId(entityType.getCarId()).build();
	}

	@Override
	public QuoteRequestEntity dtoToEntity(QuoteRequestDTO dtoType) {
		if (dtoType == null) {
            return null;
        }

		return QuoteRequestEntity.builder().id(dtoType.getId())
				.age(dtoType.getAge())
				.drivingExperience(dtoType.getDrivingExperience())
				.driverRecord(dtoType.getDriverRecord())
				.claims(dtoType.getClaims())
				.carValue(dtoType.getCarValue())
				.annualMileage(dtoType.getAnnualMileage())
				.insuranceHistory(dtoType.getInsuranceHistory())
				.carId(dtoType.getCarId()).build();
	}

}
