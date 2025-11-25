package Control.IA;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OpenRouterClient {

    private static final String API_URL = "https://openrouter.ai/api/v1/chat/completions";
    protected final String apiKey;
    protected final HttpClient httpClient;
    protected final ObjectMapper objectMapper;

    public OpenRouterClient(String apiKey) {
        this.apiKey = apiKey;
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    // --- Métodos Extensíveis (OCP) ---

    // 1. Permite que classes filhas mudem o corpo da requisição
    protected Map<String, Object> buildRequestBody(String model, List<Map<String, String>> messages) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", model);
        requestBody.put("messages", messages);
        requestBody.put("temperature", 0.7);
        requestBody.put("max_tokens", 200);
        return requestBody;
    }

    // 2. Permite que classes filhas mudem como a resposta JSON é lida
    protected String processResponse(String responseBody) throws IOException {
        Map<?, ?> resposta = objectMapper.readValue(responseBody, Map.class);
        List<?> choices = (List<?>) resposta.get("choices");

        if (choices != null && !choices.isEmpty()) {
            Object first = choices.get(0);
            if (first instanceof Map) {
                Map<?, ?> firstMap = (Map<?, ?>) first;
                Object messageObj = firstMap.get("message");
                if (messageObj instanceof Map) {
                    Map<?, ?> msgMap = (Map<?, ?>) messageObj;
                    Object content = msgMap.get("content");
                    if (content != null) {
                        return content.toString();
                    }
                }
            }
        }
        return "Sem resposta da IA.";
    }

    // --- Método Principal ---

    public String chat(String model, List<Map<String, String>> messages) throws IOException, InterruptedException {
        // Usa o método buildRequestBody (extensível)
        Map<String, Object> requestBody = buildRequestBody(model, messages); 
        String json = objectMapper.writeValueAsString(requestBody);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Erro da API: " + response.statusCode() + " - " + response.body());
        }

        // Usa o método processResponse (extensível)
        return processResponse(response.body()); 
    }
}