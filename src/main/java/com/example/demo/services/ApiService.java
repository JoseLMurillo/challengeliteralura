package com.example.demo.services;

import com.example.demo.dto.BookApiResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class ApiService {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String getBookByName(String name) {
        String encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://gutendex.com/books/?search=" + encodedName))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response.body();
    }

    public BookApiResponse parseBookResponse(String jsonResponse) {
        try {
            JsonNode root = objectMapper.readTree(jsonResponse);
            JsonNode results = root.get("results");

            if (results.isEmpty()) {
                return null;
            }

            JsonNode firstBook = results.get(0);
            JsonNode firstAuthor = firstBook.get("authors").isEmpty() ? null : firstBook.get("authors").get(0);

            BookApiResponse response = new BookApiResponse();
            response.setGutendexId(firstBook.get("id").asInt());
            response.setTitle(firstBook.get("title").asText());

            if (firstAuthor != null) {
                response.setAuthorName(firstAuthor.get("name").asText());
                response.setAuthorBirthYear(firstAuthor.get("birth_year").isNull() ? null : firstAuthor.get("birth_year").asInt());
                response.setAuthorDeathYear(firstAuthor.get("death_year").isNull() ? null : firstAuthor.get("death_year").asInt());
            }

            ArrayList<String> languages = new ArrayList<>();
            firstBook.get("languages").forEach(lang -> languages.add(lang.asText()));
            response.setLanguages(languages);

            return response;
        } catch (Exception e) {
            throw new RuntimeException("Error parsing API response", e);
        }
    }
}
