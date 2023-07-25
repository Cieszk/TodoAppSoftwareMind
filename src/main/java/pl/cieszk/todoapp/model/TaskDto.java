package pl.cieszk.todoapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskDto {
    private Long id;
    private String title;
}
