package com.estudos.login.Repository;

import com.estudos.login.models.Funcionario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    //@Query("SELECT p FROM FUNCIONARIO p WHERE p.ID = ?1")
    //Funcionario findByUser(Long id);

    @Query("SELECT u.nome FROM funcionario u ")
    List<String> findAllNames();

    @Query("SELECT u FROM funcionario u WHERE u.nome LIKE '%nome%'")
    Funcionario findByName(@Param("nome")String name);

    @Query("SELECT u FROM funcionario u where u.id = id")
    Funcionario findId(@Param("id")Long id);
}
