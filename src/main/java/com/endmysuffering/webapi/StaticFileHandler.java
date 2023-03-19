package com.endmysuffering.webapi;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

public class StaticFileHandler implements HttpHandler {

    private static String pathSep = System.getProperty("file.separator");

    private String rootDir;
    private String routePrefix;
    private String dirIndexFile;

    private StaticFileHandler(String routePrefix, String rootDir, String dirIndexFile) {
        if (!routePrefix.startsWith("/")) {
            throw new RuntimeException("Route prefix does not start with a slash");
        }
        if (!routePrefix.endsWith("/")) {
            throw new RuntimeException("Route prefix does not end with a slash");
        }
        
        if(!rootDir.endsWith(pathSep)){
            throw new RuntimeException("Static root directory dose not end in '" + pathSep + "'");
        }
        try {
            this.rootDir = new File(rootDir).getCanonicalPath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        this.routePrefix = routePrefix;
        this.dirIndexFile = dirIndexFile;
    }

    public static void create(HttpServer server, String path, String rootDir, String dirIndexFile) {
        StaticFileHandler handler = new StaticFileHandler(path, rootDir, dirIndexFile);
        server.createContext(path, handler);
    }

    public void handle(HttpExchange he) throws IOException {
        String method = he.getRequestMethod(); 
        String reqPath = he.getRequestURI().getPath();

        if (!method.equals("GET")) {
            RequestUtilitys.send(he, 501);
            return;
        }

        if (!reqPath.startsWith(routePrefix)) {
            RequestUtilitys.send(he, 400);
            return;
        }

        if (reqPath.endsWith("/")) {
            reqPath += dirIndexFile;
        }

        File file;
        try {
            file = new File(rootDir, reqPath.substring(routePrefix.length())).getCanonicalFile();
        } catch (IOException e) {
            RequestUtilitys.send(he, 400);
            return;
        }

        if ("GET".equals(method)) {
            if(!file.exists()){
                RequestUtilitys.send(he, 404);
                return;
            }
    
            if (!file.getPath().startsWith(rootDir)) {
                RequestUtilitys.send(he, 400);
                return;
            }

            RequestUtilitys.send(he, 200, file, Files.probeContentType(file.toPath()));
        } else if("HEAD".equals(method)) {
            he.sendResponseHeaders(200, -1);
        }
    }
}
