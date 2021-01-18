package com.sensha4u.springboot.camel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.sensha4u.springboot.camel")
public class SampleCamelApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(SampleCamelApplication.class, args);
	}
}