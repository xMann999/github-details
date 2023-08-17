package com.sergiuszg.githubdetailsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableFeignClients
public class GithubDetailsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GithubDetailsServiceApplication.class, args);
	}
}
