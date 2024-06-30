package com.unir.store_core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class StoreCoreApplication {

  @LoadBalanced
  @Bean
  public RestTemplate restTemplate() {
     return new RestTemplate();
  }

	public static void main(String[] args) {
		SpringApplication.run(StoreCoreApplication.class, args);
	}

}
