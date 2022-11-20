package com.freyvik.jUnit_test.app.models;

import com.freyvik.jUnit_test.app.exceptions.InsufficientMoneyException;

import java.math.BigDecimal;

public class Cuenta {
    private String persona;
    // With money variables, BigDecimal is more accurate than a float or double
    private BigDecimal saldo;

    public Cuenta(String persona, BigDecimal saldo) {
        this.persona = persona;
        this.saldo = saldo;
    }

    public void debito(BigDecimal monto) {
        if (this.saldo.compareTo(monto) < 0)
            throw new InsufficientMoneyException("Dinero insuficiente");

        this.saldo = this.saldo.subtract(monto);
    }

    public void credito(BigDecimal monto) {
        this.saldo = this.saldo.add(monto);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Cuenta)) {
            return false;
        }
        Cuenta c = (Cuenta) obj;
        if(this.persona == null || this.saldo == null) {
            return false;
        }

        return this.persona.equals(c.getPersona()) && this.saldo.equals(c.getSaldo());
    }

    public String getPersona() {
        return persona;
    }
    public void setPersona(String persona) {
        this.persona = persona;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }
    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}
