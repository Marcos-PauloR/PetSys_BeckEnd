package com.estudos.login.Service;

import com.estudos.login.Repository.PessoaRepository;
import com.estudos.login.models.Funcionario;
import com.estudos.login.models.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;


    public Pessoa savePessoa(Pessoa pessoa){
        return repository.save(pessoa);
    }
}
