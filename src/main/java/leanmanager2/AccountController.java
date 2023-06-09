package leanmanager2;

//import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AccountController {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	/* 
	@PostConstruct
	private void addTwo() {
		accountRepository.save(new Account("Patrick", passwordEncoder.encode("p1p1")));
		accountRepository.save(new Account("Miina", passwordEncoder.encode("p1p1")));
	}
	*/

	@GetMapping("/accounts")
	public String list(Model model) {
		model.addAttribute("accounts", accountRepository.findAll());
		return "accounts";
	}

	@PostMapping("/accounts")
	public String add(RedirectAttributes rda, @RequestParam String username, @RequestParam String password) {
		if (accountRepository.findByUsername(username) != null) {
			rda.addFlashAttribute("message", "Username already exists!");
			return "redirect:/accounts";
		}

		Account a = new Account(username, passwordEncoder.encode(password));
		accountRepository.save(a);
		return "redirect:/accounts";
	}

}
