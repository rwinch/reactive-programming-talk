package sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sample.user.InMemoryUserService;
import sample.user.User;

@SpringBootApplication
public class NettyApisApplication {
	@Bean
	InMemoryUserService userService() {
		User rob = new User(1L, "Rob");
		User rossen = new User(100L, "Rossen");
		return new InMemoryUserService(rob, rossen);
	}

	public static void main(String[] args) {
		SpringApplication.run(NettyApisApplication.class, args);
	}
}
