package pl.cieszk.todoapp.utils;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import pl.cieszk.todoapp.model.TaskDetailsDto;
import pl.cieszk.todoapp.model.TaskDto;
import pl.cieszk.todoapp.model.entity.Task;

@Component
@Mapper(config = MapStructConfig.class)
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mapping(target = "id", ignore = true)
    Task toEntity(TaskDetailsDto taskDetailsDto);

    @Mapping(target = "id", ignore = true)
    void updateFromDto(TaskDetailsDto taskDetailsDto, @MappingTarget Task task);

    TaskDetailsDto toDetailsDto(Task task);

    TaskDto toDto(Task task);
}
