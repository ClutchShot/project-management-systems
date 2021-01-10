package com.project.Entities;

import com.project.Response.SimpleProject;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String username;
    private String password;
    private String role;
    @ManyToMany
    @JoinTable(
            name = "user_projects",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName= "id"),
            inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"))
    private List<Project> projects;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(){}

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public JSONArray getJSONProjects(){
        JSONArray jsonArray = new JSONArray();
        for (Project p: projects) {
            JSONObject pr = new JSONObject();
            pr.put("id", p.getId());
            pr.put("name", p.getName());
            jsonArray.put(pr);
        }
        return jsonArray;
    }

    public List<SimpleProject> getSimpleProjects(){
        List<SimpleProject> projectList = new ArrayList<>();
        for (Project p: projects) {
            projectList.add(new SimpleProject(p.getId(), p.getName()));
        }
        return projectList;
    }

    public void addNewProject(Project project){
        projects.add(project);
    }

    public boolean exist_project(int project_id){
        for (Project p: projects) {
            if (p.getId() == project_id)
                return true;
        }
        return false;
    }
}
