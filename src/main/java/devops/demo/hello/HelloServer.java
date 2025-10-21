package devops.demo.hello;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class HelloServer {
  public static void main(String[] args) throws Exception {
    int port = Integer.parseInt(System.getenv().getOrDefault("SERVER_PORT", "8080"));
    HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
    server.createContext("/", new HelloHandler());
    server.setExecutor(null);
    server.start();
    System.out.println("Server started on http://localhost:" + port);
  }

  static class HelloHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange ex) throws IOException {
      String html = """
        <!DOCTYPE html>
        <html lang="en">
        <head>
          <meta charset="UTF-8">
          <title>DevOps Demo</title>
          <style>
            body { font-family: Arial, sans-serif; background-color:#f9fafc; color:#333; text-align:center; padding:50px; }
            h1 { color:#2a6592; }
            p  { font-size:18px; }
            .footer { margin-top:40px; font-size:14px; color:#777; }
          </style>
        </head>
        <body>
          <h1>Hello, DevOps!</h1>
          <p>Welcome to our first DevOps demo application.</p>
          <p>My First CI/CD pipeline has been successfully built and deployed. It works!</p>
          <div class="footer">© DevOps Demo | Computer Science - Mark Moore</div>
        </body>
        </html>
        """;
      byte[] bytes = html.getBytes(StandardCharsets.UTF_8);
      ex.getResponseHeaders().set("Content-Type", "text/html; charset=utf-8");
      ex.sendResponseHeaders(200, bytes.length);
      try (OutputStream os = ex.getResponseBody()) { os.write(bytes); }
    }
  }
}
