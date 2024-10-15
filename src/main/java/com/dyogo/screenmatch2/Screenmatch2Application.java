package com.dyogo.screenmatch2;

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
		String endereco = "https://omdbapi.com/?t=breaking+bad&apikey=6585022c";
		var json = api.obterDados(endereco);
		var converter = new ConverteDados();

		DadosSerie serie  = converter.obterDados(json, DadosSerie.class);

		System.out.println(serie);


	}
}
