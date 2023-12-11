package ca.assignment.vehicleInsuranceQuotes.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import ca.assignment.vehicleInsuranceQuotes.bean.entity.QuoteRequestEntity;

@Repository
public interface QuoteRequestRepository extends ReactiveCrudRepository<QuoteRequestEntity, String> {
}
