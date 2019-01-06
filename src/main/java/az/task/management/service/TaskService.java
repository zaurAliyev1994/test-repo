package az.task.management.service;

import az.task.management.dao.TaskEntity;
import az.task.management.exception.TaskNotFoundException;
import az.task.management.mapper.TaskMapper;
import az.task.management.model.TaskDto;
import az.task.management.model.enums.TaskStatus;
import az.task.management.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Validated
public class TaskService {

    private final TaskRepository taskRepository;
    private static final Logger log = LoggerFactory.getLogger(TaskService.class);

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskDto> getTaskList() {
        List<TaskEntity> taskEntities = taskRepository.findAll();
        log.info("Get task list");
        return taskEntities.stream().filter(Objects::nonNull)
                .map((taskEntity) -> TaskMapper.INSTANCE.taskEntityToDto(taskEntity)).collect(Collectors.toList());
    }

    public TaskDto getTaskById(@NotNull Long id) {

        TaskEntity taskEntity = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException (String.format("Task with id=%s not found", id)));

        log.info(String.format("Get task by id=%s",id));
        return TaskMapper.INSTANCE.taskEntityToDto(taskEntity);
    }

    public TaskDto createTask(@NotNull @Valid TaskDto task) {
        TaskEntity taskEntity = TaskMapper.INSTANCE.taskDtoToEntity(task);
        taskEntity.setId(null);
        taskEntity.setStatus(TaskStatus.CREATED);
        taskEntity.setDone(false);
        taskEntity.setCreateDate(LocalDateTime.now());
        taskEntity.setUpdateDate(LocalDateTime.now());
        TaskEntity createdTask = taskRepository.save(taskEntity);
        log.info(String.format("Task with id=%s created", createdTask.getId()));
        return TaskMapper.INSTANCE.taskEntityToDto(createdTask);
    }

    public TaskDto updateTask(@NotNull Long id, @NotNull @Valid TaskDto task){
        TaskEntity taskEntity = TaskMapper.INSTANCE.taskDtoToEntity(task);
        taskEntity.setId(id);
        taskEntity.setUpdateDate(LocalDateTime.now());
        log.info(String.format("Task with id=%s updated", id));
        return TaskMapper.INSTANCE.taskEntityToDto(taskRepository.save(taskEntity));
    }

    public void deleteTask(@NotNull Long id) {
        log.info(String.format("Task with id=%s deleted", id));
        taskRepository.deleteById(id);
    }

}
