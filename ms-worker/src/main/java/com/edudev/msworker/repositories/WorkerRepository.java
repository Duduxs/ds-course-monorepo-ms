package com.edudev.msworker.repositories;

import com.edudev.msworker.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkerRepository extends JpaRepository<Worker, UUID> { }
