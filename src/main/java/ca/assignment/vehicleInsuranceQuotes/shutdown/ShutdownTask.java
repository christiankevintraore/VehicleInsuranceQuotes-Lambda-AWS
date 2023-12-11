package ca.assignment.vehicleInsuranceQuotes.shutdown;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mongodb.reactivestreams.client.MongoClient;

import ca.assignment.vehicleInsuranceQuotes.controller.common.GlobalTimestampHolder;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ShutdownTask {
	
	@Value("${app.inactivity-timeout-minutes:5}")
    private int inactivityTimeoutMinutes;
	
	@Autowired
	private ApplicationContext context;

	@Autowired
	private MongoClient mongoClient;


	@Async
    @Scheduled(fixedRate = 60000)
    public void checkInactivity() {
    	var duration = Duration.between(GlobalTimestampHolder
    			.getTimestamp(), LocalDateTime.now());

    	if (duration.toMinutes() > inactivityTimeoutMinutes) {
        	log.info("*************** Inactivity timeout, shutting down the application... ***************");
        	if (mongoClient != null) {
                mongoClient.close();
            }
        	System.exit(SpringApplication.exit(context, () -> 0));
		}
    }

}
