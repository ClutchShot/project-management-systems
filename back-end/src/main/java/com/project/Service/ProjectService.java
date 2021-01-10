package com.project.Service;

import com.project.Entities.Project;
import com.project.Entities.Task;
import com.project.Repository.ProjectRepository;
import com.project.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProjectService {

    @Autowired
    ProjectRepository repository;
    @Autowired
    TaskRepository taskRepository;
    public void save(Project project) {
        repository.save(project);
    }
    public void delete(int id) {
        repository.deleteById(id);
    }
    public Project getByid(int id){
        return repository.findById(id);
    }
    public void addTask(int id, Task task){
        Project project =  repository.findById(id);
        task.setProject(project);
        project.addTask(task);
        repository.save(project);
    }
    public Task getTask(int id){
        return taskRepository.findById(id);
    }

    public void editTask(Task task){
        taskRepository.save(task);
    }
}
