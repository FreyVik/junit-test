package com.freyvik.jUnit_test.app.springboot.models;

import com.freyvik.jUnit_test.app.springboot.exceptions.InsufficientMoneyException;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name="cuentas")
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String persona;
    private BigDecimal saldo;

    public Cuenta() {
    }

    public Cuenta(Long id, String persona, BigDecimal saldo) {
        this.id = id;
        this.persona = persona;
        this.saldo = saldo;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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

    public void debito(BigDecimal monto) {
        if (monto.compareTo(this.saldo) > 0)
            throw new InsufficientMoneyException("Monto is greater than saldo");

        this.saldo = this.saldo.subtract(monto);
    }

    public void credito(BigDecimal monto) {
        this.saldo = this.saldo.add(monto);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cuenta cuenta = (Cuenta) o;
        return Objects.equals(id, cuenta.id) && Objects.equals(persona, cuenta.persona) && Objects.equals(saldo, cuenta.saldo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, persona, saldo);
    }
}
