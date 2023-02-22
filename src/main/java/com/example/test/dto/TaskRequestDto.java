package com.example.test.dto;

import com.example.test.model.Status;
import com.example.test.model.Task;
import com.sun.istack.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskRequestDto {

    @NotNull
    @Size(min = 3)
    private String name;
    private String description;
    private Status status = Status.HOLD;


    public Task toModel() {
        Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        task.setStatus(status);
    }

}
