package ca.assignment.vehicleInsuranceQuotes.controller.handler.impl;

import static ca.assignment.vehicleInsuranceQuotes.controller.common.Constants.CAR_CATEGORY_ATTRIBUTE_NAME;
import static ca.assignment.vehicleInsuranceQuotes.controller.common.Constants.CAR_MAKE_ATTRIBUTE_NAME;
import static ca.assignment.vehicleInsuranceQuotes.controller.common.Constants.CAR_MODEL_ATTRIBUTE_NAME;
import static ca.assignment.vehicleInsuranceQuotes.controller.common.Constants.CAR_YEAR_ATTRIBUTE_NAME;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import ca.assignment.vehicleInsuranceQuotes.bean.dto.CarDTO;
import ca.assignment.vehicleInsuranceQuotes.controller.handler.CarHandler;
import ca.assignment.vehicleInsuranceQuotes.service.CarService;
import reactor.core.publisher.Mono;

@Component
public class CarHandlerImpl implements CarHandler {

	@Autowired
    protected CarService carService;


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Mono<ServerResponse> search(ServerRequest request) {
		Function<String, String> getParam =
			paramKey -> request.queryParam(paramKey).orElse(null);

		var yearString = getParam.apply(CAR_YEAR_ATTRIBUTE_NAME);
		var carDTO = CarDTO.builder()
				.category(getParam.apply(CAR_CATEGORY_ATTRIBUTE_NAME))
				.make(getParam.apply(CAR_MAKE_ATTRIBUTE_NAME))
				.model(getParam.apply(CAR_MODEL_ATTRIBUTE_NAME))
				.year(yearString == null ? null : Short.valueOf(yearString))
				.build();

		return carService.search(carDTO).collectList().flatMap(cars -> {
            if (cars.isEmpty()) {
                return ServerResponse.noContent().build();
            }
            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                    .body(fromValue(cars));
        });
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Mono<ServerResponse> create(ServerRequest request) {
		return carService.create(request.bodyToMono(CarDTO.class))
				.flatMap(car -> ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(fromValue(car)))
				.onErrorResume(erro -> ServerResponse.badRequest().build());
	}

}
