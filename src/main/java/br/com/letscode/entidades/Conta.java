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
    private String titular;
    private String idConta;
    private String banco;
    private String agencia;
    private String conta;

}
