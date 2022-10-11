package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User>  findAll ();
    User findOne(long id);
    void addUser (User user);
    void deleteUser (long id);
    void updateUser (User user);
    List <Role> getSetOfRoles (List <String> rolesId);
}
