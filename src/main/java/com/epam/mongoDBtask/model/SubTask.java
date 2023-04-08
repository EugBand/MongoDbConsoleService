package com.epam.mongoDBtask.model;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.index.TextIndexed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubTask {

    private String id;

    private LocalDateTime deadline;

    private String name;

    @TextIndexed
    private String description;
}
