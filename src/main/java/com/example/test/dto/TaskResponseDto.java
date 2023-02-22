package com.example.test.dto;

import com.example.test.model.Board;
import com.example.test.model.Status;
import com.example.test.model.Task;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskResponseDto {

    private Long id;
    private String name;
    private String description;
    private Status status = Status.HOLD;


    public TaskResponseDto(Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.description = task.getDescription();
        this.status = task.getStatus();
    }
}
