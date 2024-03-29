package ru.cdek.test.task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cdek.test.task.repository.JdbcCourierRepository;

import java.util.UUID;

/**
 * @author Aleksey Rud
 */
@Controller
@RequestMapping("/courier")
@RequiredArgsConstructor
public class CourierController {
private final JdbcCourierRepository jdbcCourierRepository;



	@GetMapping("/{id}")
	public ResponseEntity getTaskByIdCourier(@PathVariable UUID id){

		return ResponseEntity.ok().build();
	}
	@GetMapping()
	public String courier(){
		return "courier";
	}
}
