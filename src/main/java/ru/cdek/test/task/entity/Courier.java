package ru.cdek.test.task.entity;

import lombok.*;

import java.util.Set;
import java.util.UUID;

/**
 * @author Aleksey Rud
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Courier {
	@Builder.Default
	private UUID id=UUID.randomUUID();
	private String name;
	private Set<Task> tasks;

}
