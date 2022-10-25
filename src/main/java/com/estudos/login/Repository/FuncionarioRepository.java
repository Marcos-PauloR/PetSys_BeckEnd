package com.estudos.login.Repository;

import com.estudos.login.models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    //@Query("SELECT p FROM FUNCIONARIO p WHERE p.ID = ?1")
    //Funcionario findByUser(Long id);
}
