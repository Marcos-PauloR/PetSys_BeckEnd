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

import java.lang.StackWalker.Option;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl  implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private final FuncionarioRepository repository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) throws Exception {
        log.info("Salvando novo Usuario :{}", user.getUsername());
        user.setRoles(null);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) throws Exception{
        log.info("Salvando nova Role: {}", role.getName());
        if(roleRepo.findByName(role.getName()) != null){
            throw new Exception("Role Já Existente");
        }
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adicionando Role: {} Ao Usuario: {}", username, roleName);
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }

    public void deleteById(Long id){
        User user = getUserById(id);
        userRepo.delete(user);
    }

    @Override
    public User getUser(String username) {
        log.info("Busca usuario: {}", username);
        return userRepo.findByUsername(username);
    }

    public User getUserById(Long userId) {
        log.info("Busca usuario: {}",userRepo.findById(userId).get().getUsername());
        Optional<User> user = userRepo.findById(userId);
        return user.orElseThrow();
    }

    @Override
    public List<User> getUsers() {
        log.info("Buscando Todos Usuarios");
        return userRepo.findAll();
    }

    public User updateUser(User newUser){
        User oldUser = getUserById(newUser.getId());
        oldUser.setUsername(newUser.getUsername());
        if(newUser.getPassword() !=null){
            oldUser.setPassword(passwordEncoder.encode( newUser.getPassword()));
        }
        oldUser.setRoles(newUser.getRoles());
        return userRepo.save(oldUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(username == ""){
            log.error("Usuario vazio: "+username);
        }
        if(user == null ){
            log.error("Usuario não encontrado");
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
            log.info("Usuario encontrado: "+ user.getUsername());
            log.info("senha: {}", user.getPassword());
            Collection<SimpleGrantedAuthority> roles = new ArrayList<>();
            user.getRoles().forEach( role -> roles.add(new SimpleGrantedAuthority(role.getName())));
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
        
    }

}