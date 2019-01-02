package az.task.management.mapper;

import az.task.management.dao.TaskEntity;
import az.task.management.model.TaskDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class TaskMapper {

    public static final TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    public abstract TaskDto taskEntityToDto(TaskEntity taskEntity);
    public abstract TaskEntity taskDtoToEntity(TaskDto taskDto);
}
