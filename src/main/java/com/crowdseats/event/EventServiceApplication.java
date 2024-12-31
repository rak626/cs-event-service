package com.crowdseats.event;

import com.crowdseats.framework.common.util.ProfileSetup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EventServiceApplication {

	public static void main(String[] args) {
		System.setProperty("service.name", "event");
//		SpringApplication.run(EventServiceApplication.class, args);
		ProfileSetup.setupProfile(new SpringApplication(EventServiceApplication.class), args);
	}

}
