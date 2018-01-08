package sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@SpringBootApplication
public class RestTemplateDashboardApplication {
	@Bean
	RestTemplate restTemplate() {
		RestTemplate rest = new RestTemplate();
		rest.setUriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:8081"));
		return rest;
	}

	public static void main(String[] args) {
		SpringApplication.run(RestTemplateDashboardApplication.class, args);
	}
}
