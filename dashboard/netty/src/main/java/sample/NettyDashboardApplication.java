package sample;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.web.reactive.function.client.WebClient;
import sample.message.Message;

@SpringBootApplication
public class NettyDashboardApplication {

	@Bean
	WebClient webClient() {
		return WebClient.builder()
				.baseUrl("http://localhost:8081")
				.build();
	}

	@Bean
	public CommandLineRunner initData(MongoOperations mongo) {
		return (String... args) ->
				mongo.createCollection(Message.class, CollectionOptions.empty().size(1000000).capped());
	}

	public static void main(String[] args) {
		SpringApplication.run(NettyDashboardApplication.class, args);
	}
}
