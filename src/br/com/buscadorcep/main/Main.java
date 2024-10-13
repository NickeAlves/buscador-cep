package br.com.buscadorcep.main;

import br.com.buscadorcep.excecao.ErroCaracteresCep;
import br.com.buscadorcep.modelos.Endereco;
import br.com.buscadorcep.validacao.ValidadorCep;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("- Bem vindo ao Buscador CEP -\n");

        String cepUsuario = "";
        List<Endereco> enderecos = new ArrayList<>();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .create();

        while (!cepUsuario.equalsIgnoreCase("sair")) {
            System.out.print("Digite o seu CEP ou 'sair' para parar o programa: ");
            cepUsuario = sc.nextLine();

            if (cepUsuario.equalsIgnoreCase("sair")) {
                break;
            }

            try {
                cepUsuario = ValidadorCep.validarCep(cepUsuario);
            } catch (ErroCaracteresCep e) {
                System.out.println(e.getMessage());
                continue;
            }

            System.out.print("Digite o número do endereço: ");
            int numeroLogradouro = Integer.parseInt(sc.nextLine());
            System.out.println();

            String enderecoUrl = "https://viacep.com.br/ws/" + cepUsuario + "/json/";

            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(enderecoUrl))
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());
                String json = response.body();

                if (json.contains("\"erro\"")) {
                    System.out.println("Endereço não existente!");
                } else {
                    Endereco endereco = gson.fromJson(json, Endereco.class);
                    endereco.setNumero(numeroLogradouro);
                    enderecos.add(endereco);
                }

                for (Endereco e : enderecos) {
                    System.out.println(e);
                }
                System.out.println();

            } catch (IOException | InterruptedException e) {
                System.out.println("Erro na busca, favor verificar o CEP digitado.");
            }
        }
        sc.close();
    }
}