package br.com.imarket.istoque.api;

import static java.util.Arrays.asList;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class IMarketAPI<T> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(IMarketAPI.class);
	
	@Autowired
	private HttpHeaders headers;
	@Autowired
	private RestOperations restOperations;
	@Autowired
	private ObjectMapper mapper;
	
	@Value("${api.endpoint}")
	private String baseEndpoint;

	public List<T> get(String endpoint, Class<T[]> clazz) {
		
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		
		try {
			ResponseEntity<T[]> response = restOperations.exchange(baseEndpoint + endpoint, GET, entity, clazz);

			if (response.getStatusCode().is2xxSuccessful()) {
				return asList(response.getBody());
			}
		} catch (Exception e) {
			LOGGER.error("Cannot request GET from api", e);
			return Collections.emptyList();
		}
		return Collections.emptyList();
	}
	
	public void post(String endpoint, Object object, APICallback callback) {
		
		String json = null;
		try {
			json = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			LOGGER.error("Error serializing POST request body", e);
			callback.error();
		}
		
		HttpEntity<String> entity = new HttpEntity<>(json, headers);
		
		try {
			ResponseEntity<Void> response = restOperations.exchange(baseEndpoint + endpoint, POST, entity, Void.class);

			if (response.getStatusCode().is2xxSuccessful()) {
				callback.success();
			}
		} catch (Exception e) {
			LOGGER.error("Cannot request POST from api", e);
			callback.error();
		}
	}
	
}
