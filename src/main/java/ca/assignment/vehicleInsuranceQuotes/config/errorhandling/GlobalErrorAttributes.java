package ca.assignment.vehicleInsuranceQuotes.config.errorhandling;

import java.util.Map;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {
	
	@Override
	public Map<String, Object> getErrorAttributes(ServerRequest serverRequest, ErrorAttributeOptions options) {
		Map<String, Object> errorAttributes = super.getErrorAttributes(serverRequest, options);
		
		log.error("Global error catched, attributes :\n{}\n", errorAttributes);
		
		return errorAttributes;
	}

}
