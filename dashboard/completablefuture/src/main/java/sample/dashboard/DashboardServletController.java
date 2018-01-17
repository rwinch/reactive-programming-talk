package sample.dashboard;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.AsyncRestOperations;
import sample.message.Message;
import sample.user.User;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.CompletableFuture;

/**
 * @author Rob Winch
 */
public class DashboardServletController {
	ObjectMapper mapper = new ObjectMapper();

	final AsyncRestOperations rest;

	public DashboardServletController(AsyncRestOperations rest) {
		this.rest = rest;
	}

	@GetMapping("/doGet")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		AsyncContext asyncContext = request.startAsync();
		String idParam = request.getParameter("id");
		Long id = Long.parseLong(idParam);
		getDashboard(id).thenAccept( dashboard -> {
			try {
				this.mapper.writeValue(out, dashboard); // Blocking write
			}
			catch (IOException e) {
				throw new RuntimeException(e);
			} finally {
				asyncContext.complete();
			}
		});
	}

	private CompletableFuture<Dashboard> getDashboard(Long id ) {
		CompletableFuture<ResponseEntity<User>> user = this.rest.getForEntity("/users/{id}", User.class, id).completable();
		return user.thenCompose(userResponseEntity -> {
			User u = userResponseEntity.getBody();
			return this.rest.getForEntity("/messages/{username}", Message.class, u.getName())
					.completable().thenApply(messageResponseEntity -> new Dashboard(u,
							messageResponseEntity.getBody()));
		});
	}
}
