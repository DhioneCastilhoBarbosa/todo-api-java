package com.example.todo_api.service;

import com.example.todo_api.dto.TodoCreateRequest;
import com.example.todo_api.model.Todo;
import com.example.todo_api.repository.TodoRepository;
import com.example.todo_api.service.TodoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TodoServiceTest {

    @Test
    @DisplayName("create gera id e createdAt e persiste")
    void create_ok() {
        var repo = new TodoRepository();
        var service = new TodoService(repo);

        var req = new TodoCreateRequest();
        req.setTitle("Estudar Java");

        Todo t = service.create(req);

        assertNotNull(t.getId());
        assertEquals("Estudar Java", t.getTitle());
        assertFalse(t.isDone());
        assertNotNull(t.getCreatedAt());
        assertEquals(1, service.list().size());
    }

    @Test
    @DisplayName("toggle altera done; id inexistente lança RuntimeException")
    void toggle_ok_and_not_found() {
        var repo = new TodoRepository();
        var service = new TodoService(repo);

        var req = new TodoCreateRequest();
        req.setTitle("A");
        var t = service.create(req);

        var updated = service.toggle(t.getId(), true);
        assertTrue(updated.isDone());

        var unknown = UUID.randomUUID();
        var ex = assertThrows(RuntimeException.class,
                () -> service.toggle(unknown, true));
        assertEquals("not found", ex.getMessage());
    }

    @Test
    @DisplayName("delete remove item")
    void delete_ok() {
        var repo = new TodoRepository();
        var service = new TodoService(repo);

        var req = new TodoCreateRequest();
        req.setTitle("A");
        var t = service.create(req);

        assertEquals(1, service.list().size());
        service.delete(t.getId());
        assertEquals(0, service.list().size());
    }
}
