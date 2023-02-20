package com.example.test.dto;

import com.example.test.model.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskResponseDto {

    private Long id;
    private String name;
    private String description;
    private Status status = Status.HOLD;

}
