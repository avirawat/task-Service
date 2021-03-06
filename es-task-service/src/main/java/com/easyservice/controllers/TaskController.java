package com.easyservice.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easyservice.exception.TaskNotFoundException;
import com.easyservice.exception.WorkerNotFoundException;
import com.easyservice.model.Status;
import com.easyservice.model.Task;
import com.easyservice.model.Worker;
import com.easyservice.service.ITaskService;

@RestController
@RequestMapping("/task-service")
public class TaskController {

	@Autowired
	ITaskService taskService;

	// http://localhost:8072/task-service/task
	@PostMapping("/task")
	Task addTask(@RequestBody Task task) {
		Task taskAdded = taskService.addTask(task);
		return taskAdded;
	}

	// http://localhost:8072/task-service/task/taskId/205
	@DeleteMapping("/task/taskId/{taskId}")
	String deleteTask(@PathVariable("taskId") int taskId) {
		taskService.deleteTask(taskId);
		return "Value Deleted";
	}

	// http://localhost:8072/task-service/task
	@PutMapping("/task")
	String updateTask(@RequestBody Task task) {
		taskService.updateTask(task);
		return "Value updated";
	}

	// http://localhost:8072/task-service/task/taskId/200
	@GetMapping("/task/taskId/{taskId}")
	Task getById(@PathVariable("taskId") int taskId) throws TaskNotFoundException {
		return taskService.getById(taskId);
	}

	// http://localhost:8072/task-service/task
	@GetMapping("/task")
	List<Task> getAllTask() {
		return taskService.getAllTask();
	}

	// http://localhost:8072/task-service/task/taskId/200/workerId/300
	@GetMapping("/task/taskId/{taskId}/workerId/{workerId}")
	String assignTaskToworker(@PathVariable("taskId") int taskId, @PathVariable("workerId") int workerId)
			throws WorkerNotFoundException {
		taskService.assignTaskToWorker(taskId, workerId);
		return "work Assigned";
	}

	// http://localhost:8072/task-service/task/maintenanceId/100
	@GetMapping("/task/maintenanceId/{maintenanceId}")
	Task getByMaintenanceId(@PathVariable("maintenanceId") int maintenanceId) throws TaskNotFoundException {
		return taskService.getByMaintenanceId(maintenanceId);
	}

	// http://localhost:8072/task-service/task/organiser/Avi/status/INPROGRESS
	@GetMapping("/task/organiser/{organiser}/status/{status}")
	Task getByOrganiserAndStatus(@PathVariable("organiser") String organiser, @PathVariable("status") String status) {
		// Status statusCon= Status.valueOf(status);
		return taskService.getByOrganiserAndStatus(organiser, status);
	}

	// http://localhost:8072/task-service/task/startDate/2021-10-11/endDate/2021-11-10
	@GetMapping("/task/startDate/{startDate}/endDate/{endDate}")
	List<Task> getByTaskStartDateAndEndDate(@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate) {
		LocalDate sDate = LocalDate.parse(startDate);
		LocalDate eDate = LocalDate.parse(endDate);
		return taskService.getByTaskStartDateAndEndDate(sDate, eDate);
	}

	// ************************worker Part***************************************************

	//http://localhost:8072/task-service/task/workerName/Ram
	@GetMapping("/task/workerName/{workerName}")
	Worker getByWorkerName(@PathVariable("workerName") String workerName) throws WorkerNotFoundException {
		return taskService.getByWorkerName(workerName);
	}
	
	//http://localhost:8072/task-service/task/status/NA/workType/InteriorPainting
	@GetMapping("/task/status/{status}/workType/{workType}")
	Worker getByStatusAndWorkType(@PathVariable("status") String status, @PathVariable("workType") String workType)
			throws WorkerNotFoundException {
		return taskService.getByStatusAndWorkType(status, workType);
	}

	//http://localhost:8072/task-service/task/workDuration/8/workType/InteriorPainting
	@GetMapping("/task/workDuration/{workDuration}/workType/{workType}")
	Worker getByDurationAndWorkType(@PathVariable("workDuration") int workDuration,
			@PathVariable("workType") String workType) throws WorkerNotFoundException {
		return taskService.getByDurationAndWorkType(workDuration, workType);
	}
}
