package ca.assignment.vehicleInsuranceQuotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.reactive.config.EnableWebFlux;

@PropertySources({
    @PropertySource("classpath:application.yml")
})
@EnableWebFlux
@EnableScheduling
@EnableTransactionManagement
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class,
	WebMvcAutoConfiguration.class})
public class VehicleInsuranceQuotesApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleInsuranceQuotesApplication.class, args);
	}

}
