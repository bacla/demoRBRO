package com.example.demoRBRO;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class DemoRbroApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoRbroApplication.class, args);
	}

}
