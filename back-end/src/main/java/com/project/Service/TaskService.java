package com.project.Service;

import com.project.Entities.Task;
import com.project.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    public void save(Task task){
        taskRepository.save(task);
    }
    public  void delete(Task task){
        taskRepository.delete(task);
    }
}
