package com.ptobuddy.repository;

import com.ptobuddy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserDetailsRepository extends JpaRepository<User, UUID> {

}
