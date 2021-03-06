package com.easyservice.service;

import org.springframework.stereotype.Service;

import com.easyservice.exception.WorkerNotFoundException;
import com.easyservice.model.Worker;

@Service
public interface IWorkerService {
	
	Worker getByWorkerName(String workerName) throws WorkerNotFoundException;
	
	Worker getByStatusAndWorkType(String status, String workType) throws WorkerNotFoundException;

	Worker getByDurationAndWorkType(int workDuration, String workType) throws WorkerNotFoundException;
	
}
