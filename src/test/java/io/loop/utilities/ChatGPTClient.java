package io.loop.utilities;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.*;
import java.util.*;
import org.json.*;
import java.net.http.HttpClient;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChatGPTClient {
    private static final String API_KEY = ConfigurationReader.getProperties("openai.api.key");
    private static final String ENDPOINT = "https://api.openai.com/v1/chat/completions";

    public static String getResponseFromPrompt(String prompt) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            ObjectMapper mapper = new ObjectMapper();

            // Build message and request body
            JSONArray messages = new JSONArray();
            JSONObject userMessage = new JSONObject();
            userMessage.put("role", "user");
            userMessage.put("content", prompt);
            messages.put(userMessage);

            JSONObject body = new JSONObject();
            body.put("model", "gpt-3.5-turbo");
            body.put("messages", messages);
            body.put("max_tokens", 300);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ENDPOINT))
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Print raw response for debugging
            System.out.println("Raw Response:\n" + response.body());

            JSONObject jsonResponse = new JSONObject(response.body());

            // Check if choices array exists
            if (jsonResponse.has("choices")) {
                return jsonResponse
                        .getJSONArray("choices")
                        .getJSONObject(0)
                        .getJSONObject("message")
                        .getString("content")
                        .trim();
            } else {
                return "API returned error: " + jsonResponse.toString(2);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to get response";
        }
    }
}