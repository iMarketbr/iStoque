package br.com.imarket.istoque.configuration;

import static java.util.Arrays.asList;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Configuration
class RestConfiguration {
	
	@Value("${api.username}")
	private String username;
	@Value("${api.password}")
	private String password;

	@Bean
	RestOperations jsonRestOperations() {
		HttpComponentsClientHttpRequestFactory restFactory = new HttpComponentsClientHttpRequestFactory();
		restFactory.setReadTimeout(20000);
		restFactory.setConnectTimeout(20000);

		RestTemplate restTemplate = new RestTemplate(restFactory);
		List<HttpMessageConverter<?>> converters = new ArrayList<>();

		converters.add(new MappingJackson2HttpMessageConverter());

		restTemplate.setMessageConverters(converters);

		return restTemplate;
	}
	
	@Bean
	HttpHeaders httpHeaders() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(APPLICATION_JSON);
		httpHeaders.setAccept(asList(APPLICATION_JSON));

		String auth = username + ":" + password;
		byte[] encodedAuth = Base64.encode(auth.getBytes(Charset.forName("US-ASCII")));
		String authHeader = "Basic " + new String(encodedAuth);
		httpHeaders.set(AUTHORIZATION, authHeader);
		
		return httpHeaders;
	}

}