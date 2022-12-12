package com.estudos.login.models;

import com.estudos.login.models.enums.TipoCliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String cpfOuCnpj;
    private Integer tipo;


    @OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST)
    private List<Endereco> enderecos = new ArrayList<>();

    @OneToOne
    private Pessoa pessoa;

    public Cliente(Long id, String cpfOuCnpj, TipoCliente tipo, Pessoa pessoa) {
        this.id = id;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipo = (tipo == null) ? null : tipo.getCod();
        this.pessoa = pessoa;

    }

}
