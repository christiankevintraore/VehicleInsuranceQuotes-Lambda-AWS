package ca.assignment.vehicleInsuranceQuotes.service;

import ca.assignment.vehicleInsuranceQuotes.bean.dto.QuoteDTO;
import ca.assignment.vehicleInsuranceQuotes.bean.dto.QuoteRequestDTO;
import reactor.core.publisher.Mono;

/**
 * Descriptor of the quote request service.
 */
public interface QuoteRequestService {

	/**
	 * Search for a specific quote request, null if not found.
	 * 
	 * @param id {@link String} : the quote request id.
	 * 
	 * @return {@link Mono} : the found quote request.
	 */
    public Mono<QuoteRequestDTO> findById(final String id);
	
    /**
	 * Create a new quote request.
	 * 
	 * @param quoteRequestDTO {@link Mono} : the quote request to create.
	 * 
	 * @return {@link Mono} : the created quote, with the request and new id.
	 */
    public Mono<QuoteDTO> create(final Mono<QuoteRequestDTO> quoteRequestDTO);
	
}
