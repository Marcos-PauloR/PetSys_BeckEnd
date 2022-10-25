package com.estudos.login.Controller;


import com.estudos.login.Service.FuncionarioService;
import com.estudos.login.models.Cliente;
import com.estudos.login.models.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FuncionarioController {


    @Autowired
    private FuncionarioService funcionarioService;


    @RequestMapping(value = "/funcionarios", method = RequestMethod.GET)
    public ResponseEntity<List<Funcionario>> listaTodos(){
        List<Funcionario> lista = funcionarioService.getFuncioanrios();
        return ResponseEntity.ok().body(lista);
    }

    @RequestMapping(value = "/funcionario/{id}", method = RequestMethod.GET)
    public ResponseEntity<Funcionario> buscaFunc(@PathVariable Long id){
        Funcionario func = funcionarioService.getFuncionario(id);
        return ResponseEntity.ok().body(func);
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id){
        funcionarioService.deleteFuncionario(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/insere", method = RequestMethod.POST)
    public  ResponseEntity<Void> insere(@RequestBody Funcionario func){
        Funcionario funcionario = funcionarioService.saveFuncionario(func);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(funcionario.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
