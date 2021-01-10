package com.project.Controller;


import com.project.Entities.Project;
import com.project.Entities.Task;
import com.project.Entities.User;
import com.project.Response.SimpleProject;
import com.project.Response.SimpleProjectPage;
import com.project.Service.ProjectService;
import com.project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/task-tracking")
public class MainController {

    @Autowired
    UserService userService;
    @Autowired
    ProjectService projectService;


    //here, as you like, we can pass parameters in the url(like ?usernam=1&password=123), and in the body
    //if in url so @PathVariable("username")...
    // in body @RequestBody
    @PostMapping("/sign-in")
    public ResponseEntity<?> SignIn(@RequestBody User user){
        //be sure our use is Project Manager
        user.setRole("PM");
        userService.save(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> SignUp( @RequestParam String username,  @RequestParam String password){
        if(userService.exists(username)){
            User check = userService.getByUsername(username);
            if (check.getPassword().equals(password))
                return ResponseEntity.ok(HttpStatus.OK);
            else
                return ResponseEntity.badRequest().body("Password id wrong");
        }
        else
            return ResponseEntity.badRequest().body("No such user");
    }


    //User homepage
    //here list of project, links
    //user UI, add new project and "create a new user(team member)" I will write about this below
    @GetMapping("/{id}/home")
    public List<SimpleProject> UserProjects(@PathVariable("id") int id){
        User user = userService.getByid(id);
        return user.getSimpleProjects();
    }

    @GetMapping("/{user_id}/{project_id}")
    public SimpleProjectPage ProjectPage(@PathVariable("user_id") int user_id, @PathVariable("project_id") int project_id){
        Project project = projectService.getByid(project_id);
        return new SimpleProjectPage(project.getTasks(), project.getSimpleUsers());
    }

    @PostMapping("/{user_id}/{project_id}/new-user")
    public ResponseEntity<?> AddTeammember(@PathVariable("user_id") int user_id, @PathVariable("project_id") int project_id,
                                           @RequestBody User user){
        User PM = userService.getByid(user_id);
        if (!PM.getRole().equals("PM"))
            return ResponseEntity.badRequest().body("User not PM");
        if (userService.exists(user.getUsername()))
            return ResponseEntity.badRequest().body("Username is already taken");
        userService.save(user);
        User member = userService.getByUsername(user.getUsername());
        Project project = projectService.getByid(project_id);
        project.AddNewMemeber(member);
        projectService.save(project);
        return  ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/{user_id}/{project_id}/new-task")
    public ResponseEntity<?> NewTask(@PathVariable("user_id") int user_id, @PathVariable("project_id") int project_id,
                                     @RequestBody Task task){
        User user = userService.getByid(user_id);
        if (user.getRole().equals("PM")){
        projectService.addTask(project_id, task);
        return ResponseEntity.ok(HttpStatus.OK);
        }
        else
            return  ResponseEntity.badRequest().body("User not PM");
    }

    @PostMapping("/{user_id}/new-project")
    public ResponseEntity<?> NewProject(@PathVariable("user_id") int user_id, @RequestBody Project project ){
        User user = userService.getByid(user_id);
        //Project project = new Project(project_name);
        boolean flage =  userService.CreateNewProject(user, project);
        return ResponseEntity.ok(flage);
    }

    @PostMapping("/{user_id}/{project_id}/change-status")
    public ResponseEntity<?> ChangeStatusOfTask(@PathVariable("user_id") int user_id, @PathVariable("project_id") int project_id,
                                                @RequestBody Task task ){
        User user = userService.getByid(user_id);
        if (!user.exist_project(project_id))
            return ResponseEntity.badRequest().body("User doesn't have this project");
        Task project_task = projectService.getTask(task.getId());
        if (project_task==null)
            return ResponseEntity.badRequest().body("No such task");
        project_task.setStatus(task.getStatus());
        projectService.editTask(project_task);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
