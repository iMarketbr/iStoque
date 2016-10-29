package br.com.imarket.istoque.home;

import org.springframework.composed.web.Get;
import org.springframework.stereotype.Controller;

@Controller
public class HomeController {

	@Get("/home")
	public String index() {
		return "home/index";
	}
}
