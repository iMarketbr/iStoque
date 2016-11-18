package br.com.imarket.istoque.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.composed.web.Get;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository repository;

	@Get("/users")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("user/list");
		mav.addObject("users", repository.findAll());
		
		return mav;
	}
}
