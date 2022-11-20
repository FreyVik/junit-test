package com.freyvik.jUnit_test.app.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import java.math.BigDecimal;
import java.util.Properties;

public class BancoTest {

    Cuenta cuentaFrey;
    Cuenta cuentaJhon;

    @BeforeEach
    void instancerFreyCuente() {
        cuentaFrey = new Cuenta("Frey", new BigDecimal("1500.27"));
        cuentaJhon = new Cuenta("Jhon", new BigDecimal("1239.58"));
    }

    @Test
    void testTranferirDineroCuentas() {

        Banco banco = new Banco("Git bank");

        banco.transferir(cuentaJhon, cuentaFrey, new BigDecimal("500"));

        assertAll(
                () -> {
                    assertEquals(new BigDecimal("739.58"), cuentaJhon.getSaldo());
                },
                () -> {
                    assertEquals(new BigDecimal("2000.27"), cuentaFrey.getSaldo());
                });
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void onlyWindowsTest() {
        System.out.println("Tests de windows");
    }

    @Test
    @EnabledOnOs(OS.LINUX)
    void onlyLinuxTest() {
        System.out.println("Tests de linux");
    }

    @Test
    @EnabledOnOs({OS.LINUX, OS.WINDOWS})
    void onlyLinuxOrWindowsTest() {
        System.out.println("Tests de linux o windows");
    }

    @Test
    @EnabledOnJre(JRE.JAVA_8)
    void onlyOnJava8() {
        System.out.println("Tests de 1.8");
    }

    @Test
    @EnabledOnJre(JRE.JAVA_17)
    void onlyOnJava17() {
        System.out.println("Tests de 1.8");
    }

    @Test
    @EnabledIfSystemProperty(named = "user.country", matches = "ES")
    void testInSpain() {
        System.out.println("Test en ES");
    }

    @Test
    @EnabledIfSystemProperty(named = "user.country", matches = "EN")
    void testInEngland() {
        System.out.println("Test en EN");
    }
}
