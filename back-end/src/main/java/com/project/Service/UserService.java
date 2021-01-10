package com.project.Service;

import com.project.Entities.Project;
import com.project.Entities.User;
import com.project.Repository.ProjectRepository;
import com.project.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository repository;
    @Autowired
    ProjectRepository projectRepository;

    public void save(User user) {
        repository.save(user);
    }
    public void delete(int id) {
        repository.deleteById(id);
    }
    public Boolean exists(String username){
        return  repository.existsByUsername(username);
    }
    public User getByid(int id){
        return repository.findById(id).orElse(null);
    }
    public User getByUsername(String username){
        return repository.findByUsername(username);
    }
    public Boolean CreateNewProject(User user,Project project){
        if (exists(user.getUsername()) && user.getRole().equals("PM") ){
            projectRepository.save(project);
            user.addNewProject(project);
            repository.save(user);
            return true;
        }
        else
            return false;
    }

}
