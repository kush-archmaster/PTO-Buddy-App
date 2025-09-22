package com.ptobuddy.controller;

import com.ptobuddy.model.User;
import com.ptobuddy.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/pto-buddy-app/v1/users")
public class BasicController {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @GetMapping
    public List<User> getUsers() {
        return userDetailsRepository.findAll();
    }

    @PostMapping(path = "/addUser")
    public String addUser() {
        User user = new User();
        user.setEmail("kushagra.upadhyay@lowes.com");
        user.setFirstName("Kushagra");
        user.setLastName("Upadhyay");
        user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));

        userDetailsRepository.save(user);
        return "User added!";
    }
}
