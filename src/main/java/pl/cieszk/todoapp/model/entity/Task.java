package pl.cieszk.todoapp.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.Size;

@Entity
@Table(name = "TASK")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITLE")
    @Size(max = 30)
    private String title;

    @Column(name = "DESCRIPTION")
    @Size(max = 100)
    private String description;

    @Column(name = "DONE")
    private boolean done;

}
