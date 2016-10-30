package br.com.imarket.istoque.api;

import static java.util.Arrays.asList;
import static org.springframework.http.HttpMethod.GET;

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

@Component
public class IMarketAPI<T> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(IMarketAPI.class);
	
	@Autowired
	private HttpHeaders headers;
	@Autowired
	private RestOperations restOperations;
	
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
	
}
