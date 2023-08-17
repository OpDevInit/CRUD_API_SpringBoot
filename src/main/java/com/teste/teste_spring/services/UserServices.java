package com.teste.teste_spring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.teste_spring.models.User;
import com.teste.teste_spring.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserServices {

    // "Referéncia de um Construtor"
    @Autowired
    private UserRepository userRepository;

    @Autowired

    public User findById(Long id) {

        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException(
                "Usuário não encontrado! Id " + id + ", Tipo " + User.class.getName()));
    }

    @Transactional
    public User create(User obj) {
        obj.setId(null);
        obj = this.userRepository.save(obj);
        return obj;

    }

    @Transactional
    public User update(User obj) {
        User newObj = findById(obj.getId());
        newObj.setPassword((obj.getPassword()));
        return this.userRepository.save(newObj);
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel excluir,"
            + "há entidades relacionadas! Id: " + id + " , Tipo: " + User.class.getName());
        }
    }

}
