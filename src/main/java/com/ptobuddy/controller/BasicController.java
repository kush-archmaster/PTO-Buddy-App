package com.ptobuddy.controller;

import com.ptobuddy.model.UserDetails;
import com.ptobuddy.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/pto-buddy-app/users")
public class BasicController {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @GetMapping("/health")
    public String checkHealth() {
        return "Service is Healthy";
    }

    @GetMapping
    public List<UserDetails> getUsers() {
        return userDetailsRepository.findAll();
    }

    @PostMapping(path = "/addUser")
    public String addUser() {
        UserDetails userDetails = new UserDetails();
        userDetails.setEmail("kushagra.upadhyay@lowes.com");
        userDetails.setFirstName("Kushagra");
        userDetails.setLastName("Upadhyay");
        userDetails.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));

        userDetailsRepository.save(userDetails);
        return "User added!";
    }
}
