package com.estudos.login.Service;

import com.estudos.login.Repository.FuncionarioRepository;
import com.estudos.login.Repository.UserRepository;
import com.estudos.login.models.Funcionario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;
    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;


    public Funcionario saveFuncionario(Funcionario funcionario){
        //log.info("Salvando novo Funcionario :{}", funcionario.getUser().getNome());
        userRepository.save(funcionario.getUser());
        return repository.save(funcionario);
    }

    public Funcionario getFuncionario(Long id) {
        Optional<Funcionario> funcionario = repository.findById(id);
        //log.info("Busca Funcionario: {}", funcionario.get().getUser().getNome());
        return funcionario.orElseThrow();
    }


    public List<Funcionario> getFuncioanrios() {
        log.info("Buscando Todos Funcinarios");
        return repository.findAll();
    }


    public void deleteFunc(Long id) throws Exception {
      getFuncionario(id);
      try {
          repository.deleteById(id);
      }catch (Exception e){
          throw new Exception(e.getMessage());
      }
    }
    

    public void deleteFuncionario(Long id){
       repository.deleteById(id);
    }


}
