package com.raju.spring_boot_observability;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.raju.spring_boot_observability")
public class SpringBootObservabilityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootObservabilityApplication.class, args);
	}

}
