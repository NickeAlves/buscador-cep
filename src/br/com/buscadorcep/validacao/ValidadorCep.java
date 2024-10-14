package br.com.buscadorcep.validacao;

import br.com.buscadorcep.excecao.ErroCaracteresCep;

public class ValidadorCep {
    public static String validarCep(String cep) throws ErroCaracteresCep {

        String cepValido = cep.replaceAll("[- ]", "");

        if (cepValido.length() != 8) {
            throw new ErroCaracteresCep("""
                    Erro: O CEP deve conter exatamente 8 caracteres. Favor, inserir novamente.
                    """);
        }

        return cepValido;
    }
}
