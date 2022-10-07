package com.estudos.login;

import com.estudos.login.Service.UserServiceImpl;
import com.estudos.login.models.User;
import com.estudos.login.models.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class LoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    CommandLineRunner run(UserServiceImpl userService){
        return args ->{
          userService.saveRole(new Role(null, "ROLE_USER"));
          userService.saveRole(new Role(null, "ROLE_MANAGER"));
          userService.saveRole(new Role(null, "ROLE_ADMIN"));


          userService.saveUser(new User(null, "Alex","alex","1234", new ArrayList<>()));
          userService.saveUser(new User(null, "Gabriel","gabriel","1234", new ArrayList<>()));
          userService.saveUser(new User(null, "Marcos Paulo","marcos","1234", new ArrayList<>()));


          userService.addRoleToUser("alex", "ROLE_USER");
          userService.addRoleToUser("alex", "ROLE_MANAGER");
          userService.addRoleToUser("alex", "ROLE_USER");

          userService.addRoleToUser("marcos", "ROLE_MANAGER");
          userService.addRoleToUser("marcos", "ROLE_ADMIN");

          userService.addRoleToUser("gabriel", "ROLE_USER");
          userService.addRoleToUser("gabriel", "ROLE_ADMIN");
        };
    }


}
