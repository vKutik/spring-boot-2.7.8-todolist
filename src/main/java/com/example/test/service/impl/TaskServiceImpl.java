package com.example.test.service.impl;

import com.example.test.model.Task;
import com.example.test.repository.TaskRepository;
import com.example.test.service.TaskService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;

    @Override
    public Task insert(Task task) {
        return repository.save(task);
    }

    @Override
    public Task getById(Long id) {
        return repository.getTaskById(id);
    }

    @Override
    public List<Task> getAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
