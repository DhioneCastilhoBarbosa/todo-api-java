package com.example.todo_api.service;

import com.example.todo_api.dto.TodoCreateRequest;
import com.example.todo_api.model.Todo;
import com.example.todo_api.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class TodoService {
    private final TodoRepository repo;

    public TodoService(TodoRepository repo) { this.repo = repo; }

    public List<Todo> list() {
        return repo.findAll();
    }

    public Todo create(TodoCreateRequest req) {
        Todo t = new Todo(UUID.randomUUID(), req.getTitle(), false, Instant.now());
        return repo.save(t);
    }

    public Todo toggle(UUID id, boolean done) {
        Todo t = repo.findById(id).orElseThrow(() -> new RuntimeException("not found"));
        t.setDone(done);
        return repo.save(t);
    }

    public void delete(UUID id) {
        repo.deleteById(id);
    }
}
