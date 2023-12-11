package ca.assignment.vehicleInsuranceQuotes.routing.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Common routing handler extended by all others handlers.
 */
@Configuration
public abstract class AbstractRoutingHandler {

    @Value("${server.servlet.context-path:/api/v1}")
	protected String apiRootPath;


    protected String getBaseRoute(final String routeUri) {
        return new StringBuilder().append(apiRootPath)
        		.append("/").append(routeUri).toString();
    }
    
    protected String getPathVariableRoute(final String baseUri, final String pathVariableName) {
        return new StringBuilder().append(baseUri).append("/{")
        		.append(pathVariableName).append("}").toString();
    }
	
}
