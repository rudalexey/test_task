package ru.cdek.test.task.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Aleksey Rud
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {
	private Long id; // номер заказа
	private Client client; // Клиент
	private Courier courier; // Назначенный курьер
	private LocalDateTime shipment; // Дата получения груза курьером
	private LocalDateTime receiving; // Дата доставки груза клиенту
	private String whoCreated; // имя оператора который согдал задачу
	private String whoLastUpdate; // Имя оператора который поледний ее обновил
	private LocalDateTime dateCreate; // Время согдания задачи
	private LocalDateTime dateUpdate; // Время обновления задачи

}
