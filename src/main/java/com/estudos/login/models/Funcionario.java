package com.estudos.login.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "funcionario")
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String matricula;
    private String cargo;
    private Date dataCadastro;
    private String nome;
    private String cpf;
    @Column( columnDefinition = "FLOAT(9,2)")
    private Double salario;
    private String email;
    private boolean ativo;

    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.PERSIST)
    @JoinTable(name = "user_func", joinColumns = 
        {@JoinColumn(name="user_id", referencedColumnName = "id")},
        inverseJoinColumns = 
        {@JoinColumn(name="funcionario_id", referencedColumnName = "id")} )
    private User user;

}
