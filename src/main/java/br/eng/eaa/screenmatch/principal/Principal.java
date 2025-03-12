package br.eng.eaa.screenmatch.principal;

import br.eng.eaa.screenmatch.model.DadosEpisodio;
import br.eng.eaa.screenmatch.model.DadosSerie;
import br.eng.eaa.screenmatch.model.DadosTemporada;
import br.eng.eaa.screenmatch.model.Episodio;
import br.eng.eaa.screenmatch.service.ConsumoAPI;
import br.eng.eaa.screenmatch.service.ConverteDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverteDados converteDados = new ConverteDados();

    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String APIKEY = "&apikey=6585022c";

    public void exibeMenu(){
        System.out.println("Digite o nome da série para a busca:");
        var nomeSerie = leitura.nextLine();
        var json = consumoAPI.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + APIKEY);
        var dados = converteDados.obterDados(json, DadosSerie.class);
        System.out.println(dados);

		List<DadosTemporada> temporadas = new ArrayList<>();

		for(int i = 1; i<=dados.totalTemporadas(); i++) {
			json = consumoAPI.obterDados(ENDERECO + nomeSerie.replace(" ", "+")  +"&season=" + i + APIKEY);
			DadosTemporada dadosTemporada = converteDados.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}
		temporadas.forEach(System.out::println);

//        for (int i = 0; i < dados.totalTemporadas(); i++) {
//            List<DadosEpisodio> episodios = temporadas.get(i).episodios();
//            for(int j = 0; j < episodios.size(); j++){
//                System.out.println(episodios.get(j));
//            }
//        }

//        temporadas.forEach(temporada -> temporada.episodios().forEach(e -> System.out.println(e.titulo())));

        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(temporada -> temporada.episodios().stream())
                .collect(Collectors.toList());

//        System.out.println('\n' + "Episódios top  10");
//        dadosEpisodios.stream()
//                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
//                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
//                .limit(10)
//                .map(e -> e.titulo().toUpperCase())
//                .forEach(System.out::println);

        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numero(), d))
                ).collect(Collectors.toList());

        episodios.forEach(System.out::println);

        episodios.stream()
                .filter(e -> e.getAvaliacao() != null && !e.getAvaliacao().toString().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(Episodio::getAvaliacao).reversed())
                .limit(10)
                .forEach(e -> System.out.println(
                        "Temporada: " + e.getTemporada() +
                                " - Episódio: " + e.getNumeroEpisodio() +
                                " - Título: " + e.getTitulo() +
                                " - Avaliação: " + e.getAvaliacao()
                ));

        System.out.println("Digite um trecho do título para buscar um episódio:");
        var trechoTitulo = leitura.nextLine();

        Optional<Episodio> episodioBuscado = episodios.stream()
                .filter(e -> e.getTitulo().toUpperCase().contains(trechoTitulo.toUpperCase()))
                .findFirst();

        if (episodioBuscado.isPresent()) {
            System.out.println("Episódio encontrado: " + episodioBuscado.get());
        } else {
            System.out.println("Episódio não encontrado.");
        }

//        System.out.println("A partir de que ano você deseja ver os episódios?");
//        var ano = leitura.nextInt();
//        leitura.nextLine();
//
//        LocalDate dataBusca = LocalDate.of(ano, 1, 1);
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        episodios.stream()
//                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
//                .forEach(e -> System.out.println(
//                        "Temporada: " + e.getTemporada() +
//                                " - Episódio: " + e.getNumeroEpisodio() +
//                                " - Data de Lançamento: " + e.getDataLancamento().format(formatter)
//                ));
//

    }

}
