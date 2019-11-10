package ru.cdek.test.task.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author Aleksey Rud
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client {
	@Builder.Default
	private UUID id=UUID.randomUUID();
	private String name;
	private String phoneNumber;
	private String address;
}
