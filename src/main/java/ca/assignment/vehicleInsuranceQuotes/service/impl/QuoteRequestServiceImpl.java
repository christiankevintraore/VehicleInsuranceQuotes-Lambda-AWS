package ca.assignment.vehicleInsuranceQuotes.service.impl;

import static ca.assignment.vehicleInsuranceQuotes.bean.common.QuoteStatus.SUCCESS;
import static ca.assignment.vehicleInsuranceQuotes.bean.common.QuoteStatus.UNKNOWN_PREMIUM;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.assignment.vehicleInsuranceQuotes.bean.dto.QuoteDTO;
import ca.assignment.vehicleInsuranceQuotes.bean.dto.QuoteRequestDTO;
import ca.assignment.vehicleInsuranceQuotes.bean.dto.validator.DtoBeanValidator;
import ca.assignment.vehicleInsuranceQuotes.bean.entity.CarEntity;
import ca.assignment.vehicleInsuranceQuotes.bean.entity.QuoteEntity;
import ca.assignment.vehicleInsuranceQuotes.bean.entity.QuoteRequestEntity;
import ca.assignment.vehicleInsuranceQuotes.config.properties.premiumfactors.PremiumFactorsProperties;
import ca.assignment.vehicleInsuranceQuotes.mapper.QuoteRequestMapper;
import ca.assignment.vehicleInsuranceQuotes.repository.CarRepository;
import ca.assignment.vehicleInsuranceQuotes.repository.QuoteRequestRepository;
import ca.assignment.vehicleInsuranceQuotes.service.QuoteRequestService;
import ca.assignment.vehicleInsuranceQuotes.service.QuoteService;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * The default implementation of quote request service.
 */
@Service
@Slf4j
public class QuoteRequestServiceImpl implements QuoteRequestService {
	
	private Function<BigDecimal, BigDecimal> SET_SCALE = input -> {
		if (input != null) {
			return input.setScale(2, RoundingMode.HALF_UP);
		}
		
		return input;
	};
	private BigDecimal ZERO = SET_SCALE.apply(BigDecimal.ZERO);
	
	
	@Autowired
	protected QuoteRequestRepository quoteRequestRepository;
	
	@Autowired
	protected CarRepository carRepository;
	
	@Autowired
	protected QuoteService quoteService;
	
	@Autowired
	protected QuoteRequestMapper quoteRequestMapper;
	
	@Autowired
	protected DtoBeanValidator dtoBeanValidator;
	
	@Value("${app.business.base-premium:2000.00}")
	private BigDecimal basePremium;
	
	@Autowired
	private PremiumFactorsProperties premiumFactorsProperties;
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Mono<QuoteRequestDTO> findById(final String id) {
		return quoteRequestRepository.findById(id).map(quoteRequest ->
				quoteRequestMapper.entityToDto(quoteRequest));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public Mono<QuoteDTO> create(final Mono<QuoteRequestDTO> quoteRequestDTO) {
		return createQuoteRequest(quoteRequestDTO).flatMap(createdQuoteRequest -> {
			var computedPremium = computePremium(createdQuoteRequest);
			var quoteToCreate = QuoteEntity.builder()
					.requestId(createdQuoteRequest.getId())
					.status(computedPremium == null ? UNKNOWN_PREMIUM : SUCCESS)
					.premium(computedPremium).build();
			
			return quoteService.create(quoteToCreate);
		});
	}
	
	private BigDecimal computePremium(QuoteRequestEntity quoteRequest) {
		Predicate<BigDecimal> unknownFactorPredicate =
				factor -> (factor == null) || ZERO.equals(factor);

		List<BigDecimal> factorsList = getFactorsList(quoteRequest);
		if (factorsList.stream().filter(unknownFactorPredicate)
				.findFirst().isPresent()) {
			return null;
		}
		
		return factorsList.stream()
				.reduce(SET_SCALE.apply(basePremium), BigDecimal::multiply);
	}
	
	private List<BigDecimal> getFactorsList(QuoteRequestEntity quoteRequest) {
		List<BigDecimal> result = new ArrayList<>(7);
		
		result.add(SET_SCALE.apply(premiumFactorsProperties.getAge()
			.floorEntry(quoteRequest.getAge()).getValue()));
		
		result.add(SET_SCALE.apply(premiumFactorsProperties
			.getDrivingExperience()
			.floorEntry(quoteRequest.getDrivingExperience())
			.getValue()));

		result.add(SET_SCALE.apply(premiumFactorsProperties
			.getDriverRecord()
			.floorEntry(quoteRequest.getDriverRecord())
			.getValue()));

		result.add(SET_SCALE.apply(premiumFactorsProperties.getClaims()
			.floorEntry(quoteRequest.getClaims()).getValue()));

		result.add(SET_SCALE.apply(premiumFactorsProperties.getCarValue()
			.floorEntry(quoteRequest.getCarValue()).getValue()));

		result.add(SET_SCALE.apply(premiumFactorsProperties
			.getAnnualMileage()
			.floorEntry(quoteRequest.getAnnualMileage())
			.getValue()));

		result.add(SET_SCALE.apply(premiumFactorsProperties
				.getInsuranceHistory()
				.floorEntry(quoteRequest.getInsuranceHistory())
				.getValue()));

		return result;
	}

	private Mono<QuoteRequestEntity> createQuoteRequest(final Mono<QuoteRequestDTO> quoteRequestDTO) {
		return quoteRequestDTO.flatMap(inputQuoteRequest -> {
			var validationMessage = dtoBeanValidator.validate(inputQuoteRequest);
			
			return Mono.defer(() -> {
				if (validationMessage != null) {
					return Mono.error(new ValidationException(validationMessage));
				}
				

				inputQuoteRequest.setCarValue(SET_SCALE.apply(inputQuoteRequest.getCarValue()));
				QuoteRequestEntity quoteRequestToSave = quoteRequestMapper
						.dtoToEntity(inputQuoteRequest);
				
				return carRepository.findById(quoteRequestToSave.getCarId())
						.defaultIfEmpty(CarEntity.builder().build())
						.zipWith(Mono.just(quoteRequestToSave));
			});
		}).flatMap(existingCarAndQuoteRequestToSave -> {
			var existingCar = existingCarAndQuoteRequestToSave.getT1();
			var quoteRequestToSave = existingCarAndQuoteRequestToSave.getT2();
			
			if (existingCar.getId() == null) {
				String doubleQuoteString = "\"";
				String errorMessage = new StringBuilder()
					.append("Unable to find the defined car with id : ")
					.append(doubleQuoteString)
					.append(quoteRequestToSave.getCarId())
					.append(doubleQuoteString).toString();

				log.error(errorMessage);

				return Mono.error(new InvalidPropertyException(String.class, "car", errorMessage));
			}
			
			return quoteRequestRepository.save(quoteRequestToSave);
		});
	}

}
