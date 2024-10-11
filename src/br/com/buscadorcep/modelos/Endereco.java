package br.com.buscadorcep.modelos;

public class Endereco {
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
    private int numero;

    public String getCep() {
        return cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public String getUf() {
        return uf;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Endereço: "
                + getLogradouro()
                + ", "
                + getNumero()
                + " - "
                + getBairro()
                + ", "
                + getLocalidade()
                + " - "
                + getUf()
                + " - CEP "
                + getCep();
    }
}
