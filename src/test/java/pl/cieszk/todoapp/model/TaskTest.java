package pl.cieszk.todoapp.model;

import org.junit.jupiter.api.Test;
import pl.cieszk.todoapp.model.entity.Task;

import static org.assertj.core.api.Assertions.assertThat;

public class TaskTest {

    @Test
    public void testTaskGetterAndSetter() {
        Task task = new Task();

        // Test ID
        task.setId(1L);
        assertThat(task.getId()).isEqualTo(1L);

        // Test Title
        task.setTitle("Test Title");
        assertThat(task.getTitle()).isEqualTo("Test Title");

        // Test Description
        task.setDescription("Test Description");
        assertThat(task.getDescription()).isEqualTo("Test Description");

        // Test Done Status
        task.setDone(true);
        assertThat(task.isDone()).isEqualTo(true);
    }

    @Test
    public void testToString() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Test Title");
        task.setDescription("Test Description");
        task.setDone(true);

        String expected = "Task(id=1, title=Test Title, description=Test Description, done=true)";
        assertThat(task.toString()).isEqualTo(expected);
    }
}