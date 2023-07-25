package pl.cieszk.todoapp.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "TASK")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITLE")
    @Size(max = 30)
    @NotBlank(message = "Task title must not be empty!")
    private String title;

    @Column(name = "DESCRIPTION")
    @Size(max = 100)
    private String description;

    @Column(name = "DONE")
    private boolean done = false;
}
