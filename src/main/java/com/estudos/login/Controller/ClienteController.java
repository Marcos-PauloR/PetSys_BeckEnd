package com.estudos.login.Controller;

import com.estudos.login.Service.ClienteService;
import com.estudos.login.models.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ClienteController {

    @Autowired
    private final ClienteService clienteService;

    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> getClientes(){
        return ResponseEntity.ok().body(clienteService.getClientes());
    }

}
