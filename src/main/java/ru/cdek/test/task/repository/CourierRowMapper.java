package ru.cdek.test.task.repository;

import org.springframework.jdbc.core.RowMapper;
import ru.cdek.test.task.entity.Courier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * @author Aleksey Rud
 */
public class CourierRowMapper implements RowMapper<Courier> {
	@Override
	public Courier mapRow(ResultSet rs, int rowNum) throws SQLException {
		return Courier.builder()
				.name(rs.getString("name"))
				.id(UUID.fromString(rs.getString("id")))
				.build();
	}
}
