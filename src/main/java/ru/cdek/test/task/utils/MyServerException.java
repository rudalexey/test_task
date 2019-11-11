package ru.cdek.test.task.utils;

import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.Map;

/**
 * @author Aleksey Rud
 */
public class MyServerException extends Exception {
	private HttpStatus code;

	public MyServerException(String message) {
		super(message);
		this.code = HttpStatus.BAD_REQUEST;
	}
	public MyServerException(HttpStatus code) {
		this.code = code;
	}
	public MyServerException(String message, HttpStatus code) {
		super(message);
		this.code = code;
	}

	public HttpStatus getCode() {
		return code;
	}

	public Map<String, String> getFormatMessage() {
		return Collections.singletonMap("messages",super.getMessage());
	}
}
