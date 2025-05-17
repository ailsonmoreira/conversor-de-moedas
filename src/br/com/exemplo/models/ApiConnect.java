//Realiza a conexao com a API e trata os dados em Json

package br.com.exemplo.models;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiConnect {

    public Moeda buscarTaxa() throws IOException, InterruptedException {
    HttpClient  client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://v6.exchangerate-api.com/v6/bab94b28a0812e9e241d755f/latest/USD"))
            .build();

    HttpResponse<String> response = client
            .send(request, HttpResponse.BodyHandlers.ofString());


    return new Gson().fromJson(response.body(), Moeda.class);

    }
}
