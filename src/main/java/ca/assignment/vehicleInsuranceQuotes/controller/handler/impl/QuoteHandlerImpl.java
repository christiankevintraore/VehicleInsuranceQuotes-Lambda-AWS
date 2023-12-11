package ca.assignment.vehicleInsuranceQuotes.controller.handler.impl;

import static ca.assignment.vehicleInsuranceQuotes.controller.common.Constants.ENTITY_ID_PATH_VARIABLE_NAME;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import ca.assignment.vehicleInsuranceQuotes.controller.handler.QuoteHandler;
import ca.assignment.vehicleInsuranceQuotes.service.QuoteService;
import reactor.core.publisher.Mono;

@Component
public class QuoteHandlerImpl implements QuoteHandler {

	@Autowired
    protected QuoteService quoteService;


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Mono<ServerResponse> findById(ServerRequest request) {
		return quoteService.findById(request
				.pathVariable(ENTITY_ID_PATH_VARIABLE_NAME))
				.flatMap(quote -> ServerResponse.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(fromValue(quote)))
				.switchIfEmpty(ServerResponse.notFound().build());
	}
    
}
