package ru.hospital.utils.web;

public class Response {

	private String body;
	private String cookie;

	public Response(String body, String cookie){
		this.body = body;
		this.cookie = cookie;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
}
