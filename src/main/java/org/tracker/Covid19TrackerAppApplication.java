package org.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Covid19TrackerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(Covid19TrackerAppApplication.class, args);
	}
}
