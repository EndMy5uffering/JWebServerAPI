package com.endmysuffering.webapi;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import com.endmysuffering.webapi.ContentTypes.ContentTypes;
import com.sun.net.httpserver.HttpExchange;

public class RequestUtilitys {
    
    public static boolean send(HttpExchange he, int resCode, String data) {
        return send(he, resCode, data.getBytes(), ContentTypes.Text.plain);
    }

    public static boolean send(HttpExchange he, int resCode, String data, ContentTypes.ContentType contentType) {
        return send(he, resCode, data, contentType.getContentType());
    }

    public static boolean send(HttpExchange he, int resCode, String data, String contentType) {
        return send(he, resCode, data.getBytes(), contentType);
    }

    public static boolean send(HttpExchange he, int resCode, byte[] data, ContentTypes.ContentType contentType) {
        return send(he, resCode, data, contentType.getContentType());
    }

    public static boolean send(HttpExchange he, int resCode, byte[] data, String contentType) {
        if(he == null) return false;

        he.getResponseHeaders().add("Content-Type", contentType != null && contentType != "" ? contentType : "text/plane");
        he.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        try {
            he.sendResponseHeaders(resCode, data.length);
            he.getResponseBody().write(data);
        } catch (IOException e) {
            return false;
        }
        he.close();
        return true;
    }

    public static boolean send(HttpExchange he, int resCode){
        if(he == null) return false;
        try {
            he.sendResponseHeaders(resCode, -1);
        } catch (IOException e) {
            return false;
        }
        he.close();
        return true;
    }

    public static boolean send(HttpExchange he, int resCode, File data, ContentTypes.ContentType contentType) {
        return send(he, resCode, data, contentType.getContentType());
    }

    public static boolean send(HttpExchange he, int resCode, File data, String contentType) {
        if(he == null) return false;
        
        he.getResponseHeaders().add("Content-Type", contentType != null && contentType != "" ? contentType : "text/plane");
        he.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        try {
            he.sendResponseHeaders(resCode, data.length());
            he.getResponseBody().write(Files.readAllBytes(data.toPath()));
        } catch (IOException e) {
            return false;
        }
        he.close();
        return true;
    }

    public static Map<String, String> queryToMap(String query) {
        if(query == null) return null; 
        Map<String, String> result = new HashMap<>();
        for (String param : query.split("&")) {
            String[] entry = param.split("=");
            if (entry.length > 1) {
                result.put(entry[0], entry[1]);
            }else{
                result.put(entry[0], "");
            }
        }
        return result;
    }

}
