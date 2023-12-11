package ca.assignment.vehicleInsuranceQuotes.service;

import ca.assignment.vehicleInsuranceQuotes.bean.dto.QuoteDTO;
import ca.assignment.vehicleInsuranceQuotes.bean.entity.QuoteEntity;
import reactor.core.publisher.Mono;

/**
 * Descriptor of the quote service.
 */
public interface QuoteService {

	/**
	 * Search for a specific quote, null if not found.
	 * 
	 * @param id {@link String} : the quote id.
	 * 
	 * @return {@link Mono} : the quote found.
	 */
    public Mono<QuoteDTO> findById(final String id);
	
    /**
	 * Create a new quote.
	 * 
	 * @param quoteEntity {@link QuoteEntity} : the quote to create.
	 * 
	 * @return {@link Mono} : the quote created, with new id.
	 */
    public Mono<QuoteDTO> create(final QuoteEntity quoteEntity);
	
}
