package br.eng.eaa.screenmatch.principal;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EstudoStreams {
    public static void main(String[] args) {

        //https://www.oracle.com/br/technical-resources/articles/java/processing-streams-java-se-8.html

        List<String> nomes = Arrays.asList("Jacque", "Iasmin", "Paulo", "Rodrigo", "Nico", "Rafael", "Lucas", "Eduardo", "Gustavo", "Ricardo");
        nomes.stream()
                .sorted()
                .limit(8)
                .filter(n -> n.startsWith("N"))
                .map(String::toUpperCase)
                .forEach(System.out::println);

        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> numerosPares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        System.out.println(numerosPares); // Output: [2, 4, 6, 8, 10]

        List<String> palavras = Arrays.asList("Java", "Stream", "Operações", "Intermediárias");

        List<Integer> tamanhos = palavras.stream()
                .map(s -> s.length())
                .collect(Collectors.toList());

        System.out.println(tamanhos); // Output: [4, 6, 11, 14]

        List<String> nomes2 = Arrays.asList("João", "Maria", "Pedro", "Ana");

        nomes2.stream()
                .forEach(nome -> System.out.println("Olá, " + nome + "!"));

        // Output:
        // Olá, João!
        // Olá, Maria!
        // Olá, Pedro!
        // Olá, Ana!


        List<Integer> numeros2 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Set<Integer> numerosPares2 = numeros2.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toSet());

        System.out.println(numerosPares2); // Output: [2, 4, 6, 8, 10]
    }

}
