package ru.geekbrains.response;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResponseBuilder {

    private final PrintWriter output;

    private final String folder;

    private final String fileName;

    public ResponseBuilder(PrintWriter output, String folder, String fileName) {
        this.output = output;
        this.folder = folder;
        this.fileName = fileName;
    }

    public void start() {
        Path path = Paths.get(folder, fileName);
        if (!checkPath(path)) {
            output.println("HTTP/1.1 404 NOT_FOUND");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<h1>Файл не найден!</h1>");
            output.flush();
            return;
        }
        try {
            Files.newBufferedReader(path).
                    transferTo(output);
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
        } catch (IOException e) {
            output.println("HTTP/1.1 500 INTERNAL_SERVER_ERROR");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<h1>Ошибка сервера</h1>");
            output.println("<h1> " + e.toString() + e.getMessage() + " </h1>");
            output.flush();
        }
    }

    private boolean checkPath(Path path) {
        return Files.exists(path);
    }

}
