package ca.assignment.vehicleInsuranceQuotes.controller.handler;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

/**
 * Descriptor of the quote request handler.
 */
public interface QuoteRequestHandler {

	/**
	 * Search for a specific quote request, null if not found.
	 * 
	 * @param id {@link String} : the quote request id.
	 * 
	 * @return {@link Mono} : the found quote request.
	 */
    public Mono<ServerResponse> findById(final ServerRequest request);
	
    /**
	 * Create a new quote request.
	 * 
	 * @param quoteRequestDTO {@link Mono} : the quote request to create.
	 * 
	 * @return {@link Mono} : the created quote request, with new id.
	 */
    public Mono<ServerResponse> create(final ServerRequest request);
	
}
