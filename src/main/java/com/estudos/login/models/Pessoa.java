package com.estudos.login.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
    private String email;
    private Date dataNascimento;

    @OneToOne(optional = false)
    private Pessoa pessoa;

    @OneToOne(optional = false)
    private Funcionario funcionario;

}
