package com.project.Repository;

import com.project.Entities.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {

    Task findById(int id);
    void deleteById(int id);
}
