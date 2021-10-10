package com.easyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easyservice.model.Worker;



@Repository
public interface IWorkRepository extends JpaRepository<Worker,Integer>{
	
	Worker findByWorkerName(String workerName);

}
