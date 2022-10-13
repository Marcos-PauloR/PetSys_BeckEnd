package com.estudos.login.Service;

import com.estudos.login.Repository.CidadeRepository;
import com.estudos.login.models.Cidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    public CidadeRepository cidadeRepository;


}
