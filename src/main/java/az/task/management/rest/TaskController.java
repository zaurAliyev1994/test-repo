package az.task.management.rest;

import az.task.management.model.TaskDto;
import az.task.management.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api("Task management controller")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    @ApiOperation("get all tasks")
    public List<TaskDto> getTaskList() {
        return taskService.getTaskList();
    }

    @GetMapping("/tasks/{id}")
    @ApiOperation("get task by task ID")
    public TaskDto getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping("/tasks")
    @ApiOperation("create new task")
    public TaskDto createTask(@RequestBody TaskDto task) {
        return taskService.createTask(task);
    }

    @PutMapping("/tasks/{id}")
    @ApiOperation("update task by task ID")
    public TaskDto updateTask(@PathVariable Long id, @RequestBody TaskDto task){
       return taskService.updateTask(id, task);
    }

    @DeleteMapping("/tasks/{id}")
    @ApiOperation("delete task by task ID")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

}
