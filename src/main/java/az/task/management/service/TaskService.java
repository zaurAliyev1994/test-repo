package az.task.management.service;

import az.task.management.dao.TaskEntity;
import az.task.management.mapper.TaskMapper;
import az.task.management.model.TaskDto;
import az.task.management.model.enums.TaskStatus;
import az.task.management.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskDto> getTaskList() {
        List<TaskEntity> taskEntities = taskRepository.findAll();

        return taskEntities.stream().filter(Objects::nonNull)
                .map((taskEntity) -> TaskMapper.INSTANCE.taskEntityToDto(taskEntity)).collect(Collectors.toList());
    }

    public TaskDto getTaskById(Long id) {
        TaskEntity taskEntity = null;
        try {
            taskEntity = taskRepository.findById(id).orElseThrow(() -> new Exception("Task not found"));
        } catch (Exception e) {
            // TODO add exception handler
            e.printStackTrace();
        }
        return TaskMapper.INSTANCE.taskEntityToDto(taskEntity);
    }

    public TaskDto createTask(TaskDto task) {
        TaskEntity taskEntity = TaskMapper.INSTANCE.taskDtoToEntity(task);
        taskEntity.setId(null);
        taskEntity.setStatus(TaskStatus.CREATED);
        taskEntity.setCreateDate(LocalDateTime.now());
        taskEntity.setUpdateDate(LocalDateTime.now());
        return TaskMapper.INSTANCE.taskEntityToDto(taskRepository.save(taskEntity));
    }

    public TaskDto updateTask(Long id, TaskDto task){
        TaskEntity taskEntity = TaskMapper.INSTANCE.taskDtoToEntity(task);
        taskEntity.setId(id);
        taskEntity.setUpdateDate(LocalDateTime.now());
        return TaskMapper.INSTANCE.taskEntityToDto(taskRepository.save(taskEntity));
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

}
