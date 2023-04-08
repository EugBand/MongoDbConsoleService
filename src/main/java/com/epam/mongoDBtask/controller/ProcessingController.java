package com.epam.mongoDBtask.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epam.mongoDBtask.service.FakeGenerateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/processes", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProcessingController {

    private final FakeGenerateService fakeValueService;

    @PostMapping
    public ResponseEntity<Void> generateFakes(@RequestParam int tasks, @RequestParam int subTasks) {
        fakeValueService.generateValues(tasks, subTasks);
        return ResponseEntity.ok().build();
    }

}
