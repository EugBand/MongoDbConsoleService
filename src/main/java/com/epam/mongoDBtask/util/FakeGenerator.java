package com.epam.mongoDBtask.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.RandomUtils;

import com.epam.mongoDBtask.model.SubTask;
import com.epam.mongoDBtask.model.Task;
import com.epam.mongoDBtask.model.TaskCategoryType;
import com.epam.mongoDBtask.model.TaskStatusType;
import com.github.javafaker.Faker;

import lombok.experimental.UtilityClass;

@UtilityClass
public class FakeGenerator {

    private final Faker faker = new Faker();
    public List<Task> generateTasks(int amount) {
        return Stream.generate(() -> {
            Task task = new Task();
            task.setId(UUID.randomUUID().toString());
            task.setCreated(LocalDateTime.now());
            task.setDeadline(faker.date().future(6,2, TimeUnit.DAYS)
                    .toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            task.setStatus(TaskStatusType.values()[RandomUtils.nextInt(0, TaskStatusType.values().length)]);
            task.setCategory(TaskCategoryType.values()[RandomUtils.nextInt(0, TaskCategoryType.values().length)]);
            task.setName(faker.hacker().abbreviation() + faker.hacker().verb() + faker.hacker().noun());
            task.setDescription(faker.shakespeare().hamletQuote());
            return task;
        }).limit(amount).collect(Collectors.toList());
    }

    public List<SubTask> generateSubTasks(int amount) {
        return Stream.generate(() -> {
            SubTask subTask = new SubTask();
            subTask.setId(UUID.randomUUID().toString());
            subTask.setDeadline(faker.date().future(6,2, TimeUnit.DAYS)
                    .toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            subTask.setName(faker.hacker().abbreviation() + faker.hacker().verb() + faker.hacker().noun());
            subTask.setDescription(faker.shakespeare().hamletQuote());
            return subTask;
        }).limit(amount).collect(Collectors.toList());
    }
}
