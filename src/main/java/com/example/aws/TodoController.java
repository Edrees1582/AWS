package com.example.aws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

import java.util.*;

@RestController
@RequestMapping("/")
public class TodoController {
    private final TodoRepository todoRepository;
    @Autowired
    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping
    public Map<String, Object> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("todos", todos);
        try {
            InetAddress ip = InetAddress.getLocalHost();
            response.put("Server IP", ip.getHostAddress());
            System.out.println("Server IP address: " + ip.getHostAddress());
        } catch (UnknownHostException e) {
            System.out.println("Could not get server IP address: " + e.getMessage());
        }
        return response;
    }
}
