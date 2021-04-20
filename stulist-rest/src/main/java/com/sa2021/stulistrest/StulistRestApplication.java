package com.sa2021.stulistrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class StulistRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(StulistRestApplication.class, args);
	}
	
}