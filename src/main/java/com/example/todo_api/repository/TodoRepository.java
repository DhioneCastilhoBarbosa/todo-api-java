package com.example.todo_api.repository;

import com.example.todo_api.model.Todo;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TodoRepository {
    private final Map<UUID, Todo> db = new ConcurrentHashMap<>();

    public List<Todo> findAll() {
        return new ArrayList<>(db.values());
    }

    public Optional<Todo> findById(UUID id) {
        return Optional.ofNullable(db.get(id));
    }

    public Todo save(Todo t) {
        db.put(t.getId(), t);
        return t;
    }

    public void deleteById(UUID id) {
        db.remove(id);
    }


}