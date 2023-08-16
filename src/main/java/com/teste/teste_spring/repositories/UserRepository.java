package com.teste.teste_spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teste.teste_spring.models.User;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
}
