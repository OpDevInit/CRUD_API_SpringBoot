package com.teste.teste_spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.teste_spring.models.Task;
import com.teste.teste_spring.models.User;
import com.teste.teste_spring.repositories.TaskRepository;

import jakarta.transaction.Transactional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userServices;

    public Task findById(Long id){
        Optional<Task> task = this.taskRepository.findById(id);
        return task.orElseThrow(()-> new RuntimeException(
            "Tarefa não encontrada!Id: "+id+", Tipo: "+Task.class.getName()
            ));
    }

    public List<Task> findAllByUserId(Long userId){
        List<Task> task = this.taskRepository.findByUser_id(userId);
        return task;
    }   

    @Transactional
    public Task create(Task obj){
        User user = this.userServices.findById(obj.getUser().getId());
        obj.setId(null);
        obj.setUser(user);
        obj = this.taskRepository.save(obj);
        return obj;
    }

    @Transactional
    public Task update(Task obj){
        Task newObj = findById(obj.getId());
        newObj.setDescription(obj.getDescription());
        return this.taskRepository.save(newObj);
    }

    public void delete(Long id){
        Task task = findById(id);
        try {
            this.taskRepository.delete(task);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel excluir,"
            + "há entidades relacionadas! Id: " + id + " , Tipo: " + User.class.getName());
        }
    }
}
