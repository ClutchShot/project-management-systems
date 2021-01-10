package com.project.Entities;

import com.project.Response.SimpleUser;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @ManyToMany(mappedBy = "projects")
    private List<User> users;
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Task> tasks;

    public Project(String name) {
        this.name = name;
    }

    public Project(){}

    public String getName() {
        return name;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public int getId() {
        return id;
    }

    public  void  addTask(Task task){
        tasks.add(task);
    }

    public List<SimpleUser> getSimpleUsers(){
        List<SimpleUser> list = new ArrayList<>();
        for (User user: users) {
            list.add(new SimpleUser(user.getId(),user.getUsername()));
        }
        return  list;
    }

    public void AddNewMemeber(User user){
        users.add(user);
    }
}
