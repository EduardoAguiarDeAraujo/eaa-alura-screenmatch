package br.eng.eaa.screenmatch;

import br.eng.eaa.screenmatch.model.DadosSerie;
import br.eng.eaa.screenmatch.service.ConsumoAPI;
import br.eng.eaa.screenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		System.out.println("Primeiro projeto Spring sem Web");
		var	comsumoAPI = new ConsumoAPI();
		var json = comsumoAPI.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=6585022c");
//		System.out.println(json);
//		json = comsumoAPI.obterDados("https://coffee.alexflipnote.dev/random.json");
//		System.out.println(json);

		ConverteDados converteDados = new ConverteDados();
		var dados = converteDados.obterDados(json, DadosSerie.class);
		System.out.println(dados);
	}
}
