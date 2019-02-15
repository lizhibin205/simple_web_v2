package com.bytrees.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bytrees.service.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

}
