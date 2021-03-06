package com.easyservice.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.easyservice.exception.TaskNotFoundException;
import com.easyservice.exception.WorkerNotFoundException;
import com.easyservice.model.Status;
import com.easyservice.model.Task;
import com.easyservice.model.Worker;

@Service
public interface ITaskService {

	// crud
	Task addTask(Task task);

	void deleteTask(int taskId);

	void updateTask(Task task);

	Task getById(int taskId) throws TaskNotFoundException;

	List<Task> getAllTask();

	void assignTaskToWorker(int taskId, int workerId) throws WorkerNotFoundException;

	Task getByMaintenanceId(int maintenanceId) throws TaskNotFoundException;

	Task getByOrganiserAndStatus(String organiser, String status);

	List<Task> getByTaskStartDateAndEndDate(LocalDate startDate, LocalDate endDate);

	int updateStatus(String value, int workerId);

	// worker
	Worker getByWorkerName(String workerName) throws WorkerNotFoundException;

	Worker getByStatusAndWorkType(String status, String workType) throws WorkerNotFoundException;

	Worker getByDurationAndWorkType(int workDuration, String workType) throws WorkerNotFoundException;
}
