package org.lousanter.IA;

import java.util.List;

import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;

public class ChatClientConstructor {

    static OpenAiService service = new OpenAiService(System.getenv("OPENAI_API_KEY"));

    private static ChatMessage chatMessage;


    public static void construirConsultaCategoria(String categoria) {
        String contenido = """
                Genera una descripcion comercial llamativa muy pequeña y resumida para un producto basado en la siguiente categoría para productos: "%s".
                Refierete en plural, El tono debe ser profesional enfocando la categoria, no lo que deriva de el. NO USES MAS DE 20 PALABRAS, evita comillas
                """.formatted(categoria);

        chatMessage = new ChatMessage("user", contenido);
    }


    public static String construirRespuesta() {
        if (chatMessage == null) {
            throw new IllegalStateException("Debes llamar a construirConsulta...() antes de pedir la respuesta.");
        }

        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(List.of(chatMessage))
                .maxTokens(100)
                .build();

        List<ChatCompletionChoice> choices = service.createChatCompletion(request).getChoices();
        return choices.get(0).getMessage().getContent();
    }

    public static void construirConsultaProducto(String descripcion) {
        String contenido = """
                Genera una descripcion comercial llamativa muy pequeña y resumida basado para el siguiente producto: "%s".
                Refierete en plural, El tono debe ser profesional enfocando en el producto, no lo que deriva de el. NO USES MAS DE 30 PALABRAS, evita comillas
                """.formatted(descripcion);

        chatMessage = new ChatMessage("user", contenido);
    }

}
