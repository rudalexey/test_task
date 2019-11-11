package ru.cdek.test.task.repository;

import org.springframework.jdbc.core.RowMapper;
import ru.cdek.test.task.entity.Client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * @author Aleksey Rud
 */
public class ClientRowMapper implements RowMapper<Client> {
	@Override
	public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
		return Client.builder()
				.address(rs.getString("address"))
				.phoneNumber(rs.getString("phone_number"))
				.name(rs.getString("name"))
				.id(UUID.fromString(rs.getString("id")))
				.build();
	}
}
