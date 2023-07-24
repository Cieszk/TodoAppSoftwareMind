package pl.cieszk.todoapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.cieszk.todoapp.model.entity.Task;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByDoneIsTrue();

    List<Task> findByDoneIsFalse();

    List<Task> findAll();

    Optional<Task> findById(Long id);

}
