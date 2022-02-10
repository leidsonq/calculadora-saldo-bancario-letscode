package br.com.letscode.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Conta {
    private String idConta;
    private String banco;
    private String agencia;
    private String conta;
    private Double saldo = 0.0;

    public void sacar(Double valor) {
        this.saldo = this.saldo - valor;
    }

    public void depositar(Double valor) {
        this.saldo = this.saldo + valor;
    }

    public void setIdConta(String idConta) {
        this.idConta = idConta;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }
}
