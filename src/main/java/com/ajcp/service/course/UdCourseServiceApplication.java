package com.ajcp.service.course;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@OpenAPIDefinition(
		info = @Info(title = "Course service",
				version = "1.0.0",
				description = "Course Microservice"))
public class UdCourseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UdCourseServiceApplication.class, args);
	}

}
