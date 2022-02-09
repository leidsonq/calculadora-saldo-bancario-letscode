package br.com.letscode.entidades.enums;

public enum TipoOperacao {
    SAQUE("Saque"), DEPOSITO("Depósito");

    private String descricao;

    private TipoOperacao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return this.descricao;
    }
}
