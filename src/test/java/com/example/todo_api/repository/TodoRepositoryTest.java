package com.example.todo_api.repository;

import com.example.todo_api.model.Todo;
import com.example.todo_api.repository.TodoRepository;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TodoRepositoryTest {

    @Test
    void crud_in_memory() {
        var repo = new TodoRepository();

        var id = UUID.randomUUID();
        var t = new Todo(id, "X", false, Instant.now());

        repo.save(t);
        assertEquals(1, repo.findAll().size());

        var got = repo.findById(id).orElseThrow();
        assertEquals("X", got.getTitle());

        t.setDone(true);
        repo.save(t);
        assertTrue(repo.findById(id).orElseThrow().isDone());

        repo.deleteById(id);
        assertTrue(repo.findById(id).isEmpty());
    }
}
