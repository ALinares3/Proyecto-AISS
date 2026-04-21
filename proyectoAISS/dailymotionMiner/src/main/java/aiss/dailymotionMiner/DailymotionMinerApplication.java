package aiss.dailymotionMiner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DailymotionMinerApplication {

	@Bean
	public RestTemplate builder(RestTemplateBuilder builder){
		return builder.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(DailymotionMinerApplication.class, args);
	}

}
