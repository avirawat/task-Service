package com.easyservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easyservice.exception.WorkerNotFoundException;
import com.easyservice.model.Worker;
import com.easyservice.repository.IWorkRepository;

@Service
public class WorkerServiceImpl implements IWorkerService {

	@Autowired
	IWorkRepository workRepository;
	
	@Override
	public Worker getByWorkerName(String workerName) throws WorkerNotFoundException {
		return workRepository.findByWorkerName(workerName);
	}

	@Override
	public Worker getByStatusAndWorkType(String status, String workType) throws WorkerNotFoundException {
		return workRepository.findByWStatusAndWorkType(status, workType);
	}

	@Override
	public Worker getByDurationAndWorkType(int workDuration, String workType) throws WorkerNotFoundException {
		return workRepository.findByDurationAndWorkType(workDuration, workType);
	}
	
	

}
