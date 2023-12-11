package ca.assignment.vehicleInsuranceQuotes.service;

import ca.assignment.vehicleInsuranceQuotes.bean.dto.CarDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Descriptor of the car service.
 */
public interface CarService {

	/**
	 * Search for cars, empty list if none found.
	 * 
	 * @param carDTO {@link Mono} : the search criteria.
	 * 
	 * @return {@link Flux} : the cars list found.
	 */
    public Flux<CarDTO> search(final CarDTO carDTO);
	
    /**
	 * Create a new car.
	 * 
	 * @param carDTO {@link Mono} : the car to create.
	 * 
	 * @return {@link Mono} : the created car, with new id.
	 */
    public Mono<CarDTO> create(final Mono<CarDTO> carDTO);
	
    /**
	 * Gives the number of existing cars.
	 * 
	 * @return {@link Mono} : the number of existing cars.
	 */
    public Mono<Long> count();
	
}
