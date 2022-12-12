package com.estudos.login.Service;

import com.estudos.login.models.User;
import com.estudos.login.models.Role;

import java.util.List;

public interface UserService {
    User saveUser(User user) throws Exception;
    Role saveRole(Role role) throws Exception;
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();

}
