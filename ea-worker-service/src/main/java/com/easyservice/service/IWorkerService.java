package com.easyservice.service;

import org.springframework.stereotype.Service;

import com.easyservice.exception.WorkerNotFoundException;
import com.easyservice.model.Worker;

@Service
public interface IWorkerService {
	
	Worker getByWorkerName(String workerName) throws WorkerNotFoundException;
	//Worker getByWorkerNameAndStatus(String workName,String status) throws
		// WorkerNotFoundException;
		// Worker getByWorkerNameAndWorkType(String workName,String workType) throws
		// WorkerNotFoundException;

	//Task getByWorkType(String workType) throws WorkerNotFoundException;
	//Worker getByWorkerNameAndStatus(String workName,String status) throws WorkerNotFoundException;
	//Worker getByWorkerNameAndWorkType(String workName,String workType) throws WorkerNotFoundException;
	
}
