package com.example.blackpancake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BlackpancakeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlackpancakeApplication.class, args);
	}

}
