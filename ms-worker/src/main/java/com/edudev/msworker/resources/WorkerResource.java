package com.edudev.msworker.resources;

import com.edudev.msworker.entities.Worker;
import com.edudev.msworker.repositories.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/workers")
public class WorkerResource {

    private final WorkerRepository repository;

    @Autowired
    public WorkerResource(final WorkerRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<Collection<Worker>> findAll(){

        final var result = repository.findAll();

        return ResponseEntity.ok(result);

    }

    @GetMapping("{id}")
    public ResponseEntity<Worker> findById(@PathVariable UUID id){

        final var result = repository.findById(id).get();

        return ResponseEntity.ok(result);

    }

}
