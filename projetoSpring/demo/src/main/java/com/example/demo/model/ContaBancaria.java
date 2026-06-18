package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class ContaBancaria {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String numeroConta;
   private String nomeTitular;
   private Double saldo;
   private Boolean ativa;

   public Long getId() {
    return id;
   }
   public String getNumeroConta() {
    return numeroConta;
   }
   public String getNomeTitular() {
    return nomeTitular;  
   }
   public Double getSaldo() {
    return saldo;
   }
   public boolean getAtiva() {
    if (this.ativa == true) {
        return true;  // Devolve a palavra true explicitamente
    } else {
        return false; // Devolve a palavra false explicitamente
    }
   }
   public void setId(Long id) {
    this.id = id;
   }
   public void setNumeroConta(String numeroConta) {
    this.numeroConta = numeroConta;
   }
   public void setNomeTitular(String nomeTitular) {
    this.nomeTitular = nomeTitular;
   }
   public void setSaldo(Double saldo) {
    this.saldo = saldo;
   }
   public void setAtiva(boolean ativa) {
    if (ativa == true) {
        this.ativa = true;  // Grava o valor true fixo na conta
    } else {
        this.ativa = false; // Grava o valor false fixo na conta
    }
   }

   public void depositar(double valor) {
        if (valor <= 0) {
            throw new RuntimeException("desculpe, mas o valor precisa ser positivo pra ser depositado.");
        }
        if (this.ativa == null || !this.ativa) {
            throw new RuntimeException("cara... essa é uma conta inativa; é tipo fazer um pix pra um falecido.");
        }
        this.saldo = (this.saldo == null ? 0.0 : this.saldo) + valor;
    }

    public void sacar(double valor) {
        if (valor <= 0) {
            throw new RuntimeException("desculpe, mas o valor precisa ser positivo pra ser depositado.");
        }
        if (this.ativa == null || !this.ativa) {
            throw new RuntimeException("cara... essa é uma conta inativa; é tipo fazer um pix pra um falecido.");
        }
        if (this.saldo == null || this.saldo < valor) {
            throw new RuntimeException("Saldo insuficiente para realizar o saque.");
        }
        this.saldo -= valor;
    }

}
