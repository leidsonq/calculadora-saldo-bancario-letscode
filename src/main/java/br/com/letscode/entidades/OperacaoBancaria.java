package br.com.letscode.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperacaoBancaria {
    private String operador;
    private Conta conta;
    private String tipo;
    private double valor;
    private String data;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OperacaoBancaria that = (OperacaoBancaria) o;

        if (Double.compare(that.valor, valor) != 0) return false;
        if (operador != null ? !operador.equals(that.operador) : that.operador != null) return false;
        if (conta != null ? !conta.equals(that.conta) : that.conta != null) return false;
        if (tipo != null ? !tipo.equals(that.tipo) : that.tipo != null) return false;
        return data != null ? data.equals(that.data) : that.data == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = operador != null ? operador.hashCode() : 0;
        result = 31 * result + (conta != null ? conta.hashCode() : 0);
        result = 31 * result + (tipo != null ? tipo.hashCode() : 0);
        temp = Double.doubleToLongBits(valor);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

}
