package ca.assignment.vehicleInsuranceQuotes.controller.handler;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

/**
 * Descriptor of the car handler.
 */
public interface CarHandler {

	/**
	 * Search for cars, empty list if none found.
	 * 
	 * @param id {@link ServerRequest} : the call request.
	 * 
	 * @return {@link Mono} : the cars list found.
	 */
    public Mono<ServerResponse> search(final ServerRequest request);
	
    /**
	 * Create a new car.
	 * 
	 * @param id {@link ServerRequest} : the call request.
	 * 
	 * @return {@link Mono} : the created car, with new id.
	 */
    public Mono<ServerResponse> create(final ServerRequest request);
	
}
