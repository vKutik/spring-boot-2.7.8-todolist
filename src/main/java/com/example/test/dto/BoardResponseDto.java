package com.example.test.dto;

import com.example.test.model.Task;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardResponseDto {

    private Long id;
    private String name;
    private List<Task> tasks;

}
