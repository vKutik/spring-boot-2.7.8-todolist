package com.example.test.service;

import com.example.test.model.Task;
import java.util.List;

public interface TaskService {

    Task insert(Task task);

    Task getById(Long id);

    List<Task> getAll();

    void deleteById(Long id);
}
