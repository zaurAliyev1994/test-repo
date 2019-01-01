package az.task.management.rest;

import az.task.management.model.Task;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskRestController {

    @GetMapping("/tasks")
    public List<Task> getTasks() {
        return null;
    }

    @GetMapping("/tasks/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return null;
    }

    @PostMapping("/tasks")
    public Task addTask(@RequestBody Task task) {
        return null;
    }

    @PutMapping("/tasks/{id}")
    public void updateTask(@PathVariable Long id){

    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable Long id) {

    }


}
