package ru.cdek.test.task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.cdek.test.task.entity.Client;
import ru.cdek.test.task.entity.Courier;
import ru.cdek.test.task.entity.Task;
import ru.cdek.test.task.entity.TaskVB;
import ru.cdek.test.task.repository.JdbcClientRepository;
import ru.cdek.test.task.repository.JdbcCourierRepository;
import ru.cdek.test.task.repository.JdbcTaskRepository;
import ru.cdek.test.task.utils.MyServerException;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Aleksey Rud
 */
@Service
@RequiredArgsConstructor
public class CallCenterService {

	private final JdbcClientRepository jdbcClientRepository;
	private final JdbcCourierRepository jdbcCourierRepository;
	private final JdbcTaskRepository jdbcTaskRepository;

	public void initDemoData() throws MyServerException {
		if (jdbcClientRepository.count() == 0) {
			jdbcClientRepository.save(Client.builder()
					.name("Клиент 1")
					.phoneNumber("+79000000000")
					.address("Адрес доставки 1")
					.build());
			jdbcClientRepository.save(Client.builder()
					.name("Клиент 2")
					.phoneNumber("+79000000022")
					.address("Адрес доставки 2")
					.build());
			jdbcClientRepository.save(Client.builder()
					.name("Клиент 3")
					.phoneNumber("+79000000033")
					.address("Адрес доставки 3")
					.build());
		}
		if (jdbcCourierRepository.count() == 0) {
			jdbcCourierRepository.save(Courier.builder().name("Курьер 1").build());
			jdbcCourierRepository.save(Courier.builder().name("Курьер 2").build());
			jdbcCourierRepository.save(Courier.builder().name("Курьер 3").build());
		}

		if (jdbcTaskRepository.count() == 0) {
			Courier courier=jdbcCourierRepository.findByName("Курьер 3").orElse(null);
			jdbcTaskRepository.getJdbcTemplate().update(
					"insert into task (client_id, courier_id, shipment,receiving,who_created,who_last_update,date_created,date_update) " +
							"values(?,?,?,?,?,?,?,?)",
					jdbcClientRepository.findByName("Клиент 1").orElse(null).getId(),
					courier!=null?courier.getId():null,
					Timestamp.valueOf(LocalDateTime.of(2019,11,1,19,0)),
					Timestamp.valueOf(LocalDateTime.of(2019,11,1,9,0)),
					"init", "Курьер 3",
					Timestamp.valueOf(LocalDateTime.of(2019,11,1,8,10)),
					Timestamp.valueOf(LocalDateTime.of(2019,11,1,19,10)));
			jdbcTaskRepository.getJdbcTemplate().update(
					"insert into task (client_id, courier_id, shipment,receiving,who_created,who_last_update,date_created,date_update) " +
							"values(?,?,?,?,?,?,?,?)",
					jdbcClientRepository.findByName("Клиент 2").orElse(null).getId(),
					courier!=null?courier.getId():null,
					Timestamp.valueOf(LocalDateTime.of(2019,11,2,19,0)),
					null,
					"init", null,
					Timestamp.valueOf(LocalDateTime.of(2019,11,2,8,0)),
					Timestamp.valueOf(LocalDateTime.of(2019,11,2,19,10)));
		}
	}

	public List<Task> findAll() {
		return jdbcTaskRepository.findAll();
	}

	public void addNewTask(TaskVB taskFormObject) {
		jdbcTaskRepository.save(Task.builder()
				.client(jdbcClientRepository.findById(safeCast(taskFormObject.getClientId())).orElse(null))
				.courier(jdbcCourierRepository.findById(safeCast(taskFormObject.getCourierId())).orElse(null))
				.shipment(taskFormObject.getShipment())
				.receiving(taskFormObject.getReceiving())
				.whoCreated("web user")
				.build());
	}
	private UUID safeCast(String s){
		if(Objects.isNull(s)||s.equals("null")) return null;
		return UUID.fromString(s);
	}
}
