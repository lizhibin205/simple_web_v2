package com.bytrees.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bytrees.service.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
