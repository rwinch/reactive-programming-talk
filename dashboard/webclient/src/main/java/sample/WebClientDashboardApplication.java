package sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestOperations;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

@SpringBootApplication
public class WebClientDashboardApplication {
	@Bean
	AsyncRestTemplate asyncRestTemplate(ThreadPoolTaskExecutor executor) {
		AsyncRestTemplate rest = new AsyncRestTemplate(executor);
		rest.setUriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:8081"));
		return rest;
	}

	@Bean
	ThreadPoolTaskExecutor executor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(10);
		return executor;
	}

	@Bean
	RestOperations restTemplate(AsyncRestTemplate asyncRestTemplate) {
		return asyncRestTemplate.getRestOperations();
	}

	@Bean
	WebClient webClient() {
		return WebClient.builder()
				.baseUrl("http://localhost:8081")
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(WebClientDashboardApplication.class, args);
	}
}
