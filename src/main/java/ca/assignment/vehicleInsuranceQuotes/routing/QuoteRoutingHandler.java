package ca.assignment.vehicleInsuranceQuotes.routing;

import static ca.assignment.vehicleInsuranceQuotes.controller.common.Constants.ENTITY_ID_PATH_VARIABLE_NAME;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import ca.assignment.vehicleInsuranceQuotes.controller.handler.QuoteHandler;
import ca.assignment.vehicleInsuranceQuotes.routing.common.AbstractRoutingHandler;

/**
 * Quote routing handler creator.
 */
@Configuration
public class QuoteRoutingHandler extends AbstractRoutingHandler {


    @Bean
    public RouterFunction<ServerResponse> quoteRouter(final QuoteHandler quoteHandler) {
    	final String baseRoute = getBaseRoute("quotes");
    	final String withIdRoute = getPathVariableRoute(baseRoute, ENTITY_ID_PATH_VARIABLE_NAME);

        return route(GET(withIdRoute), quoteHandler::findById);
    }
	
}
