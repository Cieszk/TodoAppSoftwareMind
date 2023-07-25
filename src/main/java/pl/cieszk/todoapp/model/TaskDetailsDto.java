package pl.cieszk.todoapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskDetailsDto {
    private Long id;
    private String title;
    private String description;
    private boolean done;
}
