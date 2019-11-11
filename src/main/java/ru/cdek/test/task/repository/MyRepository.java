package ru.cdek.test.task.repository;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

/**
 * @author Aleksey Rud
 */
public interface MyRepository<T,ID> {
	int count();
	Object save(T entity);
	Number update(T entity);
	int deleteById(ID id);
	List<T> findAll();
	Optional<T> findById(ID id);
}
