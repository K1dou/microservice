package com.kidou.hr_worker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kidou.hr_worker.model.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long> {

}
