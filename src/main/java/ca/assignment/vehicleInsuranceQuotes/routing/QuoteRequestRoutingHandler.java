package ca.assignment.vehicleInsuranceQuotes.routing;

import static ca.assignment.vehicleInsuranceQuotes.controller.common.Constants.ENTITY_ID_PATH_VARIABLE_NAME;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import ca.assignment.vehicleInsuranceQuotes.controller.handler.QuoteRequestHandler;
import ca.assignment.vehicleInsuranceQuotes.routing.common.AbstractRoutingHandler;

/**
 * Car routing handler creator.
 */
@Configuration
public class QuoteRequestRoutingHandler extends AbstractRoutingHandler {


    @Bean
    public RouterFunction<ServerResponse> quoteRequestRouter(final QuoteRequestHandler quoteRequestHandler) {
    	final String baseRoute = getBaseRoute("quotesrequest");
    	final String withIdRoute = getPathVariableRoute(baseRoute, ENTITY_ID_PATH_VARIABLE_NAME);

        return route(POST(baseRoute).and(accept(MediaType.APPLICATION_JSON)), quoteRequestHandler::create)
                .andRoute(GET(withIdRoute), quoteRequestHandler::findById);
    }
	
}
