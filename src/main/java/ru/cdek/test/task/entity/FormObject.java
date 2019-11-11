package ru.cdek.test.task.entity;

import lombok.Data;

/**
 * @author Aleksey Rud
 */
@Data
public class FormObject<T> {
	T obj;
	String error;
}
