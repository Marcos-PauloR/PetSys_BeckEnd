package com.estudos.login.Service;

import com.estudos.login.Repository.ClienteRepository;
import com.estudos.login.models.Cliente;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getClientes(){
        log.info("Buscando todos Clientes!");
        return clienteRepository.findAll();
    }

}
