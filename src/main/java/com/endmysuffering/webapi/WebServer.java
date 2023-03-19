package com.endmysuffering.webapi;


import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.concurrent.Executors;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.TrustManagerFactory;

import com.endmysuffering.webapi.ContentTypes.ContentTypes;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsParameters;
import com.sun.net.httpserver.HttpsServer;

public class WebServer 
{

    private HttpServer server;

    public static WebServer createHttpServer(int port) {
        WebServer webServer = new WebServer();
        try {
            InetSocketAddress address = new InetSocketAddress(InetAddress.getLocalHost(), port);
            //new InetSocketAddress(port)
            HttpServer server = HttpServer.create(address, 0);
            webServer.server = server;
            server.setExecutor(Executors.newCachedThreadPool());

        } catch(UnknownHostException e){
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return webServer;
    }


    public static WebServer createHttpsServer(int port){
        WebServer webServer = new WebServer();
        try {

            HttpsServer server = HttpsServer.create(new InetSocketAddress(port), 0);
            SSLContext sslContext = SSLContext.getInstance("TLS");

            // Initialise the keystore
            char[] password = "simulator".toCharArray();
            KeyStore ks = KeyStore.getInstance("JKS");
            FileInputStream fis = new FileInputStream("lig.keystore");
            ks.load(fis, password);

            // Set up the key manager factory
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(ks, password);

            // Set up the trust manager factory
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            tmf.init(ks);

            // Set up the HTTPS context and parameters
            sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
            server.setHttpsConfigurator(new HttpsConfigurator(sslContext) {
                public void configure(HttpsParameters params) {
                    // Initialise the SSL context
                    SSLContext c = null;
                    try {
                        c = SSLContext.getDefault();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                        return;
                    }
                    SSLEngine engine = c.createSSLEngine();
                    params.setNeedClientAuth(false);
                    params.setCipherSuites(engine.getEnabledCipherSuites());
                    params.setProtocols(engine.getEnabledProtocols());

                    // Get the default parameters
                    SSLParameters defaultSSLParameters = c.getDefaultSSLParameters();
                    params.setSSLParameters(defaultSSLParameters);
                }
            });

            server.setExecutor(Executors.newCachedThreadPool());
            webServer.server = server;

        } catch(UnknownHostException e){
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
            return null;
        } catch (KeyStoreException e) {
            e.printStackTrace();
            return null;
        } catch (CertificateException e) {
            e.printStackTrace();
            return null;
        } catch (KeyManagementException e) {
            e.printStackTrace();
            return null;
        }
        return webServer;
    }

    public HttpServer getServer(){
        return this.server;
    }

    public boolean start(){
        if(this.server != null){
            this.server.start();
            return true;
        }
        return false;
    }

    public boolean stop(){
        if(this.server != null){
            this.server.stop(0);
        }
        return false;
    }

    public void addContext(String path, HttpHandler handel){
        this.server.createContext(path, handel);
    }

    public static void main(String[] args) {
        WebServer server = WebServer.createHttpServer(666);
        if(server == null) return;
        server.addContext("/", (he) -> {
            System.out.println("test");
            RequestUtilitys.send(he, 200, "{'res': 'Hello world'}", ContentTypes.Application.json);
        });

        if(server.start())
            System.out.println("server running on " + server.getServer().getAddress());
    }

}
