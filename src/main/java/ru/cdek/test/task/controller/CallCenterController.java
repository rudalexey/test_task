package ru.cdek.test.task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import ru.cdek.test.task.entity.Task;
import ru.cdek.test.task.entity.TaskVB;
import ru.cdek.test.task.service.CallCenterService;
import ru.cdek.test.task.service.ClientService;
import ru.cdek.test.task.service.CourierService;
import ru.cdek.test.task.utils.MyServerException;

import java.util.Objects;

/**
 * @author Aleksey Rud
 */
@Controller
@RequestMapping("/call")
@RequiredArgsConstructor
public class CallCenterController{
private final CallCenterService callCenterService;
private final CourierService courierService;
	private final ClientService clientService;
	@GetMapping("/init")
	public ResponseEntity initDemoData() {
		try  {
			callCenterService.initDemoData();
		}catch (MyServerException ex){
			return ResponseEntity.status(ex.getCode()).body(ex.getFormatMessage());
		}
		return ResponseEntity.ok().build();
	}
	@GetMapping()
	public String call(Model model){
		model.addAttribute("clients", clientService.findAll());
		model.addAttribute("couriers", courierService.findAll());
		model.addAttribute("tasks", callCenterService.findAll());
		return "/call";
	}

	@PostMapping("/edit")
	public RedirectView edit(@ModelAttribute TaskVB taskFormObject, Model model, RedirectAttributes redirectAttributes) {
		if(Objects.nonNull(taskFormObject)){
			if(taskFormObject.getId()==null){
				callCenterService.addNewTask(taskFormObject);
			}

		}

		return new RedirectView("/call");
	}

}
