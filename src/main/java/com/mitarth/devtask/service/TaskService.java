package com.mitarth.devtask.service;
//managing all the tasks
import com.mitarth.devtask.dto.TaskRequest;
import com.mitarth.devtask.dto.TaskResponse;
import com.mitarth.devtask.exception.ResourceNotFoundException;
import com.mitarth.devtask.model.Task;
import com.mitarth.devtask.model.Users;
import com.mitarth.devtask.repo.TaskRepository;
import com.mitarth.devtask.repo.repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private repo usersRepository;

//    DTO connecting
    // mapping helper

    private Task toEntity(TaskRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setDueDate(request.getDueDate());
        return task;
    }

    private TaskResponse toResponse(Task task) {
        TaskResponse response = new TaskResponse();
        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setStatus(task.getStatus());
        response.setDueDate(task.getDueDate());
        if (task.getUser() != null) {
            response.setUsername(task.getUser().getUsername());
        }
        return response;
    }

    // service method

    public List<TaskResponse> getAllTasks() {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        return repository.findByUserUsername(username)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public TaskResponse getTask(Long id) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        return toResponse(task);
    }

    public TaskResponse saveTask(TaskRequest request) {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        Users user = usersRepository.findByUsername(username);

        Task task = toEntity(request);
        task.setUser(user);

        return toResponse(repository.save(task));
    }

    public TaskResponse updateTask(Long id, TaskRequest request) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("the required task is not found"));
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        Users user = usersRepository.findByUsername(username);

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setDueDate(request.getDueDate());
        task.setUser(user);

        return toResponse(repository.save(task));
    }

    public void deleteTask(Long id) {
        repository.deleteById(id);
    }
}
