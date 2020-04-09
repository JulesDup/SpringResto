package dev.config;

import java.util.Scanner;

import org.springframework.context.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan({ "dev.ihm", "dev.service", "dev.dao" })
@PropertySource("app.properties")
public class AppConfig {

	@Bean
	public Scanner scanner() {
		return new Scanner(System.in);
	}

}
