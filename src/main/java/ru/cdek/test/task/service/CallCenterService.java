package ru.cdek.test.task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cdek.test.task.entity.Client;
import ru.cdek.test.task.entity.Courier;
import ru.cdek.test.task.repository.JdbcClientRepository;
import ru.cdek.test.task.repository.JdbcCourierRepository;
import ru.cdek.test.task.utils.MyServerException;

/**
 * @author Aleksey Rud
 */
@Service
@RequiredArgsConstructor
public class CallCenterService {

	private final JdbcClientRepository jdbcClientRepository;
	private final JdbcCourierRepository jdbcCourierRepository;

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
	}
}
