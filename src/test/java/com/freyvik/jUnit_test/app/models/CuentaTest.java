
package com.freyvik.jUnit_test.app.models;

import com.freyvik.jUnit_test.app.exceptions.InsufficientMoneyException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CuentaTest {

    Cuenta cuenta;

    @BeforeAll
    static void initTests() {
        System.out.println("Inicializando tests");
    }

    @AfterAll
    static void finishTests() {
        System.out.println("Tests finalizados");
    }

    @BeforeEach
    void instancerFreyCuente() {
        cuenta = new Cuenta("Frey", new BigDecimal("1500.27"));
        System.out.println("Cuenta Frey creada");
    }

    @Test
    void testNombreCuenta() {
        assertEquals("Frey", cuenta.getPersona());
    }

    @Test
    void testSaldoCuenta() {
        assertEquals(1500.27, cuenta.getSaldo().doubleValue());
    }

    @Test
    void testReferenciaCuenta() {
        Cuenta cloneCuenta = new Cuenta("Frey", new BigDecimal("1500.27"));

        assertEquals(cuenta, cloneCuenta);
    }
    
    @Test
    void testDineroInsuficienteException() {
        Exception exception = assertThrows(InsufficientMoneyException.class, () -> {
           cuenta.debito(new BigDecimal("1501"));
        });

        assertEquals("Dinero insuficiente", exception.getMessage());
    }
}