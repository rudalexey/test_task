package ru.cdek.test.task.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Aleksey Rud
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskVB {
	private Long id; // номер заказа
	private String clientId; // Клиент
	private String courierId; // Назначенный курьер
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
	private LocalDateTime shipment; // Дата получения груза курьером
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
	private LocalDateTime receiving; // Дата доставки груза клиенту
	private String whoCreated; // имя оператора который согдал задачу
}
