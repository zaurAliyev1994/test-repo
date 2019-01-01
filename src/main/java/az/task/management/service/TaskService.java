package az.task.management.service;

import az.task.management.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private Task theTask = new Task(Long.valueOf(1), "Write rest controller");

    public List<Task> getTaskList() {
        List<Task> taskList = new ArrayList<>();
        taskList.add(theTask);
        return taskList;
    }

    public Task getTaskById(Long id) {
        return theTask;
    }

    public Task createTask(Task task) {
        return task;
    }

    public Task updateTask(Long id, Task task){
        return task;

    }
    public void deleteTask(Long id) {

    }

}
