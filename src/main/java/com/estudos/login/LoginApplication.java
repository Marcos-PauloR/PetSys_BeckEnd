package com.estudos.login;

import com.estudos.login.Repository.*;
import com.estudos.login.Service.UserServiceImpl;
import com.estudos.login.models.*;
import com.estudos.login.models.enums.TipoCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class LoginApplication {


    public static void main(String[] args) {
        SpringApplication.run(LoginApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserServiceImpl userService, ClienteRepository clienteRepository, CidadeRepository cidadeRepository, EstadoRepository estadoRepository,
                          EnderecoRepository enderecoRepository, PessoaRepository pessoaRepository){
        return args ->{

          /*userService.addRoleToUser("alex", "ROLE_USER");
          userService.addRoleToUser("alex", "ROLE_MANAGER");
          userService.addRoleToUser("alex", "ROLE_USER");

          userService.addRoleToUser("marcos", "ROLE_MANAGER");
          userService.addRoleToUser("marcos", "ROLE_ADMIN");

          userService.addRoleToUser("gabriel", "ROLE_USER");
          userService.addRoleToUser("gabriel", "ROLE_ADMIN");*/
          /*  Estado est1 = new Estado(null, "Minas Gerais");


            Cidade cid1 = new Cidade(null, "Uberlândia", est1);

            est1.getCidades().addAll(Arrays.asList(cid1));
            estadoRepository.saveAll(Arrays.asList(est1));
            cidadeRepository.saveAll(Arrays.asList(cid1));

          Cliente cli = new Cliente(null, "12345678900", TipoCliente.PESSOAFISICA, "Marcos", "teste@gmail.com", Date.from(Instant.now()) );

          Endereco end1 = new Endereco(null, "22 de Abril", "100", "casa azul", "Flamboyant", "74000000", cli, cid1);

          cli.getEnderecos().add(end1);

          clienteRepository.save(cli);
          enderecoRepository.save(end1);
          */
        };
    }


}
