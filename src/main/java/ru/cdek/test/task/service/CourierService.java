package ru.cdek.test.task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.cdek.test.task.entity.Courier;
import ru.cdek.test.task.repository.JdbcCourierRepository;
import ru.cdek.test.task.utils.MyServerException;

import java.util.List;

/**
 * @author Aleksey Rud
 */
@Service
@RequiredArgsConstructor
public class CourierService {
	private final JdbcCourierRepository jdbcCourierRepository;
	public Courier  getCourierByName(String name) throws MyServerException {
		try {
			return jdbcCourierRepository.findByName(name).orElseThrow(() -> new MyServerException(HttpStatus.INTERNAL_SERVER_ERROR));

		} catch (EmptyResultDataAccessException e){
			throw  new MyServerException(String.format("Курьер с именем %s не найден",name), HttpStatus.BAD_REQUEST);
		}
	}

	public List<Courier> findAll(){
		return jdbcCourierRepository.findAll();
	}
}
