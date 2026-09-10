//controller tasks, or get task by ID
package com.mitarth.devtask.controller;

import com.mitarth.devtask.dto.TaskRequest;
import com.mitarth.devtask.dto.TaskResponse;
import com.mitarth.devtask.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService service;

    @GetMapping
    public List<TaskResponse> getALLTasks() {
        return service.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskResponse getTask(@PathVariable Long id) {
        return service.getTask(id);
    }

    @PostMapping
    public TaskResponse createTask(@RequestBody TaskRequest task) {
        return service.saveTask(task);
    }

    @PostMapping("/{id}")
    public TaskResponse updateTask(@PathVariable Long id, @RequestBody TaskRequest task) {
        return service.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id) {
        service.deleteTask(id);
        return "Task deleted";
    }
}
