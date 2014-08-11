package com.achui.quick.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.achui.quick.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
