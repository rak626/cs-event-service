package com.crowdseats.event;

import com.crowdseats.framework.common.util.ProfileSetup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.crowdseats.framework.common.feign.clients")
public class EventServiceApplication {

	public static void main(String[] args) {
		System.setProperty("service.name", "event");
//		SpringApplication.run(EventServiceApplication.class, args);
		ProfileSetup.setupProfile(new SpringApplication(EventServiceApplication.class), args);
	}

}
