package com.pppfreak.Hired;

import com.pppfreak.Hired.ServiceImpl.EmployeeServiceImpl;
import com.pppfreak.Hired.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class HiredApplication {

	public static void main(String[] args) {
		SpringApplication.run(HiredApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SpringApplicationContext springApplicationContext(){
		return new SpringApplicationContext();
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
