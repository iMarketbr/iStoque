package br.com.imarket.istoque.user.settings;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.composed.web.Get;
import org.springframework.composed.web.Post;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import br.com.imarket.istoque.user.LoggedUser;
import br.com.imarket.istoque.user.User;
import br.com.imarket.istoque.user.UserRepository;

@Controller
public class SettingsController {
	
	@Autowired
	private LoggedUser loggedUser;
	@Autowired
	private UserRepository userRepository;

	@Get("/settings")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("settings/index");
		
		return mav;
	}
	
	@Get("/settings/change-password")
	public ModelAndView showChangePassword() {
		ModelAndView mav = new ModelAndView("settings/changePassword");
		mav.addObject("user", loggedUser.get());
		return mav;
	}
	
	@Post("/settings/change-password")
	public ModelAndView changePassword(@NotBlank String newPassword, @NotBlank String confirmNewPassword) {
		ModelAndView mav = new ModelAndView("redirect:/settings");
		if (!newPassword.equals(confirmNewPassword)) {
			return mav;
		}
		
		User user = loggedUser.get();
		user.hashPassword(newPassword);
		userRepository.save(user);
		return mav;
	}
}
