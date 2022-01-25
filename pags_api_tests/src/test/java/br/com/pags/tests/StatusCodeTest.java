package br.com.pags.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.pags.utils.Utils;

public class StatusCodeTest {
	private static RestTemplate template;
	private static String url;
	private static StatusCodeEntity statusCodeEntity;

	@BeforeAll
	static void setup() throws JsonParseException, JsonMappingException, IOException {
		statusCodeEntity = (StatusCodeEntity) Utils.getObjectFromJsonFile("./support/data_provider.json",
				StatusCodeEntity.class);
		template = new RestTemplate();
		url = System.getProperty("url");
	}

	static String getSuccessCodeParam() {
		return statusCodeEntity.getSuccessStatusCode();
	}

	static String getFailureCodeParam() {
		return statusCodeEntity.getFailureStatusCode();
	}

	private static Stream<Arguments> getHttpCodeValues() {
		return Stream.of(
				Arguments.of(HttpMethod.POST, getSuccessCodeParam(),
						String.format("POST with HTTP status code %s response", getSuccessCodeParam())),
				Arguments.of(HttpMethod.POST, getFailureCodeParam(),
						String.format("POST with HTTP status code %s response", getFailureCodeParam())),
				Arguments.of(HttpMethod.PUT, getSuccessCodeParam(),
						String.format("PUT with HTTP status code %s response", getSuccessCodeParam())),
				Arguments.of(HttpMethod.PUT, getFailureCodeParam(),
						String.format("PUT with HTTP status code %s response", getFailureCodeParam())),
				Arguments.of(HttpMethod.GET, getSuccessCodeParam(),
						String.format("GET with HTTP status code %s response", getSuccessCodeParam())),
				Arguments.of(HttpMethod.GET, getFailureCodeParam(),
						String.format("GET with HTTP status code %s response", getFailureCodeParam())),
				Arguments.of(HttpMethod.DELETE, getSuccessCodeParam(),
						String.format("DELETE with HTTP status code %s response", getSuccessCodeParam())),
				Arguments.of(HttpMethod.DELETE, getFailureCodeParam(),
						String.format("DELETE with HTTP status code %s response", getFailureCodeParam())));
	}

	@DisplayName("When consuming an endpoint, its response should be the code passed in the requested url.")
	@ParameterizedTest(name = "{index} => method={0}, code={1}, description={2}")
	@MethodSource("getHttpCodeValues")
	public void testRequisition(HttpMethod method, String code, String description) {
		try {
			System.out.println(url);
			var response = template.exchange(url + code, method, null, String.class);
			assertTrue(response.getStatusCodeValue() == Integer.valueOf(code));
		} catch (HttpServerErrorException | HttpClientErrorException e) {
			assertTrue(e.getStatusCode().value() == Integer.valueOf(code));
		}
	}
}
