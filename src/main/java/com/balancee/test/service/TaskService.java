package com.balancee.test.service;

import com.balancee.test.models.Task;
import com.balancee.test.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task createNewTask(Task task){
        return taskRepository.save(task);
    }

    public Task updateTask(Long taskId , Task task){
        Task updatedtask = taskRepository.findById(taskId).orElseThrow(()-> new IllegalStateException("task id "+ taskId +" does not exist"));
        updatedtask.setTitle(task.getTitle());
        updatedtask.setDescription(task.getDescription());
        updatedtask.setCompleted(task.getCompleted());
        updatedtask.setDueDate(task.getDueDate());
        return taskRepository.save(task);
    }

    public void deleteTaskById(Long taskId){
        if(!this.isExists(taskId)){
            throw new IllegalStateException("task id "+ taskId +" does not exist");
        }
        taskRepository.deleteById(taskId);
    }
    public List<Task> getAllTask(){
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long taskId){
        if(!this.isExists(taskId)){
            throw new IllegalStateException("task id "+ taskId +" does not exist");
        }
        return taskRepository.findById(taskId);
    }

    public  boolean isExists(Long taskId){
        return taskRepository.existsById(taskId);
    }


}
