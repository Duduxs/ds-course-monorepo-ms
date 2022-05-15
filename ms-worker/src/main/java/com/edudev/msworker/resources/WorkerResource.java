package com.edudev.msworker.resources;

import com.edudev.msworker.entities.Worker;
import com.edudev.msworker.repositories.WorkerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/workers")
public class WorkerResource {

    private final WorkerRepository repository;

    @Autowired
    private Environment env;

    @Value("${test.config}")
    private String testConfig;

    private static final Logger logger = LoggerFactory.getLogger(WorkerResource.class);

    @Autowired
    public WorkerResource(final WorkerRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/configs")
    public ResponseEntity<Void> findAllConfigs(){
        logger.info("CONFIG = " + testConfig);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Collection<Worker>> findAll(){

        final var result = repository.findAll();

        return ResponseEntity.ok(result);

    }

    @GetMapping("{id}")
    public ResponseEntity<Worker> findById(@PathVariable Long id){

        logger.info("PORT = " + env.getProperty("local.server.port"));

        final var result = repository.findById(id).get();

        return ResponseEntity.ok(result);

    }

}
