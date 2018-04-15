package com.sakinramazan.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Yapmış olduğunuz projenin base package ismini belirtiyoruz.
@SpringBootApplication(scanBasePackages = { "com.sakinramazan.springboot" })
public class SpringBootRestApiApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestApiApp.class, args);
	}
}
