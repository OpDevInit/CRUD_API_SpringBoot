package com.teste.teste_spring.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.teste.teste_spring.models.Task;
import com.teste.teste_spring.models.User;

public interface TaskRepository extends JpaRepository<Task, Long> {
    //Método Convencional
    List<Task> findByUser_id(Long id);

    //SQL + Spring
    //@Query( value = "SELECT t FROM Task t WHERE t.user.id = :id ")
    //List<Task> findByUser_Id(@Param("id")Long id);

    //SQL puro
    //@Query(value = "SELECT * FROM task t WHERE t.user_id = :id", nativeQuery = true)
    //List<Task> findByUser_Id(@Param("id")Long id);


}
