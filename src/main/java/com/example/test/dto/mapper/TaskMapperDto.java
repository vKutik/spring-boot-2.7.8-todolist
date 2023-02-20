package com.example.test.dto.mapper;

import com.example.test.dto.TaskRequestDto;
import com.example.test.dto.TaskResponseDto;
import com.example.test.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperDto implements RequestMapperDto<TaskRequestDto, Task>,
    ResponseMapperDto<TaskResponseDto, Task> {

    @Override
    public Task toModel(TaskRequestDto taskRequestDto) {
        Task task = new Task();
        task.setName(taskRequestDto.getName());
        task.setDescription(taskRequestDto.getDescription());
        task.setStatus(taskRequestDto.getStatus());
        return task;
    }

    @Override
    public TaskResponseDto toResponse(Task task) {
        TaskResponseDto taskResponseDto = new TaskResponseDto();
        taskResponseDto.setId(task.getId());
        taskResponseDto.setName(task.getName());
        taskResponseDto.setDescription(task.getDescription());
        taskResponseDto.setStatus(task.getStatus());

        return taskResponseDto;
    }

}
