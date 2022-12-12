package com.estudos.login.Controller;


import com.estudos.login.Service.FuncionarioService;
import com.estudos.login.models.Funcionario;
import com.estudos.login.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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


    @RequestMapping(value = "/funcionarionome/{nome}", method = RequestMethod.GET)
    public ResponseEntity<Funcionario> listaTodos(@PathVariable String nome){
        Funcionario func = funcionarioService.getByName(nome);
        return ResponseEntity.ok().body(func);
    }

    @RequestMapping(value = "/todosfuncionarios", method = RequestMethod.GET)
    public ResponseEntity<List<Funcionario>> listaTodos(){
        List<Funcionario> func = funcionarioService.getAll();
        return ResponseEntity.ok().body(func);
    }

    @RequestMapping(value = "/funcionarioid/{id}", method = RequestMethod.GET)
    public ResponseEntity<Funcionario> buscaFunc(@PathVariable Long id){
        Funcionario func = funcionarioService.getFuncionario(id);
        return ResponseEntity.ok().body(func);
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception{
        funcionarioService.deleteFunc(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/insere", method = RequestMethod.POST)
    public  ResponseEntity<Void> insere(@RequestBody Funcionario func) throws Exception{
        User funcionario = funcionarioService.saveFuncionario(func);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(funcionario.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/updateFuncionario", method = RequestMethod.PUT)
    public  ResponseEntity<Void> update(@RequestBody Funcionario func){
        Funcionario funcionario = funcionarioService.updateFunc(func);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(funcionario.getId()).toUri();
        return ResponseEntity.status(HttpStatus.valueOf(204)).build();
    }

}
