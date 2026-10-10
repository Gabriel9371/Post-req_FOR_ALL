package com.gabriel;

public class Main {
    public static void main(String[] args) {
        var r = new HttpRequestData("GET", "http://localhost", null, null);
        var nr = new HttpResponseData(200, null, null, 6);

        System.out.println(r);
        System.out.println(nr);
    }
}