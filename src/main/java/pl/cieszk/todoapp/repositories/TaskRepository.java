package pl.cieszk.todoapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.cieszk.todoapp.model.entity.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findByTitle(String title);
    List<Task> findByDoneIsTrue();
    List<Task> findByDoneIsFalse();
    List<Task> findAll();
    Task getById(Long id);
}
