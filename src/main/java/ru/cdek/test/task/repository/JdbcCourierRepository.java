package ru.cdek.test.task.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.cdek.test.task.entity.Courier;

import java.util.List;
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
	public int save(Courier entity) {
		return jdbcTemplate.update(
				"insert into courier (id, name) values(?,?)",
				entity.getId(), entity.getName());
	}

	@Override
	public int update(Courier entity) {
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
				"select * from courier",
				(rs, rowNum) ->
						Courier.builder()
								.id(UUID.fromString(rs.getString("id")))
								.name(rs.getString("name"))
								.build()
		);
	}

	@Override
	public Optional<Courier> findById(UUID id) {
		return jdbcTemplate.queryForObject(
				"select * from courier where id= ?",
				new Object[]{id},
				(rs, rowNum) ->
						Optional.of(Courier.builder()
								.id(UUID.fromString(rs.getString("id")))
								.name(rs.getString("name"))
								.build())
		);
	}
}
