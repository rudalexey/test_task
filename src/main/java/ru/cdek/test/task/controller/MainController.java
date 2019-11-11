package ru.cdek.test.task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.cdek.test.task.entity.Courier;
import ru.cdek.test.task.entity.FormObject;
import ru.cdek.test.task.service.Auth;
import ru.cdek.test.task.service.CourierService;
import ru.cdek.test.task.utils.MyServerException;

/**
 * @author Aleksey Rud
 */
@Controller
@RequiredArgsConstructor
public class MainController {
	private final Auth auth;
	private final CourierService courierService;

	@GetMapping(value = "/")
	public String index() {
		return "/home";

	}
	@GetMapping("/login")
	public String login() {
		return auth.isAuthorize() ? "/courier" : "/login";
	}
	@GetMapping("/logout")
	public String logout() {
		auth.setCourier(null);
		return "/home";
	}
	@GetMapping("/home")
	public String home() {
		return "/home";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute FormObject<String> form, Model model) {
		Courier courier;
		try {
			courier = courierService.getCourierByName(form.getObj());
		} catch (MyServerException e) {
			model.addAttribute("error", e.getMessage());
			return "login";
		}
		auth.setCourier(courier);
		return "courier";
	}

	@GetMapping("/403")
	public String error403() {
		return "/error/403";
	}
}
