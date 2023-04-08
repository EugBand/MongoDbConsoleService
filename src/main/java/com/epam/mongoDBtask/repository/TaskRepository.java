package com.epam.mongoDBtask.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.epam.mongoDBtask.model.Task;

public interface TaskRepository extends MongoRepository<Task, Long> {

    @Aggregation(pipeline = {
            "{ '$match': { '$text': { '$search': ?0}, 'status': ?1 } }",
            "{'$sort':{'description':?2}}"
    })
    List<Task> findTasksWithParams(String search, String status, Long sort);

    Optional<Task> findById(String id);
}
