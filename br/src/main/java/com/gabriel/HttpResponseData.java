package com.gabriel;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;


public record HttpResponseData(int status, Map<String, String> headers, String body, long ms) {
    public HttpResponseData{
        
        headers = (headers == null) ? Map.of() : Collections.unmodifiableMap(new LinkedHashMap<>(headers));
        body = (body == null) ? "{ :( }" : body ;
        
    }
}
