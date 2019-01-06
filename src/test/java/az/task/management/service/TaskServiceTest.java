package az.task.management.service;

import az.task.management.dao.TaskEntity;
import az.task.management.exception.TaskNotFoundException;
import az.task.management.model.TaskDto;
import az.task.management.model.enums.TaskStatus;
import az.task.management.repository.TaskRepository;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.any;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;


@RunWith(SpringJUnit4ClassRunner.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    TaskEntity taskEntity1 = TaskEntity.builder()
            .id(1L)
            .name("task1")
            .description("Test task 1")
            .status(TaskStatus.CREATED)
            .isDone(false)
            .createDate(LocalDateTime.now())
            .updateDate(LocalDateTime.now())
            .build();

    TaskDto taskDtoForCreate = TaskDto.builder()
            .name("task2")
            .description("test task 2")
            .build();

    TaskDto taskDtoForUpdate = TaskDto.builder()
            .id(2L)
            .name("task3")
            .description("test task 3")
            .status("CREATED")
            .isDone(true)
            .createDate(LocalDateTime.now())
            .updateDate(LocalDateTime.now())
            .build();

    @Before
    public void setUp() {
        when(taskRepository.findAll()).thenReturn(Arrays.asList(null, taskEntity1, null));
        when(taskRepository.findById(1L)).thenReturn(Optional.of(taskEntity1));
        when(taskRepository.save(any())).then(AdditionalAnswers.returnsFirstArg());
    }

    @Test
    public void getTaskList() {
        List<TaskDto> taskList = taskService.getTaskList();

        verify(taskRepository, times(1)).findAll();
        assertEquals(taskList.size(), 1);
        assertNotNull(taskList.get(0));
        assertEquals(taskList.get(0).getName(), "task1");
    }

    @Test
    public void getTaskByIdSuccess() {
        TaskDto task = taskService.getTaskById(1L);

        verify(taskRepository, times(1)).findById(1L);
        assertNotNull(task);
        assertEquals(task.getName(), "task1");
    }

    @Test(expected = TaskNotFoundException.class)
    public void getTaskByIdWithIncorrectId() {
        TaskDto task = taskService.getTaskById(2L);
    }

    @Test
    public void createTask() {
        taskService.createTask(taskDtoForCreate);

        verify(taskRepository, times(1)).save((argThat(taskEntity ->
                        taskEntity.getId() == null &&
                        taskEntity.getName().equals(taskDtoForCreate.getName()) &&
                        taskEntity.getDescription().equals(taskDtoForCreate.getDescription()) &&
                        taskEntity.getStatus() == TaskStatus.CREATED &&
                        taskEntity.isDone() == false &&
                        taskEntity.getCreateDate() != null &&
                        taskEntity.getUpdateDate() != null
        )));
    }

    @Test
    public void updateTask() {
        taskService.updateTask(2L, taskDtoForUpdate);

        verify(taskRepository, times(1)).save((argThat(taskEntity ->
                        taskEntity.getId() == 2L &&
                        taskEntity.getName().equals(taskDtoForUpdate.getName()) &&
                        taskEntity.getDescription().equals(taskDtoForUpdate.getDescription()) &&
                        taskEntity.getStatus().name().equals(taskDtoForUpdate.getStatus()) &&
                        taskEntity.isDone() == taskDtoForUpdate.isDone() &&
                        taskEntity.getCreateDate() == taskDtoForUpdate.getCreateDate() &&
                        taskEntity.getUpdateDate() != null
        )));
    }

    @Test
    public void deleteTask() {
        taskService.deleteTask(1L);

        verify(taskRepository, times(1)).deleteById(1L);
    }

}