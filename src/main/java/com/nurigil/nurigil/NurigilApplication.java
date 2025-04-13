package com.nurigil.nurigil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NurigilApplication {

	public static void main(String[] args) {
		SpringApplication.run(NurigilApplication.class, args);
	}

}
