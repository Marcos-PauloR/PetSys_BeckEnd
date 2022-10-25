package com.estudos.login.Service;

import com.estudos.login.Repository.FuncionarioRepository;
import com.estudos.login.Repository.RoleRepository;
import com.estudos.login.Repository.UserRepository;
import com.estudos.login.models.User;
import com.estudos.login.models.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl  implements UserService, UserDetailsService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;

    @Autowired
    private final FuncionarioRepository repository;

    private final PasswordEncoder passwordEncoder;
    @Override
    public User saveUser(User user) {
        log.info("Salvando novo Usuario :{}", user.getNome());
        //repository.findById(user.getFuncionario().getId());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Salvando nova Role: {}", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adicionando Role: {} Ao Usuario: {}", username, roleName);
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        log.info("Busca usuario: {}", username);
        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("Buscando Todos Usuarios");
        return userRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(user == null ){
            log.error("Usuario não encontrado");
            throw new UsernameNotFoundException("Usuario no encontrado");
        }else {
            log.info("Usuario encontrado: "+ user.getUsername());
            log.info("senha: {}", user.getPassword());
        }
        Collection<SimpleGrantedAuthority> roles = new ArrayList<>();
        user.getRoles().forEach( role -> roles.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
    }

}