package ru.cdek.test.task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cdek.test.task.entity.Client;
import ru.cdek.test.task.repository.JdbcClientRepository;

import java.util.List;

/**
 * @author Aleksey Rud
 */
@Service
@RequiredArgsConstructor
public class ClientService {

	private final JdbcClientRepository jdbcClientRepository;

	public List<Client> findAll() {
		return jdbcClientRepository.findAll();
	}
}
