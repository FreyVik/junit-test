package com.freyvik.jUnit_test.app.springboot;

import static org.junit.jupiter.api.Assertions.*;

import com.freyvik.jUnit_test.app.springboot.models.Cuenta;
import com.freyvik.jUnit_test.app.springboot.repositories.CuentaRepository;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Tag("JPA")
@DataJpaTest
public class JpaTest {
    @Autowired
    CuentaRepository cuentaRepository;

    @Test
    void testFindById() {
        Optional<Cuenta> cuenta = cuentaRepository.findById(1L);

        assertTrue(cuenta.isPresent());
        assertEquals("Frey", cuenta.orElseThrow().getPersona());
    }

    @Test
    void testFindByPersona() {
        Optional<Cuenta> cuenta = cuentaRepository.findByPersona("Frey");

        assertTrue(cuenta.isPresent());
        assertEquals("Frey", cuenta.orElseThrow().getPersona());
        assertEquals("1500.00", cuenta.orElseThrow().getSaldo().toPlainString());
    }

    @Test
    void testFindByPersonaException() {
        Optional<Cuenta> cuenta = cuentaRepository.findByPersona("Invent");

        assertThrows(NoSuchElementException.class, cuenta::orElseThrow);
        assertFalse(cuenta.isPresent());
    }

    @Test
    void testFindAll() {
        List<Cuenta> cuentas = cuentaRepository.findAll();

        assertFalse(cuentas.isEmpty());
        assertEquals(2, cuentas.size());
    }

    @Test
    void testSave() {
        // Given
        Cuenta cuentaInvent = new Cuenta(null, "Invent", new BigDecimal("2500"));
        // ¹ cuentaRepository.save(cuentaInvent);

        // When
        // ¹ Cuenta savedCuenta = cuentaRepository.findByPersona("Invent").orElseThrow();
        // ¹ Cuenta savedCuenta = cuentaRepository.findById(save.getId()).orElseThrow();
        Cuenta save = cuentaRepository.save(cuentaInvent);

        // Then
        assertEquals("Invent", cuentaInvent.getPersona());
        assertEquals("2500", cuentaInvent.getSaldo().toPlainString());
        //assertEquals(3, cuentaInvent.getId());
    }

    @Test
    void testUpdate() {
        // Given
        Cuenta cuentaInvent = new Cuenta(null, "Invent", new BigDecimal("2500"));

        // When
        Cuenta save = cuentaRepository.save(cuentaInvent);

        // Then
        assertEquals("Invent", save.getPersona());
        assertEquals("2500", save.getSaldo().toPlainString());

        // When
        save.setSaldo(new BigDecimal("3000"));
        Cuenta updatedCuenta = cuentaRepository.save(save);

        // Then
        assertEquals("Invent", updatedCuenta.getPersona());
        assertEquals("3000", updatedCuenta.getSaldo().toPlainString());
    }

    @Test
    void testDelete() {
        Cuenta cuenta = cuentaRepository.findById(2L).orElseThrow();
        assertEquals("Vik", cuenta.getPersona());

        cuentaRepository.delete(cuenta);

        assertThrows(NoSuchElementException.class, () -> {
            cuentaRepository.findByPersona("Vik").orElseThrow();
            cuentaRepository.findById(1L);
        });

        assertEquals(1, cuentaRepository.findAll().size());
    }
}
