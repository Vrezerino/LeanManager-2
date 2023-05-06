package leanmanager2;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

	@GetMapping("/")
	public String handleDefault() {
		return "redirect:/assets";
	}

	@GetMapping("/health")
	public ResponseEntity<Object> healthCheck() {
		return ResponseEntity.ok().build();
	}
}
