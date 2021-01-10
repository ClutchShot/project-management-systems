package com.project.Response;

import com.project.Entities.Task;

import java.util.List;

public class SimpleProjectPage {
    public List<Task> taskList;
    public List<SimpleUser> userList;

    public SimpleProjectPage(List<Task> taskList, List<SimpleUser> userList) {
        this.taskList = taskList;
        this.userList = userList;
    }
}
