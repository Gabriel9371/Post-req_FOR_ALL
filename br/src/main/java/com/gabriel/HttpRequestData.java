package com.gabriel;

import java.util.Map;
import java.util.Objects;

public record HttpRequestData(String method, String url, Map<String, String> headers, String body) {
    public HttpRequestData{
        Objects.requireNonNull(method, "TESTE");
        Objects.requireNonNull(url, "TESTE");
        headers = (headers == null) ? Map.of() : Map.copyOf(headers);
    }
}
