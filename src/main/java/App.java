import br.com.letscode.entidades.Extrato;
import br.com.letscode.entidades.OperacaoBancaria;

import java.io.IOException;
import java.util.LinkedList;

public class App {
    public static void main(String[] args) {
        Extrato ex = new Extrato();
        try {
            ex.carregarListaOperacoes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String conta: ex.getContas()) {
            LinkedList<OperacaoBancaria> op =  ex.obterOperacoesDeUmaConta(conta);
            ex.gerarExtratoConta(op);
        }
    }
}
