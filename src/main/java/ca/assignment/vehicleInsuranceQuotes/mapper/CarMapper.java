package ca.assignment.vehicleInsuranceQuotes.mapper;

import org.mapstruct.Mapper;

import ca.assignment.vehicleInsuranceQuotes.bean.dto.CarDTO;
import ca.assignment.vehicleInsuranceQuotes.bean.entity.CarEntity;


@Mapper(componentModel = "spring")
public interface CarMapper extends GenericMapper<CarEntity, CarDTO> {
}
