package ca.assignment.vehicleInsuranceQuotes.mapper.impl;

import org.springframework.stereotype.Component;

import ca.assignment.vehicleInsuranceQuotes.bean.dto.CarDTO;
import ca.assignment.vehicleInsuranceQuotes.bean.entity.CarEntity;
import ca.assignment.vehicleInsuranceQuotes.mapper.CarMapper;

@Component
public class CarMapperImpl implements CarMapper {

	@Override
	public CarDTO entityToDto(CarEntity entityType) {
		if (entityType == null) {
            return null;
        }

		return CarDTO.builder().id(entityType.getId())
				.category(entityType.getCategory())
				.make(entityType.getMake())
				.model(entityType.getModel())
				.year(entityType.getYear()).build();
	}

	@Override
	public CarEntity dtoToEntity(CarDTO dtoType) {
		if (dtoType == null) {
            return null;
        }

		return CarEntity.builder().id(dtoType.getId())
				.category(dtoType.getCategory())
				.make(dtoType.getMake())
				.model(dtoType.getModel())
				.year(dtoType.getYear()).build();
	}

}
