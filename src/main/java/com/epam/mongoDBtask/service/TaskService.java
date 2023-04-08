package com.epam.mongoDBtask.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.epam.mongoDBtask.model.Task;
import com.epam.mongoDBtask.repository.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;


    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    public boolean save(Task task) {
        try {
            taskRepository.save(task);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Optional<Task> getById(String id) {
        try {
            return taskRepository.findById(id);

        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    public List<Task> search(String search, String status, Long order) {
        return taskRepository.findTasksWithParams(search, status, order);
    }
}
