package com.estudos.login.Service;

import com.estudos.login.Repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CidadeService {

    @Autowired
    public CidadeRepository cidadeRepository;


}
