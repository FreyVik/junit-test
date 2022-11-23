
package com.freyvik.jUnit_test.app.simple_junit.models.models;

import com.freyvik.jUnit_test.app.simple_junit.exceptions.InsufficientMoneyException;
import com.freyvik.jUnit_test.app.simple_junit.models.Cuenta;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

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
    void testNombreCuenta(TestReporter testReporter) {
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

    // With assume we can check nested things like external API
    // If assume is false, the test is disabled automatically
    @Test
    void testAssumptions() {
        boolean isDev = "dev".equals(System.getProperty("env"));

        assumeTrue(isDev);
        Cuenta cloneCuenta = new Cuenta("Frey", new BigDecimal("1500.27"));

        assertEquals(cuenta, cloneCuenta);
    }

    @Tag("timeout")
    @Test
    @Timeout(5)
    void testTimeout() throws InterruptedException {
        TimeUnit.SECONDS.sleep(6);
    }

    @Tag("timeout")
    @Test
    @Timeout(5)
    void testNoTimeout() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
    }
}