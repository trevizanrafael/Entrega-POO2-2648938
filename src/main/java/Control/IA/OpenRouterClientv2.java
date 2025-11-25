package Control.IA;

import java.io.IOException;
import java.util.List;
import java.util.Map;

// EXTENDE a classe original
public class OpenRouterClientV2 extends OpenRouterClient {

    public OpenRouterClientV2(String apiKey) {
        super(apiKey);
    }

    /**
     * Exemplo de sobrescrita do método de processamento.
     * Esta nova versão simula uma mudança na API onde o campo principal passa a ser 'text'.
     */
    @Override
    protected String processResponse(String responseBody) throws IOException {
        System.out.println("DEBUG: Usando o processador de resposta V2...");

        Map<?, ?> resposta = objectMapper.readValue(responseBody, Map.class);
        List<?> choices = (List<?>) resposta.get("choices");

        if (choices != null && !choices.isEmpty()) {
            Object first = choices.get(0);
            if (first instanceof Map) {
                Map<?, ?> firstMap = (Map<?, ?>) first;
                Object messageObj = firstMap.get("message");
                if (messageObj instanceof Map) {
                    Map<?, ?> msgMap = (Map<?, ?>) messageObj;
                    // A única linha que muda o comportamento: busca por 'text' em vez de 'content'
                    Object content = msgMap.get("text"); 
                    if (content != null) {
                        return content.toString() + " (Processado pelo V2)";
                    }
                }
            }
        }
        return "Sem resposta da IA (V2).";
    }

    /**
     * Opcional: Exemplo de sobrescrita para adicionar um novo parâmetro na requisição.
     */
    @Override
    protected Map<String, Object> buildRequestBody(String model, List<Map<String, String>> messages) {
        // Chama o método original da classe pai
        Map<String, Object> requestBody = super.buildRequestBody(model, messages);
        
        // ADICIONA um novo parâmetro, sem modificar a lógica original
        requestBody.put("seed", 42); 
        
        return requestBody;
    }
}