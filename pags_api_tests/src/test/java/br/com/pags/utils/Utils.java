package br.com.pags.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
	public static ObjectMapper mapper = new ObjectMapper();
	
	public static <T> Object getObjectFromJsonFile(String file, Class<T> clasz) throws JsonParseException, JsonMappingException, IOException  {
		var objetoComoJson = getJsonFromFile(file);
		
		return mapper.readValue(objetoComoJson, clasz);		
	} 
	
	public static String getJsonFromFile(String caminho) throws JsonParseException, JsonMappingException, IOException  {
		var classLoader = ClassLoader.getSystemClassLoader();
		var in = classLoader.getResource(caminho);
		var jsonNode = mapper.readValue(in, JsonNode.class);
		var jsonString = mapper.writeValueAsString(jsonNode);
		
		return jsonString;
	}
}
