package com.gabriel;

import com.gabriel.http.HttpEngine;
import com.gabriel.http.HttpRequestData;
import com.gabriel.http.HttpResponseData;

import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        HttpEngine engine = new HttpEngine();
        HttpResponseData res = engine.send(new HttpRequestData("GET", "https://httpbin.org/get", Map.of(), null));

        System.out.println(res.status() + " em " + res.ms() + "ms");
        System.out.println(res.body());
    }
}
