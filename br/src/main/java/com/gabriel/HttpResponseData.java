package com.gabriel;

import java.util.Map;
import java.util.Objects;

public record HttpResponseData(int status, Map<String, String> headers, String body, long ms) {
    public HttpResponseData{
        Objects.requireNonNull(status, "Invalid");
        headers = (headers == null) ? Map.of() : Map.copyOf(headers);
    }
}
