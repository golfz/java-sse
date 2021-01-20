package com.iamgolfz.sse;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class MyHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String requestParamValue = null;

        if ("GET".equals(httpExchange.getRequestMethod())) {
            requestParamValue = handleGetRequest(httpExchange);
        }

        handleResponse(httpExchange, requestParamValue);
    }

    private String handleGetRequest(HttpExchange httpExchange) {
        return httpExchange.getRequestURI().toString().split("\\?")[1].split("=")[1];
    }

    private void handleResponse(HttpExchange httpExchange, String requestParamValue) throws IOException {
        OutputStream outputStream = httpExchange.getResponseBody();

        // this line is a must
        httpExchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        httpExchange.getResponseHeaders().set("Content-Type", "text/event-stream");
        httpExchange.getResponseHeaders().set("Cache-Control", "no-cache");
        httpExchange.getResponseHeaders().set("Connection", "keep-alive");

        while (true) {
            String bodyResponse = "data: " + MainApplication.count + "\n\n";

            httpExchange.sendResponseHeaders(200, bodyResponse.length());
            outputStream.write(bodyResponse.getBytes());
            outputStream.flush();

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            MainApplication.count += 1;

        }


    }

}
