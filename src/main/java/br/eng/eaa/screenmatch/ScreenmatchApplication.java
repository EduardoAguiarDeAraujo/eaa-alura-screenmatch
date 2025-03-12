package br.eng.eaa.screenmatch;

import br.eng.eaa.screenmatch.model.DadosEpisodio;
import br.eng.eaa.screenmatch.model.DadosSerie;
import br.eng.eaa.screenmatch.model.DadosTemporada;
import br.eng.eaa.screenmatch.principal.Principal;
import br.eng.eaa.screenmatch.service.ConsumoAPI;
import br.eng.eaa.screenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibeMenu();

//		json = consumoAPI.obterDados("https://coffee.alexflipnote.dev/random.json");
//		System.out.println(json);

	}
}
