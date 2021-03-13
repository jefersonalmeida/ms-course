package com.jastech.hrworker.resources;

import com.jastech.hrworker.entities.Worker;
import com.jastech.hrworker.repositories.WorkerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RefreshScope
@RestController
@RequestMapping(value = "/workers")
public class WorkerResource {
    private static Logger logger = LoggerFactory.getLogger(WorkerResource.class);

    // @Value("${test.config}")
    // private String testConfig;

    private final Environment env;
    private final WorkerRepository repository;

    @Autowired
    public WorkerResource(Environment env, WorkerRepository repository) {
        this.env = env;
        this.repository = repository;
    }

    @GetMapping(value = "configs")
    public ResponseEntity<Void> configs() {
        // logger.info("CONFIG = " + testConfig);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Worker>> findAll() {
        List<Worker> list = repository.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Worker> find(@PathVariable("id") Long id) {

        /*try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        logger.info("PORT = " + env.getProperty("local.server.port"));

        Worker obj = repository.findById(id).get();
        return ResponseEntity.ok().body(obj);
    }
}
