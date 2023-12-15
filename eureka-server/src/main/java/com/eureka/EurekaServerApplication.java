package com.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.client.RestTemplate;



@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}

}
