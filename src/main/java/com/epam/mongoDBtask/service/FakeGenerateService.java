package com.epam.mongoDBtask.service;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import com.epam.mongoDBtask.model.Task;
import com.epam.mongoDBtask.repository.TaskRepository;
import com.epam.mongoDBtask.util.FakeGenerator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FakeGenerateService {

    private final TaskRepository taskRepository;

    public void generateValues(int taskAmount, int subTasksAmount) {

        List<Task> tasks = FakeGenerator.generateTasks(taskAmount);
        tasks.forEach(t -> {
//            t.setId(UUID.randomUUID().toString());
            t.setSubTasks(FakeGenerator.generateSubTasks(RandomUtils.nextInt(0, subTasksAmount)));
        });
        tasks.forEach(taskRepository::save);
    }
}
