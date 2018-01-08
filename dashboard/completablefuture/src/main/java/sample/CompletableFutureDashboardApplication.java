package sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@SpringBootApplication
public class CompletableFutureDashboardApplication {
	@Bean
	AsyncRestTemplate asyncRestTemplate(ThreadPoolTaskExecutor executor) {
		AsyncRestTemplate rest = new AsyncRestTemplate(executor);
		rest.setUriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:8081"));
		return rest;
	}

	@Bean
	ThreadPoolTaskExecutor taskExecutor() {
		int corePoolSize = 200;
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(corePoolSize);
		return executor;
	}

	public static void main(String[] args) {
		SpringApplication.run(CompletableFutureDashboardApplication.class, args);
	}
}
