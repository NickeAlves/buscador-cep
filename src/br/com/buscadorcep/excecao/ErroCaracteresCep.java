package br.com.buscadorcep.excecao;

public class ErroCaracteresCep extends Exception {

    public ErroCaracteresCep(String mensagem) {
        super(mensagem);
    }
}
