package com.factory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class KrushnaFactoryApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(KrushnaFactoryApplication.class, args);
		System.out.println("Rupali Gajare");
	}

}
