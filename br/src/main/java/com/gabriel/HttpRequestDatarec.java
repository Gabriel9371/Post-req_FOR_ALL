package com.gabriel;

import java.util.Map;
import java.util.Objects;

public record HttpRequestDatarec(String method, String url, Map<String, String> headers, String body) {
    public HttpRequestDatarec{
        Objects.requireNonNull(method, "TESTE");
        Objects.requireNonNull(url, "TESTE");
        headers = (headers == null) ? Map.of() : Map.copyOf(headers);
    }
}
