package br.com.exemplo.models;
import java.util.Map;

//Salva os atributos extraidos do Json

public record Moeda(String base_code, Map<String, Double> conversion_rates) {
}
