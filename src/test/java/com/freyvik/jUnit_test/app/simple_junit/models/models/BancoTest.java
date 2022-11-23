package com.freyvik.jUnit_test.app.simple_junit.models.models;

import static org.junit.jupiter.api.Assertions.*;

import com.freyvik.jUnit_test.app.simple_junit.models.Banco;
import com.freyvik.jUnit_test.app.simple_junit.models.Cuenta;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

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

    @RepeatedTest(value = 5, name = "Repeated {currentRepetition}")
    void testRepeatingTransfer(RepetitionInfo info) {
        Banco banco = new Banco("Git bank");

        banco.transferir(cuentaJhon, cuentaFrey, new BigDecimal("500"));
        assertEquals(new BigDecimal("739.58"), cuentaJhon.getSaldo());
        System.out.println("Tranfer #" + info.getCurrentRepetition());
    }

    @ParameterizedTest
    @ValueSource(strings = {"100", "250", "500", "1239.58", "1239.59"})
    void testParametrizedTransferHigherThanSaldo(String monto) {
        Banco banco = new Banco("Git bank");

        System.out.println("Saldo before transer: " + cuentaJhon.getSaldo());
        banco.transferir(cuentaJhon, cuentaFrey, new BigDecimal(monto));
        System.out.println("Saldo after transfer: " + cuentaJhon.getSaldo());
        assertTrue(cuentaJhon.getSaldo().compareTo(BigDecimal.ZERO) >= 0);
        System.out.println("--------------");
    }

    @Nested
    class OSTests {
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
    }

    @Nested
    class JRETests {
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
    }

    @Nested
    class SystemVariablesTest {
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
}
