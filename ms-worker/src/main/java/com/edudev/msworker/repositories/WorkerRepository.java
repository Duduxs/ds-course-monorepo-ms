package com.edudev.msworker.repositories;

import com.edudev.msworker.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Long> { }
