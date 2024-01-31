package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.userService;

@SpringBootApplication
@CrossOrigin("http://localhost:8080")
@RestController
public class DemoApplication {
    public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
    }
    @Autowired
    private userService u;

    @GetMapping("/get")
    public String getUsers() {
      return u.get();
    }

    // @GetMapping("/{id}")
    // public User getuser(@PathVariable String id){
    //   return u.getUser(id);
    // }

    @PostMapping("/")
    public String createuser(@RequestBody User user){
      System.out.println("get");
      return u.save(user);
    }
    
}