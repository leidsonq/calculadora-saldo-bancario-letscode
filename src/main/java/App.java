import br.com.letscode.entidades.Extrato;

import java.io.IOException;

public class App {
    public static void main(String[] args) {
        Extrato ex = new Extrato();
        try {
            ex.carregarListaOperacoes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ex.imprimirLista();
    }
}
