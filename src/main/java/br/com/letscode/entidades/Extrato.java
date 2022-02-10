package br.com.letscode.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class Extrato {

    List<OperacaoBancaria> listaOperacoes = new ArrayList<>();
    List<String> contas = new ArrayList<>();

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
                    Conta conta = new Conta(dados[1],dados[2],dados[3],dados[4], null);
                    OperacaoBancaria operacao = new OperacaoBancaria(
                            dados[5], conta, dados[6],Double.parseDouble(dados[7]), dados[0]);
                    listaOperacoes.add(operacao);
                    if (!verificarSeContaExiste(dados[1])) {
                        contas.add(dados[1]);
                    }
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

    public LinkedList<OperacaoBancaria> obterOperacoesDeUmaConta(String idConta) {
        LinkedList<OperacaoBancaria> operacoesConta = new LinkedList<>();

        for (OperacaoBancaria op: listaOperacoes) {
            if (op.getConta().getIdConta().equals(idConta)){
                if(!verificarOperacaoDuplicada(operacoesConta, op)) {
                    operacoesConta.add(op);
                }
            }
        }
        return  operacoesConta;
    }

    public boolean verificarSeContaExiste (String conta) {
        for (String c: contas) {
            if(c.equals(conta)) {
                return true;
            }
        }
        return false;
    }

    public boolean verificarOperacaoDuplicada(LinkedList<OperacaoBancaria> listaOperacoes,OperacaoBancaria operacao) {
        for (OperacaoBancaria op: listaOperacoes) {
            if(op.equals(operacao)){
                return true;
            }
        }
        return false;
    }

    public void gerarExtratoConta(LinkedList<OperacaoBancaria> operacoes) {

        FileOutputStream fileOutputStream = null;

        File file = new File("C:/Users/55319/Documents/Projetos-java-lets-code/calculadora-saldo-bancario-letscode/src/main/java/br/com/letscode/arquivos/extrato"+operacoes.get(0).getConta().getConta()+".txt");
        try {
            fileOutputStream = new FileOutputStream(file);

            for (OperacaoBancaria op: operacoes) {
                byte[] dados = obterDados(op);
                fileOutputStream.write(dados);
            }

            byte[] saldo = atualizarSaldoConta(operacoes.get(0).getConta().getIdConta());
            fileOutputStream.write(saldo);

            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Falha na escrita do arquivo!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public byte[] obterDados(OperacaoBancaria op) {
        StringBuilder builder = new StringBuilder();
        builder.append("Banco: "+op.getConta().getBanco())
                .append("\nOperação Bancária:")
                .append("\n").append("Operador: "+op.getOperador())
                .append("\nTipo: "+op.getTipo())
                .append("\nValor: "+op.getValor())
                .append("\nData: "+op.getData())
                .append("\nConta: "+op.getConta().getConta())
                .append("\nAgência: "+op.getConta().getAgencia())
                .append("\n").append("\n");
        return builder.toString().getBytes();
    }

    public byte[] atualizarSaldoConta (String conta) {
        LinkedList<OperacaoBancaria> operacoes = obterOperacoesDeUmaConta(conta);
        Conta contaBancaria = new Conta();

        for (OperacaoBancaria op: operacoes) {
            if(op.getTipo().equals("DEPOSITO")) {
                contaBancaria.depositar(op.getValor());
            } else {
                contaBancaria.sacar(op.getValor());
            }

        }
        byte[] saldoConta = ("Saldo da Conta: "+String.valueOf(contaBancaria.getSaldo())).getBytes();
        return saldoConta;
    }
}
