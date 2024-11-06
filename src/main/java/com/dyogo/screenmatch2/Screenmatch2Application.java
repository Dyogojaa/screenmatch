package com.dyogo.screenmatch2;

import com.dyogo.screenmatch2.model.DadosEpisodio;
import com.dyogo.screenmatch2.model.DadosSerie;
import com.dyogo.screenmatch2.service.ConsumoApi;
import com.dyogo.screenmatch2.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@SpringBootApplication
public class Screenmatch2Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Screenmatch2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		var api = new ConsumoApi();
		var converter = new ConverteDados();

		// Chamada para obter informações da série
		String endereco = "https://omdbapi.com/?t=breaking+bad&apikey=6585022c";
		var json = api.obterDados(endereco);
		DadosSerie serie = converter.obterDados(json, DadosSerie.class);
		System.out.println("Série: " + serie.titulo());

		// Segunda chamada para obter a temporada e os episódios
		endereco = "https://omdbapi.com/?t=breaking+bad&Season=1&apikey=6585022c";
		json = api.obterDados(endereco);
		DadosSerie dadosSerie = converter.obterDados(json, DadosSerie.class);

		// Verifique se a lista de episódios não é nula
		if (dadosSerie.episodios() != null) {
			System.out.println("Episódios da Temporada 1 para a série " + dadosSerie.titulo() + ":");
			dadosSerie.episodios().forEach(System.out::println);
		} else {
			System.out.println("Nenhum episódio encontrado.");
		}

	}
}
