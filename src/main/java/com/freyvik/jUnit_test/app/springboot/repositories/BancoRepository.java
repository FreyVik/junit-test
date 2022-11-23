package com.freyvik.jUnit_test.app.springboot.repositories;

import com.freyvik.jUnit_test.app.springboot.Banco;

import java.util.List;

public interface BancoRepository {
    List<Banco> findAll();
    Banco findById(Long id);
    void update(Banco cuenta);
}
