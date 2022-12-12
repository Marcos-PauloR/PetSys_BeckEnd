package com.estudos.login.Service;

import com.estudos.login.Repository.EstadoRepository;
import com.estudos.login.models.Estado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository repository;

    public List<Estado> getEstados(){
        return repository.findAll();
    }

    /*public Estado getEstado(String estado){
        return  repository.findByNome(estado);
    }*/

}
