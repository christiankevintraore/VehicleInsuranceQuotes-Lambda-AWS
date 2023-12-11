package ca.assignment.vehicleInsuranceQuotes.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import ca.assignment.vehicleInsuranceQuotes.bean.entity.QuoteEntity;

@Repository
public interface QuoteRepository extends ReactiveCrudRepository<QuoteEntity, String> {
}
