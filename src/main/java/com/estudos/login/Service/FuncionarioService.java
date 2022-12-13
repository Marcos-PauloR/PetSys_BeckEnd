package com.estudos.login.Service;

import com.estudos.login.Repository.FuncionarioRepository;
import com.estudos.login.models.Funcionario;
import com.estudos.login.models.User;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;
    @Autowired
    private UserServiceImpl userRepository;

    public User saveFuncionario(Funcionario funcionario) throws Exception {
        log.info("Salvando novo Funcionario :{}", funcionario.getNome());
        funcionario.setId(null);
        User user = funcionario.getUser();
        Funcionario func = funcionario;
        user.setFuncionario(funcionario);
        func.setUser(user);
        return userRepository.saveUser(user);
    }

    public Funcionario getFuncionario(Long id) {
        Optional<Funcionario> funcionario = repository.findById(id);
        log.info("Busca Funcionario: {}", funcionario.get().getNome());
        return funcionario.orElseThrow();
    }

    public Funcionario getByName(String name) {
        log.info("Buscando Todos Funcionarios");
        return repository.findByName(name);
    }

    public List<Funcionario> getAll(){
        return repository.findAll();
    }

    public void deleteFunc(Long id) throws Exception {
        Funcionario func = getFuncionario(id);
        try {   
            if(func.getUser() != null){
                userRepository.deleteById(func.getUser().getId());     
            } 
            repository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Funcionario updateFunc(Funcionario func) {
        Funcionario oldFunc = getFuncionario(func.getId());
        log.info("oldFunc"+oldFunc.getId());
        oldFunc.setCargo(func.getCargo());
        oldFunc.setDataCadastro(func.getDataCadastro());
        oldFunc.setMatricula(func.getMatricula());
        oldFunc.setNome(func.getNome());
        oldFunc.setSalario(func.getSalario());
        oldFunc.setEmail(func.getEmail());
        oldFunc.setCpf(func.getCpf());
        oldFunc.setAtivo(func.isAtivo());
        return repository.save(oldFunc);
    }


}
