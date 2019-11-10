package ru.cdek.test.task.repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Aleksey Rud
 */
public interface MyRepository<T,ID> {
	int count();
	int save(T entity);
	int update(T entity);
	int deleteById(ID id);
	List<T> findAll();
	Optional<T> findById(ID id);
}
