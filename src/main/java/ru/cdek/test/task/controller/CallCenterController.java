package ru.cdek.test.task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cdek.test.task.service.CallCenterService;
import ru.cdek.test.task.utils.MyServerException;

/**
 * @author Aleksey Rud
 */
@RestController
@RequestMapping("/call")
@RequiredArgsConstructor
public class CallCenterController{
private final CallCenterService callCenterService;


	@GetMapping("/init")
	public ResponseEntity initDemoData() {
		try  {
			callCenterService.initDemoData();
		}catch (MyServerException ex){
			return ResponseEntity.status(ex.getCode()).body(ex.getFormatMessage());
		}
		return ResponseEntity.ok().build();
	}
}
