package com.freyvik.jUnit_test.app.springboot.repositories;

import com.freyvik.jUnit_test.app.springboot.Cuenta;

import java.util.List;

public interface CuentaRepository {
    List<Cuenta> findAll();
    Cuenta findById(Long id);
    void update(Cuenta cuenta);
}
