package edu.mamontova;/*
  @author tanus
  @project lab5
  @class ItemHandler
  @version 1.0.0
  @since 19.04.2025 - 23.06
*/

import com.sun.net.httpserver.*;
import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

import com.google.gson.*;

public class ItemHandler implements HttpHandler {
    private final ItemStore store = new ItemStore();
    private final Gson gson = new Gson();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        URI uri = exchange.getRequestURI();
        String path = uri.getPath();
        String[] parts = path.split("/");
        System.out.println("Request received: " + exchange.getRequestURI().getPath());
        System.out.println("HTTP Method: " + exchange.getRequestMethod());

        if (path.matches("/api/v1/items/?")) {
            switch (method) {
                case "GET":
                    respond(exchange, 200, gson.toJson(store.getAll()));
                    break;
                case "POST":
                    String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
                    Item item = gson.fromJson(body, Item.class);
                    respond(exchange, 201, gson.toJson(store.create(item)));
                    break;
                case "PUT":
                    String putBody = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
                    Item updated = gson.fromJson(putBody, Item.class);
                    Item result = store.update(updated);
                    if (result != null) {
                        respond(exchange, 200, gson.toJson(result));
                    } else {
                        respond(exchange, 404, "Not Found");
                    }
                    break;
                default:
                    respond(exchange, 405, "Method Not Allowed");
            }
        } else if (path.matches("/api/v1/items/\\d+/?")) {
            long id = Long.parseLong(parts[4]);
            if ("GET".equals(method)) {
                Item item = store.getById(id);
                if (item != null) {
                    respond(exchange, 200, gson.toJson(item));
                } else {
                    respond(exchange, 404, "Not Found");
                }
            } else if ("DELETE".equals(method)) {
                boolean success = store.delete(id);
                respond(exchange, success ? 204 : 404, "");
            } else {
                respond(exchange, 405, "Method Not Allowed");
            }
        } else {
            respond(exchange, 404, "Not Found");
        }
    }

    private void respond(HttpExchange exchange, int status, String response) throws IOException {
        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.sendResponseHeaders(status, bytes.length);
        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();
    }
}