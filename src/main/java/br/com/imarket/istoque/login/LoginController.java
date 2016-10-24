package br.com.imarket.istoque.login;

import org.springframework.composed.web.Get;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController {

	@Get("/")
	public String index() {
		return "login/index";
	}
}
