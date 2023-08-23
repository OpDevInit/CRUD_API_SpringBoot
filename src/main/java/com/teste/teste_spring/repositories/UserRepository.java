package com.teste.teste_spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.teste_spring.models.User;


public interface UserRepository extends JpaRepository<User, Long>{
    
}
