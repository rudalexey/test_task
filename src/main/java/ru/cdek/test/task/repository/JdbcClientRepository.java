package ru.cdek.test.task.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.cdek.test.task.entity.Client;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Aleksey Rud
 */
@RequiredArgsConstructor
@Repository
public class JdbcClientRepository implements MyRepository<Client, UUID> {

	private final JdbcTemplate jdbcTemplate;
	@Override
	public int count() {
		return Optional.ofNullable(jdbcTemplate.queryForObject(
				"select count(id) from client", Integer.class)).orElse(0);
	}
	@Override
	public int save(Client entity) {
		return jdbcTemplate.update(
				"insert into client (id, name, phone_number, address) values(?,?,?,?)",
				entity.getId(), entity.getName(), entity.getPhoneNumber(), entity.getAddress());
	}

	@Override
	public int update(Client entity) {
		return jdbcTemplate.update(
				"update client set name = ?, phone_number = ?, address= ? where id = ?",
				entity.getName(), entity.getPhoneNumber(), entity.getAddress(), entity.getId());
	}

	@Override
	public int deleteById(UUID uuid) {
		return jdbcTemplate.update(
				"delete client where id = ?",
				uuid);
	}

	@Override
	public List<Client> findAll() {
		return jdbcTemplate.query(
				"select * from client",
				(rs, rowNum) ->
						Client.builder()
								.id(UUID.fromString(rs.getString("id")))
								.name(rs.getString("name"))
								.phoneNumber(rs.getString("phone_number"))
								.address(rs.getString("address"))
								.build()
		);
	}

	@Override
	public Optional<Client> findById(UUID id) {
		return jdbcTemplate.queryForObject(
				"select * from client where id= ?",
				new Object[]{id},
				(rs, rowNum) ->
						Optional.of(Client.builder()
								.id(UUID.fromString(rs.getString("id")))
								.name(rs.getString("name"))
								.phoneNumber(rs.getString("phone_number"))
								.address(rs.getString("address"))
								.build())
		);
	}
}
