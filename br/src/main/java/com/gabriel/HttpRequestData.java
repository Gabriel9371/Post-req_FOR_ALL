package com.gabriel;

import java.util.Map;
import java.util.Objects;

public class HttpRequestData {

    private final String method;
    private final String url;
    private final Map<String, String> headers;
    private final String body;

    public HttpRequestData(String method, String url, Map<String, String> headers, String body) {
        this.method = Objects.requireNonNull(method, "Metodo não pode ser nulo");
        this.url = Objects.requireNonNull(url, "URL não pode ser nulo");
        this.headers = (headers ==  null) ? Map.of() : Map.copyOf(headers);
        this.body = body;
    }

    public String method() {
        return method; 
    }

    public String url(){
        return url;
    }

    public Map<String, String> headers(){
        return headers;
    }

    public String body(){
        return body;
    }
}
