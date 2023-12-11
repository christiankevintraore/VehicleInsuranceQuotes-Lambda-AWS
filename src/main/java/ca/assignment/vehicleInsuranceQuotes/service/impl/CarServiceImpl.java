package ca.assignment.vehicleInsuranceQuotes.service.impl;

import java.util.Optional;
import java.util.function.Function;

import org.owasp.encoder.Encode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.assignment.vehicleInsuranceQuotes.bean.dto.CarDTO;
import ca.assignment.vehicleInsuranceQuotes.bean.dto.validator.DtoBeanValidator;
import ca.assignment.vehicleInsuranceQuotes.bean.entity.CarEntity;
import ca.assignment.vehicleInsuranceQuotes.mapper.CarMapper;
import ca.assignment.vehicleInsuranceQuotes.repository.CarRepository;
import ca.assignment.vehicleInsuranceQuotes.service.CarService;
import jakarta.validation.ValidationException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The default implementation of car service.
 */
@Service
public class CarServiceImpl implements CarService {
	
	@Autowired
	protected CarRepository carRepository;
	
	@Autowired
	protected CarMapper carMapper;
	
	@Autowired
	protected DtoBeanValidator dtoBeanValidator;


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Flux<CarDTO> search(final CarDTO carDTO) {
		Function<String, String> checkSearchValue = (searchValue) -> {
			Optional<String> optionalValue = Optional.ofNullable(searchValue);
			optionalValue.ifPresent(value -> Encode.forJava(value));
			return optionalValue.orElse("");
		};
		
		var category = carDTO.getCategory();
		var make = carDTO.getMake();
		var model = carDTO.getModel();
		var year = carDTO.getYear();
		if ((category == null) && (make == null)
				&& (model == null) && (year == null)) {
			return carRepository.findAll()
					.map(currentCar -> carMapper.entityToDto(currentCar));
		}

		Flux<CarEntity> result = (year == null) ?
				carRepository.
				findByCategoryContainingIgnoreCaseAndMakeContainingIgnoreCaseAndModelContainingIgnoreCase
				(checkSearchValue.apply(category), checkSearchValue.apply(make), checkSearchValue.apply(model))
				:
				carRepository.
				findByCategoryContainingIgnoreCaseAndMakeContainingIgnoreCaseAndModelContainingIgnoreCaseAndYear
				(checkSearchValue.apply(category), checkSearchValue.apply(make), checkSearchValue.apply(model), year);

		return result.map(currentCar -> carMapper.entityToDto(currentCar));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Mono<CarDTO> create(final Mono<CarDTO> carDTO) {
		return carDTO.flatMap(inputCar -> {
			var validationMessage = dtoBeanValidator.validate(inputCar);
			
			return Mono.defer(() -> {
				if (validationMessage != null) {
					return Mono.error(new ValidationException(validationMessage));
				}
				
				return search(inputCar).collectSortedList().flatMap(existingCars -> {
					if (existingCars.isEmpty()) {
						CarEntity carToSave = carMapper.dtoToEntity(inputCar);
						
						return carRepository.save(carToSave)
								.map(currentCar -> carMapper.entityToDto(currentCar));
					}
					
					return Mono.just(existingCars.get(0));
				});
			});
		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Mono<Long> count() {
		return carRepository.count();
	}

}
