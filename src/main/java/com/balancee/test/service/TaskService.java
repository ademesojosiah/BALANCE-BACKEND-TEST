package com.balancee.test.service;

import com.balancee.test.models.Task;
import com.balancee.test.models.User;
import com.balancee.test.repository.TaskRepository;
import com.balancee.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    // Create a new task and associate it with a user
    public Task createNewTask(Task task, Long userId){
        this.validator(task);
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new IllegalStateException("user with id: "+ userId+ " doesn't exist"));
        task.setUser(user);
        return taskRepository.save(task);
    }


    // Update a task and validate ownership before updating
    public Task updateTask(Long taskId , Task task, Long userId){
        Task updatedtask = taskRepository.findById(taskId)
                .orElseThrow(()-> new IllegalStateException("task id "+ taskId +" does not exist"));
        if(!this.isOwner(userId,taskId)){
            throw new IllegalStateException("Unauthorised to update task id: "+ taskId);
        }

        this.validator(task);
        updatedtask.setTitle(task.getTitle());
        updatedtask.setDescription(task.getDescription());
        updatedtask.setDueDate(task.getDueDate());

        return updatedtask;
    }

    // Validate ownership and Mark a task as completed
    public void completeTask(Long taskId, Long userId){
        if(!this.isOwner(userId,taskId)){
            throw new IllegalStateException("Unauthorised to update task id: "+ taskId);
        }

        Task updatedtask = taskRepository.findById(taskId)
                .orElseThrow(()-> new IllegalStateException("task id "+ taskId +" does not exist"));
        updatedtask.setCompleted(true);
    }


    // Validate ownership and Delete a task by ID
    public void deleteTaskById(Long taskId, Long userId){
        if(!this.isExists(taskId)){
            throw new IllegalStateException("task id "+ taskId +" does not exist");
        }

        if(!this.isOwner(userId,taskId)){
            throw new IllegalStateException("Unauthorised to update task id: "+ taskId);
        }


        taskRepository.deleteById(taskId);
    }

    // Get all tasks with optional filtering by completion status
    public List<Task> getAllTasks(String completed, Long userId){

        if(completed == null)
            return taskRepository.GetTasksByUserId(userId);
         else if(completed.equals("true"))
            return taskRepository.GetTasksByUserIdAndCompletedTrue(userId);
         else if (completed.equals("false"))
            return taskRepository.GetTasksByUserIdAndCompletedFalse(userId);
         else
             return taskRepository.GetTasksByUserId(userId);
    }

    // Validate ownership and  Get a task by ID
    public Task getTaskById(Long taskId, Long userId){
        if(!this.isExists(taskId)){
            throw new IllegalStateException("task id "+ taskId +" does not exist");
        }
        if(!this.isOwner(userId,taskId)){
            throw new IllegalStateException("Unauthorised to update task id: "+ taskId);
        }

        return taskRepository.findById(taskId)
                .orElseThrow(()-> new IllegalStateException("task id "+ taskId +" does not exist"));
    }

    // Check if a task with the given ID exists
    public  boolean isExists(Long taskId){
        return taskRepository.existsById(taskId);
    }


    // Validate the task before creating or updating
    public  void validator(Task task){
        if (task.getTitle() == null) {
            throw new IllegalStateException("Task title is required");
        } else if (task.getDescription() == null) {
            throw new IllegalStateException("Task description is required");
        } else if (task.getDueDate() == null) {
            throw new IllegalStateException("Task due date is required");
        }
    }

    // Check if the given user is the owner of the task
    public boolean isOwner(Long userId, Long taskId){
        Task task = taskRepository.getUserByTaskAndId(userId,taskId);
        return task != null;
    }


}
