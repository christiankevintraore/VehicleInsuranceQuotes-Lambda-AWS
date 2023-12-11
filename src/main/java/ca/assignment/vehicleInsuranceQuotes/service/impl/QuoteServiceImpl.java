package ca.assignment.vehicleInsuranceQuotes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.assignment.vehicleInsuranceQuotes.bean.dto.QuoteDTO;
import ca.assignment.vehicleInsuranceQuotes.bean.dto.validator.DtoBeanValidator;
import ca.assignment.vehicleInsuranceQuotes.bean.entity.QuoteEntity;
import ca.assignment.vehicleInsuranceQuotes.mapper.QuoteMapper;
import ca.assignment.vehicleInsuranceQuotes.repository.QuoteRepository;
import ca.assignment.vehicleInsuranceQuotes.repository.QuoteRequestRepository;
import ca.assignment.vehicleInsuranceQuotes.service.QuoteService;
import jakarta.validation.ValidationException;
import reactor.core.publisher.Mono;

/**
 * The default implementation of quote service.
 */
@Service
@Transactional
public class QuoteServiceImpl implements QuoteService {
	
	@Autowired
	protected QuoteRepository quoteRepository;
	@Autowired
	protected QuoteRequestRepository quoteRequestRepository;
	
	@Autowired
	protected QuoteMapper quoteMapper;
	
	@Autowired
	protected DtoBeanValidator dtoBeanValidator;
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Mono<QuoteDTO> findById(final String id) {
		return quoteRepository.findById(id)
				.map(quote -> quoteMapper.entityToDto(quote));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Mono<QuoteDTO> create(final QuoteEntity quoteEntity) {
		var validationMessage = dtoBeanValidator.validate(quoteEntity);
		
		return Mono.defer(() -> {
			if (validationMessage != null) {
				return Mono.error(new ValidationException(validationMessage));
			}
			
			return quoteRepository.save(quoteEntity)
					.map(createdQuote -> quoteMapper.entityToDto(createdQuote));
		});
	}

}
