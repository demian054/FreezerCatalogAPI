package com.catalog.freezer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FreezerCatalogApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreezerCatalogApiApplication.class, args);
	}
	
}

