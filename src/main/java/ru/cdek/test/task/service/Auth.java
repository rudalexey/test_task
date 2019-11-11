package ru.cdek.test.task.service;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.cdek.test.task.entity.Courier;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Aleksey Rud
 */

@Data
public class Auth {
	private Courier courier;

	public String getCourierName() {
		return courier.getName();
	}

	public boolean isAuthorize(){
		return Objects.nonNull(courier);
	}
}
