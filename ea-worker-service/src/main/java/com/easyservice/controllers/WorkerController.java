package com.easyservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easyservice.exception.WorkerNotFoundException;
import com.easyservice.model.Worker;
import com.easyservice.service.IWorkerService;

@RestController
@RequestMapping("/work-service")
public class WorkerController {

	@Autowired
	IWorkerService workService;

	// http://localhost:8073/work-service/work/workerName/Ram
	@GetMapping("/work/workerName/{workerName}")
	Worker getByWorkerName(@PathVariable("workerName") String workerName) throws WorkerNotFoundException {
		return workService.getByWorkerName(workerName);
	}

	// http://localhost:8073/work-service/work/status/NA/workType/InteriorPainting
	@GetMapping("/work/status/{status}/workType/{workType}")
	Worker getByStatusAndWorkType(@PathVariable("status") String status, @PathVariable("workType") String workType)
			throws WorkerNotFoundException {
		return workService.getByStatusAndWorkType(status, workType);
	}

	// http://localhost:8073/work-service/work/workDuration/8/workType/InteriorPainting
	@GetMapping("/work/workDuration/{workDuration}/workType/{workType}")
	Worker getByDurationAndWorkType(@PathVariable("workDuration") int workDuration,
			@PathVariable("workType") String workType) throws WorkerNotFoundException {
		return workService.getByDurationAndWorkType(workDuration, workType);
	}

}
