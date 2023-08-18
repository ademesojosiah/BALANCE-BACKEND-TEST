package com.balancee.test.repository;

import com.balancee.test.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // Get tasks by user ID
    @Query(value = "SELECT * FROM TASK_TABLE s where s.USER_ID = ?1", nativeQuery = true)
    List<Task> GetTasksByUserId(Long userId);

    // Get completed tasks by user ID
    @Query(value = "SELECT * FROM TASK_TABLE s where s.USER_ID = ?1 and s.COMPLETED = TRUE", nativeQuery = true)
    List<Task> GetTasksByUserIdAndCompletedTrue(Long userId);

    // Get incomplete tasks by user ID
    @Query(value = "SELECT * FROM TASK_TABLE s where s.USER_ID = ?1 and s.COMPLETED = FALSE", nativeQuery = true)
    List<Task> GetTasksByUserIdAndCompletedFalse(Long userId);

    // Get a specific task by user ID and task ID
    @Query(value = "SELECT * FROM TASK_TABLE s where s.USER_ID = ?1 and s.TASK_ID = ?2", nativeQuery = true)
    Task getUserByTaskAndId(Long userId, Long taskId);
}
