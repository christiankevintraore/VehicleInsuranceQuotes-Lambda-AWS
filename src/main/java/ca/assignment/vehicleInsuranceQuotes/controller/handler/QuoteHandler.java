package ca.assignment.vehicleInsuranceQuotes.controller.handler;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

/**
 * Descriptor of the quote handler.
 */
public interface QuoteHandler {

	/**
	 * Search for a specific quote, null if not found.
	 * 
	 * @param id {@link ServerRequest} : the call request.
	 * 
	 * @return {@link Mono} : the quote found.
	 */
    public Mono<ServerResponse> findById(final ServerRequest request);
	
}
