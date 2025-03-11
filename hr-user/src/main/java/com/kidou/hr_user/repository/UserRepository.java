package com.kidou.hr_user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kidou.hr_user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
