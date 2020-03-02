package com.coding.sushi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class SushiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SushiApplication.class, args);
	}

}
