package ca.assignment.vehicleInsuranceQuotes.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import ca.assignment.vehicleInsuranceQuotes.bean.entity.CarEntity;
import reactor.core.publisher.Flux;

@Repository
public interface CarRepository extends ReactiveCrudRepository<CarEntity, String> {

	public Flux<CarEntity>
	findByCategoryContainingIgnoreCaseAndMakeContainingIgnoreCaseAndModelContainingIgnoreCase
	(final String category, final String make, final String model);
	
	public Flux<CarEntity>
	findByCategoryContainingIgnoreCaseAndMakeContainingIgnoreCaseAndModelContainingIgnoreCaseAndYear
	(final String category, final String make, final String model, final Short year);

}
