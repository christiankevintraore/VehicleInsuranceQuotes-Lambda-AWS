package ca.assignment.vehicleInsuranceQuotes.mapper;

import org.mapstruct.Mapper;

import ca.assignment.vehicleInsuranceQuotes.bean.dto.QuoteDTO;
import ca.assignment.vehicleInsuranceQuotes.bean.entity.QuoteEntity;


@Mapper(componentModel = "spring")
public interface QuoteMapper extends GenericMapper<QuoteEntity, QuoteDTO> {
}
