package az.task.management.service;

import az.task.management.dao.TaskEntity;
import az.task.management.model.TaskDto;
import az.task.management.model.enums.TaskStatus;
import az.task.management.repository.TaskRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

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

    @Before
    public void setUp() {
        when(taskRepository.findAll()).thenReturn(Arrays.asList(null, taskEntity1, null));
    }

    @Test
    public void getTaskList() {
        List<TaskDto> taskList = taskService.getTaskList();

        verify(taskRepository, times(1)).findAll();
        assertEquals(taskList.size(), 1);
        assertNotNull(taskList.get(0));
        assertEquals(taskList.get(0).getName(), "task1");
    }
}