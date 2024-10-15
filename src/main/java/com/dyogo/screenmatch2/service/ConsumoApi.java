package com.dyogo.screenmatch2.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoApi {

    private final HttpClient client;

    // Inicializa o HttpClient na construção do objeto para reutilização
    public ConsumoApi() {
        this.client = HttpClient.newHttpClient();
    }

    // Método principal para obter dados de uma URL
    public String obterDados(String endereco) {
        HttpRequest request = criarRequisicao(endereco);
        return enviarRequisicao(request);
    }

    // Cria uma requisição HTTP a partir de um endereço
    private HttpRequest criarRequisicao(String endereco) {
        return HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
    }

    // Envia a requisição e retorna o corpo da resposta como String
    private String enviarRequisicao(HttpRequest request) {
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt(); // Restaura o estado de interrupção
            throw new RuntimeException("Erro ao obter dados da API", e);
        }
    }
}
