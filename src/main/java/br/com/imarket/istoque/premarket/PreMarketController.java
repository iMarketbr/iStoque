package br.com.imarket.istoque.premarket;

import org.springframework.composed.web.Get;
import org.springframework.stereotype.Controller;

@Controller
public class PreMarketController {

	@Get("/premarkets")
	public String list() {
		return "premarket/list";
	}
}
