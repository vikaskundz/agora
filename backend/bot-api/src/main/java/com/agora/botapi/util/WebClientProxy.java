package com.agora.botapi.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

@Service
public class WebClientProxy {

    public static final String URL = "http://agora-nft-automation.com/api";

    public String sendAndReceive(String uriPath) {
        try {
            HttpClient httpClient = HttpClient
                    .newBuilder()
                    .build();
            HttpRequest httpRequest = HttpRequest
                    .newBuilder()
                    .uri(new URI(URL + uriPath))
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public String sendAndReceiveForMint(String uriPath, Map<String, String> bodyMap) {
        try {
            HttpClient httpClient = HttpClient
                    .newBuilder()
                    .build();
            HttpRequest httpRequest = HttpRequest
                    .newBuilder()
                    .uri(new URI(URL + uriPath))
                    .POST(HttpRequest.BodyPublishers.ofString(getData(bodyMap)))
                    .build();
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getData(Map<String, String> bodyMap) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(bodyMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
