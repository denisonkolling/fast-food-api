package com.fastfood;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class FastFoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastFoodApplication.class, args);
	}

	@Bean
	public ModelMapper getModelMapper() {
		var mapper = new ModelMapper();
		mapper.getConfiguration().setSkipNullEnabled(true);
		return mapper;
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
