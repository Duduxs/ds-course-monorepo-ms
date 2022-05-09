package com.edudev.mspayroll.feignclients;

import com.edudev.mspayroll.entities.Worker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Component
@FeignClient(name = "ms-worker", url = "http://localhost:8081", path = "/workers")
public interface WorkerFeignClient {

    @GetMapping("{id}")
    ResponseEntity<Worker> findById(@PathVariable UUID id);

}
