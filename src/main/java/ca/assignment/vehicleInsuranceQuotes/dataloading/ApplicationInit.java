package ca.assignment.vehicleInsuranceQuotes.dataloading;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import ca.assignment.vehicleInsuranceQuotes.dataloading.dto.CarDataDTO;
import ca.assignment.vehicleInsuranceQuotes.service.CarService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class ApplicationInit {
	
	@Value("${app.initial-car-list-url:https://storage.googleapis.com/connex-th/insurance_assignment/car_model.json}")
	private String initialCarListUrl;
	
	@Autowired
	protected CarService carService;


    @PostConstruct
    private void appInit() {
		carService.count().subscribe(count -> {
			if (count == 0) {
				log.info("*************** Initializing application... ***************");
				
				WebClient.builder()
		          .baseUrl(initialCarListUrl)
		          .exchangeStrategies(ExchangeStrategies
		    		  .builder().codecs(codecs -> codecs.defaultCodecs()
						  .maxInMemorySize(3 * 1024 * 1024)).build())
		          .build().get().retrieve().bodyToMono(CarDataDTO.class)
		          .map(cars -> cars.getData().getCarModelLists().getResults())
		          .flatMapMany(Flux::fromIterable)
			      .doOnNext(car -> carService.create(Mono.just(car)).subscribe())
			      .doOnComplete(() -> {
			    	  log.info("*************** Finishing initializing application. ***************");
			      }).subscribe();
			}
		});
    }

}
