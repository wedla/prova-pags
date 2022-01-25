package br.com.pags.utils;

public enum PathEnum {
	STATUS_CODE("https://httpbin.org/status/");
	
	private String value;
	
	private PathEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
