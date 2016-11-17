package br.com.imarket.istoque.user;

import org.springframework.composed.web.Get;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

	@Get("/users")
	public String index() {
		return "user/list";
	}
}
