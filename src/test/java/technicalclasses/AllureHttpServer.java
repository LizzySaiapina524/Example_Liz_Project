package technicalclasses;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;

public class AllureHttpServer {

    private static final int PORT = 8080;
    private static final String REPORT_DIR = "allure-report";

    public static void startServer() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.createContext("/", new ReportHandler());
        server.setExecutor(null); // создаем дефолтный executor
        server.start();
        System.out.println("✅ Allure HTTP сервер запущен: http://localhost:" + PORT);
    }

    static class ReportHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String requestedPath = exchange.getRequestURI().getPath();

            // Если запрос на "/", показываем index.html
            if (requestedPath.equals("/")) {
                requestedPath = "/index.html";
            }

            Path filePath = Path.of(REPORT_DIR, requestedPath);

            if (!Files.exists(filePath) || Files.isDirectory(filePath)) {
                String response = "404 Not Found: " + requestedPath;
                exchange.sendResponseHeaders(404, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
                return;
            }

            // Определяем MIME-тип
            String mime = Files.probeContentType(filePath);
            if (mime == null) {
                mime = "application/octet-stream";
            }

            exchange.getResponseHeaders().set("Content-Type", mime);
            byte[] bytes = Files.readAllBytes(filePath);
            exchange.sendResponseHeaders(200, bytes.length);
            OutputStream os = exchange.getResponseBody();
            os.write(bytes);
            os.close();
        }
    }

    public static void main(String[] args) throws IOException {
        startServer();
    }
}
