package com.iamgolfz.sse;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;



public class MainApplication {

    public static int count = 1;

    public static void main(String[] args) throws IOException {
        System.out.println("Hello");

        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);
        server.createContext("/user", new MyHttpHandler());

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        server.setExecutor(threadPoolExecutor);

        server.start();
        System.out.println(" Server started on port 8080");
    }
}
