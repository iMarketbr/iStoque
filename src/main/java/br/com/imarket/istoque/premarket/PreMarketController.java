package br.com.imarket.istoque.premarket;

import static br.com.imarket.istoque.api.IMarketEndpoint.PREMARKETS;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.composed.web.Get;
import org.springframework.composed.web.Post;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import br.com.imarket.istoque.api.APICallback;
import br.com.imarket.istoque.api.IMarketAPI;

@Controller
public class PreMarketController {
	
	@Autowired
	private IMarketAPI<PreMarket> api;

	@Get("/premarkets")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView("premarket/list");
		
		List<PreMarket> preMarkets = api.get(PREMARKETS, PreMarket[].class);
		
		mav.addObject("preMarkets", preMarkets);
		
		mav.addObject("preMarketFromView", new PreMarketFromView());
		return mav;
	}
	
	@Post("/premarkets")
	public ModelAndView change(@ModelAttribute PreMarketFromView preMarketFromView) {
		ModelAndView mav = new ModelAndView("redirect:/premarkets");
		
		api.post(PREMARKETS + preMarketFromView.getId(), preMarketFromView, new APICallback() {
			
			@Override
			public void success() {
				
			}
			
			@Override
			public void error() {
				
			}
		});
		
		return mav;
	}
}
