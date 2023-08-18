package com.balancee.test.controller;

import com.balancee.test.models.Task;
import com.balancee.test.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tasks") // Base URL for the tasks resource
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    /**
     * Get a list of tasks.
     *
     * @param completed Filter tasks by completion status (optional)
     * @param userInfo from spring security ID of the task to delete
     * @return List of tasks
     */
    @GetMapping
    public ResponseEntity<List<Task>>  getAllTasks(@RequestParam(required = false) String completed, @AuthenticationPrincipal UserDetails userInfo) {
        String username = userInfo.getUsername();
        List<Task> tasks = taskService.getAllTasks(completed,username);
        return ResponseEntity.ok(tasks);
    }

    /**
     * Create a new task.
     *
     * @param task Task object to create
     * @param userInfo from spring security ID of the task to delete
     * @return Created task
     */
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task, @AuthenticationPrincipal UserDetails userInfo) {
        String username = userInfo.getUsername();
        Task createdTask = taskService.createNewTask(task,username);
        return ResponseEntity.ok(createdTask);
    }



    /**
     * Update a task by its ID.
     *
     * @param taskId ID of the task to update
     * @param userInfo from spring security ID of the task to delete
     * @param task   Updated task object
     * @return Updated task
     */
    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task task, @AuthenticationPrincipal UserDetails userInfo) {
        String username = userInfo.getUsername();
        Task updatedTask = taskService.updateTask(taskId, task,username);
        return ResponseEntity.ok(updatedTask);
    }

    /**
     * Update a task as completed.
     *
     * @param taskId ID of the task to mark as completed
     * @param userInfo from spring security ID of the task to delete
     * @return Success message
     */

    @PutMapping("/{taskId}/complete")
    public ResponseEntity<String > completeTask(@PathVariable("taskId") Long taskId, @AuthenticationPrincipal UserDetails userInfo){
        String username = userInfo.getUsername();
        taskService.completeTask(taskId, username);
        return ResponseEntity.ok("Successfully completed task id: "+ taskId);
    }


    /**
     * Delete a task by its ID.
     *
     * @param taskId ID of the task to delete
     * @param userInfo from spring security ID of the task to delete
     * @return Success message
     */
    @DeleteMapping("/{taskId}")
    public ResponseEntity<String > deleteTask(@PathVariable("taskId") Long taskId, @AuthenticationPrincipal UserDetails userInfo){
        String username = userInfo.getUsername();
        taskService.deleteTaskById(taskId,username);
        return ResponseEntity.ok("Successfully delete task id: "+ taskId);
    }


    /**
     * Get a task by its ID.
     *
     * @param taskId ID of the task to retrieve
     * @return Task with the given ID
     */
    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable("taskId") Long taskId, @AuthenticationPrincipal UserDetails userInfo){
        String username = userInfo.getUsername();
        Task task = taskService.getTaskById(taskId,username);
        return ResponseEntity.ok(task);
    }


}
