package com.freyvik.jUnit_test.app.springboot.repositories;

import com.freyvik.jUnit_test.app.springboot.models.Banco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BancoRepository extends JpaRepository<Banco, Long> {
    // Implementeds by extension from JpaRepositoy
    /*
    List<Banco> findAll();
    Banco findById(Long id);
    void update(Banco cuenta);
     */
}
