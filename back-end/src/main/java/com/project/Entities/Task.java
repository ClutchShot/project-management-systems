package com.project.Entities;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Task {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String description;
    private String status;
    @NotNull
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="project_id")
    protected Project project;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Task(){}


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }
}
