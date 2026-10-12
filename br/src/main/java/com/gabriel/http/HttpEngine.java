package com.gabriel.http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

public class HttpEngine {

    private final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .followRedirects(HttpClient.Redirect.NORMAL)
            .build();

    public HttpResponseData send(HttpRequestData req) throws Exception {
        HttpRequest.BodyPublisher publisher =
                (req.body() == null || req.body().isBlank())
                        ? HttpRequest.BodyPublishers.noBody()
                        : HttpRequest.BodyPublishers.ofString(req.body());

        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(req.url()))
                .timeout(Duration.ofSeconds(30))
                .method(req.method(), publisher);

        req.headers().forEach(builder::header);

        long start = System.nanoTime();
        HttpResponse<String> res =
                client.send(builder.build(), HttpResponse.BodyHandlers.ofString());
        long ms = (System.nanoTime() - start) / 1_000_000;

        Map<String, String> headers = new LinkedHashMap<>();
        res.headers().map().forEach((k, v) -> headers.put(k, String.join(", ", v)));

        return new HttpResponseData(res.statusCode(), headers, res.body(), ms);
    }
}