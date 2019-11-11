package ru.cdek.test.task.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.cdek.test.task.entity.Courier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Aleksey Rud
 */
@Repository
@RequiredArgsConstructor
public class JdbcCourierRepository implements MyRepository<Courier, UUID> {

	private final JdbcTemplate jdbcTemplate;
	@Override
	public int count() {
		return Optional.ofNullable(jdbcTemplate.queryForObject(
				"select count(1) from courier", Integer.class)).orElse(0);
	}

	@Override
	public Number save(Courier entity) {
		return jdbcTemplate.update(
				"insert into courier (id, name) values(?,?)",
				entity.getId(), entity.getName());
	}

	@Override
	public Number update(Courier entity) {
		return jdbcTemplate.update(
				"update courier set name = ? where id = ?",
				entity.getName(), entity.getId());
	}

	@Override
	public int deleteById(UUID uuid) {
		return jdbcTemplate.update(
				"delete courier where id = ?",
				uuid);
	}

	@Override
	public List<Courier> findAll() {
		return jdbcTemplate.query(
				"select * from courier",new CourierRowMapper()
		);
	}

	@Override
	public Optional<Courier> findById(UUID id) throws DataAccessException{
		if(Objects.isNull(id)) return Optional.empty();
		return Optional.ofNullable(jdbcTemplate.queryForObject(
				"select * from courier where id= ?",
				new Object[]{id},new CourierRowMapper())
		);
	}
	public Optional<Courier> findByName(String name) throws DataAccessException {
		return Optional.ofNullable(jdbcTemplate.queryForObject(
				"select * from courier where name= ?",
				new Object[]{name},new CourierRowMapper())
		);
	}
}
