package com.spring.repo;


import com.spring.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users, Integer> {
   Users findByUsername(String username);

}
