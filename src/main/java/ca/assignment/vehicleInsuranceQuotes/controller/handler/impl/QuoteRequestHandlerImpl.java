package ca.assignment.vehicleInsuranceQuotes.controller.handler.impl;

import static ca.assignment.vehicleInsuranceQuotes.controller.common.Constants.ENTITY_ID_PATH_VARIABLE_NAME;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import ca.assignment.vehicleInsuranceQuotes.bean.dto.QuoteRequestDTO;
import ca.assignment.vehicleInsuranceQuotes.controller.handler.QuoteRequestHandler;
import ca.assignment.vehicleInsuranceQuotes.service.QuoteRequestService;
import reactor.core.publisher.Mono;

@Component
public class QuoteRequestHandlerImpl implements QuoteRequestHandler {

	@Autowired
    protected QuoteRequestService quoteRequestService;


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Mono<ServerResponse> findById(ServerRequest request) {
		return quoteRequestService
				.findById(request.pathVariable(ENTITY_ID_PATH_VARIABLE_NAME))
				.flatMap(quoteRequest -> ServerResponse.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(fromValue(quoteRequest)))
				.switchIfEmpty(ServerResponse.notFound().build());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Mono<ServerResponse> create(ServerRequest request) {
		return quoteRequestService
				.create(request.bodyToMono(QuoteRequestDTO.class))
				.flatMap(quoteRequest -> ServerResponse.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(fromValue(quoteRequest)))
				.onErrorResume(erro -> ServerResponse.badRequest().build());
	}

}
