package com.freyvik.jUnit_test.app.models;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CuentaTest {
    @Test
    void testNombreCuenta() {
        Cuenta cuenta = new Cuenta("Gonzalo", new BigDecimal("1500.27"));
        assertEquals("Gonzalo", cuenta.getPersona());
    }

    @Test
    void testSaldoCuenta() {
        Cuenta cuenta = new Cuenta("Gonzalo", new BigDecimal("1500.27"));
        assertEquals(1500.27, cuenta.getSaldo().doubleValue());
    }

    @Test
    void testReferenciaCuenta() {
        Cuenta cuenta = new Cuenta("Jhon", new BigDecimal("890.58"));
        Cuenta cloneCuenta = new Cuenta("Jhon", new BigDecimal("890.58"));

        assertEquals(cuenta, cloneCuenta);
    }
}