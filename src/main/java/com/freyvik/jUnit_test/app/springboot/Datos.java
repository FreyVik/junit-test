package com.freyvik.jUnit_test.app.springboot;

import java.math.BigDecimal;

public class Datos {
//    public static final Cuenta CUENTA_001 = new Cuenta(1L, "Frey", new BigDecimal("1500"));
//    public static final Cuenta CUENTA_002 = new Cuenta(2L, "Vik", new BigDecimal("2000"));
//    public static final Banco BANCO = new Banco(1L, "Git Bank", 0);

    public static Cuenta crearCuenta001() {
        return new Cuenta(1L, "Frey", new BigDecimal("1500"));
    }

    public static Cuenta crearCuenta002() {
        return new Cuenta(2L, "Vik", new BigDecimal("2000"));
    }

    public static Banco crearBanco() {
        return new Banco(1L, "Git Bank", 0);
    }
}
