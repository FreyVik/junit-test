package com.freyvik.jUnit_test.app.springboot.repositories;

import com.freyvik.jUnit_test.app.springboot.models.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    // Implementeds by extension from JpaRepositoy
    /*
    List<Cuenta> findAll();
    Cuenta findById(Long id);
    void update(Cuenta cuenta);
     */
    @Query("select c from Cuenta c where c.persona=?1")
    Optional<Cuenta> findByPersona(String persona);

}
