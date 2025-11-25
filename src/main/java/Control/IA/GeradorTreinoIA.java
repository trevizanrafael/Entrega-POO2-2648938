package Control.IA;

import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GeradorTreinoIA {

    private final OpenRouterClient client;

    public GeradorTreinoIA(OpenRouterClient client) {
        this.client = client;
    }

    public Map<String, String> gerarTreino(String grupo, String peso, String objetivo, String idade) throws Exception {

        String prompt = """
            Você é um treinador.
            Monte uma ficha de treino com 4 exercícios para o grupo muscular %s,
            considerando:
            - Peso do aluno: %s kg
            - Objetivo: %s
            - Idade: %s anos

            Responda apenas neste formato (sem texto extra):
            Exercício 1:
            Peso 1:
            Repetições 1:
            Exercício 2:
            Peso 2:
            Repetições 2:
            Exercício 3:
            Peso 3:
            Repetições 3:
            Exercício 4:
            Peso 4:
            Repetições 4:
        """.formatted(grupo, peso, objetivo, idade);

        List<Map<String, String>> mensagens = List.of(Map.of("role", "user", "content", prompt));

        String resposta = client.chat("openai/gpt-4o", mensagens);

        // simples parser linha a linha
        Map<String, String> dados = new HashMap<>();
        for (String linha : resposta.split("\n")) {
            linha = linha.trim();
            if (linha.contains(":")) {
                String[] partes = linha.split(":", 2);
                dados.put(partes[0].replaceAll("\\s+", ""), partes[1].trim());
            }
        }
        return dados;
    }
}
