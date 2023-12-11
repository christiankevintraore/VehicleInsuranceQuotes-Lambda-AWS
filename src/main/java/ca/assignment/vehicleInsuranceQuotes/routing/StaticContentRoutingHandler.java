package ca.assignment.vehicleInsuranceQuotes.routing;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import ca.assignment.vehicleInsuranceQuotes.routing.common.AbstractRoutingHandler;

/**
 * Static content into 'resources/public' directory routing handler creator.
 */
@Configuration
public class StaticContentRoutingHandler extends AbstractRoutingHandler {


	@Bean
	public RouterFunction<ServerResponse> htmlRouter() {
		return RouterFunctions.route().nest(path("/api/v1").negate(),
				builder -> builder.resources("/**",
							new ClassPathResource("public/"))).build();
	}
	
}
