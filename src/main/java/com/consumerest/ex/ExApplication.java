package com.consumerest.ex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ExApplication {

	// Creating Logger to display the input in the log
	public static final Logger log= LoggerFactory.getLogger(ExApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ExApplication.class, args);
	}

	//RestTemplate uses Jackson JSON library to process incoming data
	@Bean
  	public RestTemplate restTemplate(RestTemplateBuilder builder){
		return builder.build();
  }

  //CommandLineRunner runs RestTemplate on startup
 	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			Quote quote= restTemplate.getForObject("https://quoters.apps.pcfone.io/api/random", Quote.class);
			log.info(quote.toString());
		};
	}

}
