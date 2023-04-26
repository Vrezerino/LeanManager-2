package leanmanager2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

@Controller
public class AssetController {

	@Autowired
	private AssetService assetService;

	@Autowired
	private AccountRepository accountRepository;

	@GetMapping("/assets")
	public String view(Model model) {
		model.addAttribute("assets", assetService.findAll());
		return "assets";
	}

	@PostMapping("/assets")
	public String add(Asset asset, BindingResult result, RedirectAttributes rda) {
		if (result.hasErrors()) {
			rda.addFlashAttribute("errors", "Check that you filled all fields correctly.");
			return "redirect:/assets";
		} 

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		asset.setDate(dtf.format(now));

		asset.setUser(accountRepository.findByUsername(username));
		assetService.save(asset);

		return "redirect:/assets";
	}

	@DeleteMapping("/assets")
	// Inventory managers can delete any asset, not just their own.
	public String delete(@PathVariable("id") Long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.isAuthenticated()) assetService.deleteById(id);
		return "redirect:/assets";
	}
}
