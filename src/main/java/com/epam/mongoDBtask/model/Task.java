package com.epam.mongoDBtask.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tasks")
public class Task {

    @Id
    private String id;

    private LocalDateTime created;

    private LocalDateTime deadline;

    @TextIndexed
    private String name;

    @TextIndexed
    private String description;

    private List<SubTask> subTasks;

    private TaskStatusType status;

    private TaskCategoryType category;

}
