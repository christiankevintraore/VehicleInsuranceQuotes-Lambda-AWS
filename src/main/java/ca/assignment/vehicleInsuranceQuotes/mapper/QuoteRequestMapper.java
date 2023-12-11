package ca.assignment.vehicleInsuranceQuotes.mapper;

import org.mapstruct.Mapper;

import ca.assignment.vehicleInsuranceQuotes.bean.dto.QuoteRequestDTO;
import ca.assignment.vehicleInsuranceQuotes.bean.entity.QuoteRequestEntity;


@Mapper(componentModel = "spring")
public interface QuoteRequestMapper extends GenericMapper<QuoteRequestEntity, QuoteRequestDTO> {
}
