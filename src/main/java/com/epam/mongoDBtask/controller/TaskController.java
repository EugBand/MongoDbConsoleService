package com.epam.mongoDBtask.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epam.mongoDBtask.model.Task;
import com.epam.mongoDBtask.service.TaskService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TaskController {

    private final TaskService service;

    @GetMapping
    private ResponseEntity<List<Task>> getAll() {
        List<Task> tasks = service.getAll();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Task> getById(@PathVariable String id) {
        return service.getById(id)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    private ResponseEntity<Void> save(@RequestBody Task task) {
        if (service.save(task)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search")
    private ResponseEntity<List<Task>> search(@RequestParam String search, @RequestParam String status, @RequestParam Long order) {
        List<Task> responce = new ArrayList<>(service.search(search, status, order));
        return responce.isEmpty() ? new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(responce, HttpStatus.OK);
    }

}
