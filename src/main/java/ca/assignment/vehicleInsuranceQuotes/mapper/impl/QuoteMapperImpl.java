package ca.assignment.vehicleInsuranceQuotes.mapper.impl;

import org.springframework.stereotype.Component;

import ca.assignment.vehicleInsuranceQuotes.bean.dto.QuoteDTO;
import ca.assignment.vehicleInsuranceQuotes.bean.entity.QuoteEntity;
import ca.assignment.vehicleInsuranceQuotes.mapper.QuoteMapper;

@Component
public class QuoteMapperImpl implements QuoteMapper {


	@Override
	public QuoteDTO entityToDto(QuoteEntity entityType) {
		if (entityType == null) {
            return null;
        }

		return QuoteDTO.builder()
				.id(entityType.getId())
				.status(entityType.getStatus())
				.premium(entityType.getPremium())
				.requestId(entityType.getRequestId())
				.build();
	}

	@Override
	public QuoteEntity dtoToEntity(QuoteDTO dtoType) {
		if (dtoType == null) {
            return null;
        }

		return QuoteEntity.builder()
				.id(dtoType.getId())
				.status(dtoType.getStatus())
				.premium(dtoType.getPremium())
				.requestId(dtoType.getRequestId())
				.build();
	}

}
