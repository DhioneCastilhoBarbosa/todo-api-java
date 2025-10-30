package com.example.todo_api.controller;

import com.example.todo_api.dto.TodoCreateRequest;
import com.example.todo_api.dto.TodoResponse;
import com.example.todo_api.model.Todo;
import com.example.todo_api.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping
    public List<TodoResponse> list() {
        return service.list().stream().map(this::toResp).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse create(@Valid @RequestBody TodoCreateRequest req) {
        return toResp(service.create(req));
    }

    @PatchMapping("/{id}")
    public TodoResponse toggle(@PathVariable UUID id, @RequestParam boolean done) {
        return toResp(service.toggle(id, done));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

    private TodoResponse toResp(Todo t) {
        return new TodoResponse(t.getId(), t.getTitle(), t.isDone(), t.getCreatedAt());
    }
}
