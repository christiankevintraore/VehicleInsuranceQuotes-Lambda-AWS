package ca.assignment.vehicleInsuranceQuotes.config.properties.premiumfactors;

import java.math.BigDecimal;
import java.util.NavigableMap;
import java.util.TreeMap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "app.business.premium-factors")
@Getter
@Setter
public class PremiumFactorsProperties {
	
	private final NavigableMap<Short, BigDecimal> age = new TreeMap<>();
	private final NavigableMap<Short, BigDecimal> drivingExperience = new TreeMap<>();
	private final NavigableMap<Short, BigDecimal> driverRecord = new TreeMap<>();
	private final NavigableMap<Short, BigDecimal> claims = new TreeMap<>();
	private final NavigableMap<BigDecimal, BigDecimal> carValue = new TreeMap<>();
	private final NavigableMap<Integer, BigDecimal> annualMileage = new TreeMap<>();
	private final NavigableMap<Short, BigDecimal> insuranceHistory = new TreeMap<>();

}
