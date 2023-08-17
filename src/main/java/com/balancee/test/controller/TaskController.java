package com.balancee.test.controller;

import com.balancee.test.models.Task;
import com.balancee.test.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
     * @return List of tasks
     */
    @GetMapping("/{userId}")
    public ResponseEntity<List<Task>>  getAllTasks(@RequestParam(required = false) String completed, @PathVariable("userId") Long userId ) {
        List<Task> tasks = taskService.getAllTasks(completed,userId);
        return ResponseEntity.ok(tasks);
    }

    /**
     * Create a new task.
     *
     * @param task Task object to create
     * @return Created task
     */
    @PostMapping("/{userId}")
    public ResponseEntity<Task> createTask(@RequestBody Task task,@PathVariable("userId") Long userId) {
        Task createdTask = taskService.createNewTask(task,userId);
        return ResponseEntity.ok(createdTask);
    }



    /**
     * Update a task by its ID.
     *
     * @param taskId ID of the task to update
     * @param task   Updated task object
     * @return Updated task
     */
    @PutMapping("/{userId}/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task task) {
        Task updatedTask = taskService.updateTask(taskId, task);
        return ResponseEntity.ok(updatedTask);
    }

    /**
     * Update a task as completed.
     *
     * @param taskId ID of the task to mark as completed
     * @return Success message
     */

    @PutMapping("/{userId}/{taskId}/complete")
    public ResponseEntity<String > completeTask(@PathVariable("taskId") Long taskId){
        taskService.completeTask(taskId);
        return ResponseEntity.ok("Successfully completed task id: "+ taskId);
    }


    /**
     * Delete a task by its ID.
     *
     * @param taskId ID of the task to delete
     * @return Success message
     */
    @DeleteMapping("/{userId}/{taskId}")
    public ResponseEntity<String > deleteTask(@PathVariable("taskId") Long taskId){
        taskService.deleteTaskById(taskId);
        return ResponseEntity.ok("Successfully delete task id: "+ taskId);
    }


    /**
     * Get a task by its ID.
     *
     * @param taskId ID of the task to retrieve
     * @return Task with the given ID
     */
    @GetMapping("/{userId}/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable("taskId") Long taskId){
        Task task = taskService.getTaskById(taskId);
        return ResponseEntity.ok(task);
    }


}
