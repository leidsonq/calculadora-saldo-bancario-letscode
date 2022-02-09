package br.com.letscode.entidades;

import br.com.letscode.entidades.enums.TipoOperacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OperacaoBancaria {
    private Conta conta;
    private String tipo;
    private double valor;
    private String data;

}
