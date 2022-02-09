package br.com.letscode.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Getter
@Setter
@NoArgsConstructor
public class Extrato {

    List<OperacaoBancaria> listaOperacoes = new ArrayList<>();

    public void carregarListaOperacoes() throws IOException {
        FileInputStream fileInputStream = null;
        File arquivoOperacoes = new File("C:/Users/55319/Documents/Projetos-java-lets-code/calculadora-saldo-bancario-letscode/src/main/java/br/com/letscode/arquivos/operacoes.csv");

        if (arquivoOperacoes.exists() && arquivoOperacoes.isFile()){
            try {
                fileInputStream = new FileInputStream(arquivoOperacoes);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String linha;
                while ((linha = bufferedReader.readLine()) !=null){
                    String[] dados = bufferedReader.readLine().split(",");
                    Conta conta = new Conta(dados[5], dados[1],dados[2],dados[3],dados[4]);
                    OperacaoBancaria operacao = new OperacaoBancaria(
                            conta, dados[6],Double.parseDouble(dados[7]), dados[0]);
                    listaOperacoes.add(operacao);
                }


            } catch (FileNotFoundException e) {
                System.out.println("Erro na leitura do arquivo");
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                fileInputStream.close();
            }
        }

    }

    public void imprimirLista() {
        System.out.println("NOME"+listaOperacoes.get(0).getConta().getTitular());
    }
}
