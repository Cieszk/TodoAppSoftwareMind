package pl.cieszk.todoapp.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class TaskDetailsDto {
    private Long id;
    @NotBlank(message = "Task title must not be empty!")
    private String title;
    private String description;
    private boolean done;
}
