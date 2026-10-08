package com.gabriel;

public class Main {
    public static void main(String[] args) {
        var r = new HttpRequestDatarec("GET", "http://localhost", null, null);
        System.out.println(r);
    }
}