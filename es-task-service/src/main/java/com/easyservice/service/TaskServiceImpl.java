package com.easyservice.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.easyservice.exception.TaskNotFoundException;
import com.easyservice.exception.WorkerNotFoundException;
import com.easyservice.model.Status;
import com.easyservice.model.Task;
import com.easyservice.model.Worker;
import com.easyservice.repository.ITaskRepository;

@Service
@Transactional
public class TaskServiceImpl implements ITaskService {

	@Autowired
	RestTemplate restTemplate;

	private static final String BASEURL = "http://localhost:8073/work-service/work";

	@Autowired
	ITaskRepository taskRepository;

	@Override
	public Task addTask(Task task) {
		return taskRepository.save(task);

	}

	@Override
	public void deleteTask(int taskId) {
		taskRepository.deleteById(taskId);

	}

	@Override
	public void updateTask(Task task) {
		taskRepository.save(task);

	}

	@Override
	public Task getById(int taskId) {
		return taskRepository.findById(taskId).get();
	}

	@Override
	public List<Task> getAllTask() {
		return taskRepository.findAll();
	}

	@Override
	public void assignTaskToWorker(int taskId, int workerId) throws WorkerNotFoundException {
		taskRepository.updateStatus("NA", workerId);
		taskRepository.assignTask(taskId, workerId);

	}

	@Override
	public Task getByMaintenanceId(int maintenanceId) throws TaskNotFoundException {
		return taskRepository.findByMaintenanceId(maintenanceId);

	}

	@Override
	public Task getByOrganiserAndStatus(String organiser, String status) {
		Status statusCon = Status.valueOf(status);
		return taskRepository.findByOrganiserAndTStatus(organiser, statusCon);
	}

	@Override
	public List<Task> getByTaskStartDateAndEndDate(LocalDate startDate, LocalDate endDate) {
		return taskRepository.findByTaskStartDateAndEndDate(startDate, endDate);
	}

	@Override
	public int updateStatus(String value, int workerId) {
		return taskRepository.updateStatus(value, workerId);
	}
	
	
	///work/workerName/{workerName}
	@Override
	public Worker getByWorkerName(String workerName) throws WorkerNotFoundException {
		String url = BASEURL + "/workerName/" + workerName;
		return restTemplate.getForObject(url, Worker.class);
	}

	@Override
	public Worker getByStatusAndWorkType(String status, String workType) throws WorkerNotFoundException {
		String url = BASEURL + "/status/" + status + "/workType/" + workType;
		return restTemplate.getForObject(url, Worker.class);
	}

	@Override
	public Worker getByDurationAndWorkType(int workDuration, String workType) throws WorkerNotFoundException {
		String url = BASEURL + "/workDuration/" + workDuration + "/workType/" + workType;
		return restTemplate.getForObject(url, Worker.class);
	}

}
