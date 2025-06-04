package com.picotrake.API.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Named;

public class JsonMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Named("stringToJsonNode")
    public static JsonNode stringToJsonNode(String json) {
        try {
            return objectMapper.readTree(json);
        } catch (Exception e) {
            throw new RuntimeException("GeoJSON inválido", e);
        }
    }

    @Named("jsonNodeToString")
    public static String jsonNodeToString(JsonNode node) {
        try {
            return objectMapper.writeValueAsString(node);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al convertir JsonNode a String", e);
        }
    }
}