package ru.cdek.test.task.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.cdek.test.task.entity.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author Aleksey Rud
 */
@Repository
@RequiredArgsConstructor
public class JdbcTaskRepository implements MyRepository<Task, Long> {
	private final JdbcTemplate jdbcTemplate;

	@Override
	public int count() {
		return Optional.ofNullable(jdbcTemplate.queryForObject(
				"select count(id) from task", Integer.class)).orElse(0);
	}

	@Override
	public Long save(Task entity) {
		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getDataSource());
		UUID client = Objects.nonNull(entity.getClient()) ? entity.getClient().getId() : null;
		UUID courier = Objects.nonNull(entity.getCourier()) ? entity.getCourier().getId() : null;
		jdbcInsert.withTableName("task")
				.usingGeneratedKeyColumns("id")
				.usingColumns("client_id", "courier_id", "shipment", "receiving", "who_created");
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("client_id", client);
		parameters.put("courier_id", courier);
		parameters.put("shipment", Objects.nonNull(entity.getShipment()) ? Timestamp.valueOf(entity.getShipment()) : null);
		parameters.put("receiving", Objects.nonNull(entity.getReceiving()) ? Timestamp.valueOf(entity.getReceiving()) : null);
		parameters.put("who_created", entity.getWhoCreated());
		return jdbcInsert.executeAndReturnKey(parameters).longValue();
	}

	@Override
	public Number update(Task entity) throws DataAccessException {
		UUID client = Objects.nonNull(entity.getClient()) ? entity.getClient().getId() : null;
		UUID courier = Objects.nonNull(entity.getCourier()) ? entity.getCourier().getId() : null;
		return jdbcTemplate.update(
				"update task set client_id = ?, courier_id = ?, shipment= ?, receiving= ? , who_last_update= ? , date_update= ?  where id = ?",
				client,
				courier,
				Objects.nonNull(entity.getShipment()) ? Timestamp.valueOf(entity.getShipment()) : null,
				Objects.nonNull(entity.getReceiving()) ? Timestamp.valueOf(entity.getReceiving()) : null,
				entity.getWhoLastUpdate(),
				Timestamp.from(Instant.now()),entity.getId());

	}

	@Override
	public int deleteById(Long aLong) {
		return 0;
	}

	@Override
	public List<Task> findAll() {
		return jdbcTemplate.query(
				"select * from task",
				new TaskRowMapper()
		);
	}

	@Override
	public Optional<Task> findById(Long id) {
		return Optional.ofNullable(jdbcTemplate.queryForObject(
				"select * from task where id= ?",
				new Object[]{id}, new TaskRowMapper()

		));
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	private class TaskRowMapper implements RowMapper<Task> {
		@Override
		public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
			return Task.builder()
					.id(rs.getLong("id"))
					.client(jdbcTemplate.queryForObject(
							"select * from client where id= ?",
							new Object[]{UUID.fromString(rs.getString("client_id"))}, new ClientRowMapper()))
					.courier(Objects.nonNull(rs.getString("courier_id"))?jdbcTemplate.queryForObject(
							"select * from courier where id= ?",
							new Object[]{UUID.fromString(rs.getString("courier_id"))}, new CourierRowMapper()):null)
					.dateCreate(rs.getObject("date_created", LocalDateTime.class))
					.dateUpdate(rs.getObject("date_update", LocalDateTime.class))
					.whoLastUpdate(rs.getString("who_last_update"))
					.whoCreated(rs.getString("who_created"))
					.receiving(rs.getObject("receiving", LocalDateTime.class))
					.shipment(rs.getObject("shipment", LocalDateTime.class))
					.build();
		}
	}
}
