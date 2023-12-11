package ca.assignment.vehicleInsuranceQuotes.controller.filter;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import ca.assignment.vehicleInsuranceQuotes.controller.common.GlobalTimestampHolder;
import reactor.core.publisher.Mono;

@Component
public class TimestampFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    	GlobalTimestampHolder.setTimestamp(LocalDateTime.now());
        return chain.filter(exchange);
    }
}
