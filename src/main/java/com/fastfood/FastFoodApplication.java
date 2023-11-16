package com.fastfood;

import com.fastfood.model.Customer;
import com.fastfood.model.Order;
import com.fastfood.model.Role;
import com.fastfood.model.User;
import com.fastfood.repository.CustomerRepository;
import com.fastfood.repository.OrderRepository;
import com.fastfood.repository.ProductRepository;
import com.fastfood.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
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

	@Bean
	CommandLineRunner run(ProductRepository productRepo, CustomerRepository customerRepo,
						  OrderRepository orderRepo, UserService userService) {

		return args -> {
			var users = userService.findAllUsers();
			if (users.isEmpty()) {
				userService.createUser(new User("James Kirk", "james@enterprise.com", "123456", Role.ADMIN));
				userService.createUser(new User("Spock", "spock@enterprise.com", "123456", Role.ADMIN));
				userService.createUser(new User("Leonard McCoy", "mccoy@enterprise.com", "123456", Role.USER));
				userService.createUser(new User("Montgomery Scott", "scott@enterprise.com", "123456", Role.USER));
			}
		};
	}
}
