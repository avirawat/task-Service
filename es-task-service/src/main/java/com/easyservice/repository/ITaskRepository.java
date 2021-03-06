package com.easyservice.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.easyservice.model.Status;
import com.easyservice.model.Task;

@Repository
public interface ITaskRepository extends JpaRepository<Task, Integer> {

	@Modifying
	@Query(value = "update worker set task_id=?1 where worker_id=?2 ", nativeQuery = true)
	public int assignTask(int taskId, int workerId);

	@Query("from Task where maintenance_id=?1")
	Task findByMaintenanceId(int maintenanceId);

	@Query("from Task where organiser=?1 and tStatus=?2")
	Task findByOrganiserAndTStatus(String organiser, Status status);

	@Query("from Task where tStartDate<?1 and tEndDate>?2")
	List<Task> findByTaskStartDateAndEndDate(LocalDate startDate, LocalDate endDate);
	
	@Modifying
	@Query(value = "update worker set w_status=?1 where worker_id=?2 ", nativeQuery = true)
	int updateStatus(String value,int workerId);

}
