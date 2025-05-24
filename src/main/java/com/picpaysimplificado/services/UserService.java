package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.UserType;
import com.picpaysimplificado.dtos.UserDTO;
import com.picpaysimplificado.infra.InsufficientBalanceException;
import com.picpaysimplificado.infra.NonAuthorizedUserType;
import com.picpaysimplificado.infra.NotFoundUser;
import com.picpaysimplificado.infra.UserAlreadyExistsException;
import com.picpaysimplificado.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount){
        if(sender.getUserType() == UserType.MERCHANT){
            throw new NonAuthorizedUserType();
        }

        if(sender.getBalance().compareTo(amount) < 0){
            throw new InsufficientBalanceException();
        }
    }

    public User findUserById(Long id){
        return this.repository.findUserById(id).orElseThrow(() -> new NotFoundUser());
    }

    public void saveUser(User user){
        this.repository.save(user);
        System.out.println("Document salvo: " + user.getDocument());
    }

    public User createUser(UserDTO data){

        if (repository.existsByDocument(data.document()) || repository.existsByEmail(data.email())) {
            throw new UserAlreadyExistsException();
        }

        User newUser = new User(data);
        this.saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUsers() {
        return this.repository.findAll();
    }
}
