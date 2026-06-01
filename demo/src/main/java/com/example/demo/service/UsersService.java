package com.example.demo.service;

import com.example.demo.models.Users;
import com.example.demo.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final static Logger logger = LoggerFactory.getLogger(UsersService.class);

    public UsersService(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    public Users login(String username, String password){
        logger.info("Login started in the service for {}.", username);
        Users user = usersRepository.findByUsername(username);
        if(user == null)
            return null;
        if (user.getPassword().equals(password))
            return user;
        logger.info("Error occurred in login.");
        return null;
    }

    public Users findById(Integer id){
        logger.info("Find by id started.");
        return usersRepository.findById(id).orElse(null);
    }

    public Users register(String username, String password){
        logger.info("Register started.");
        Users user = new Users();
        user.setUsername(username);
        user.setPassword(password);
        return usersRepository.save(user);
    }
}
