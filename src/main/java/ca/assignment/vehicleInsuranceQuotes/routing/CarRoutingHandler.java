package ca.assignment.vehicleInsuranceQuotes.routing;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import ca.assignment.vehicleInsuranceQuotes.controller.handler.CarHandler;
import ca.assignment.vehicleInsuranceQuotes.routing.common.AbstractRoutingHandler;

/**
 * Car routing handler creator.
 */
@Configuration
public class CarRoutingHandler extends AbstractRoutingHandler {


    @Bean
    public RouterFunction<ServerResponse> carRouter(final CarHandler carHandler) {
    	final String api = getBaseRoute("cars");

        return route(GET(api), carHandler::search)
                .andRoute(POST(api).and(accept(MediaType.APPLICATION_JSON)), carHandler::create);
    }
	
}
