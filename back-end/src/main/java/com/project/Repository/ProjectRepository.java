package com.project.Repository;

import com.project.Entities.Project;
import com.project.Entities.Task;
import com.project.Entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Integer> {

    Project findById(int id);
    void deleteById(int id);
}