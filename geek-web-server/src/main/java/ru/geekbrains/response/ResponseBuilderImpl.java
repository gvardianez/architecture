package ru.geekbrains.response;

import ru.geekbrains.ResponsePropertiesReader;
import ru.geekbrains.ResponsePropertiesReaderImpl;
import ru.geekbrains.domain.HttpResponse;
import ru.geekbrains.services.FileService;
import ru.geekbrains.domain.HttpRequest;

import java.util.Map;

public class ResponseBuilderImpl implements ResponseBuilder {

    private final FileService fileService;

    private final HttpRequest httpRequest;

    private final ResponsePropertiesReader responsePropertiesReader;

    public ResponseBuilderImpl(FileService fileService, HttpRequest httpRequest, String responsePropertiesPath) {
        this.fileService = fileService;
        this.httpRequest = httpRequest;
        this.responsePropertiesReader = new ResponsePropertiesReaderImpl(responsePropertiesPath);
    }

    @Override
    public String buildResponse() {
        HttpResponse httpResponse = new HttpResponse();
        ResponseSerializer responseSerializer = new ResponseSerializerImpl();
        try {
            if (!fileService.exists(httpRequest.getPath()) || fileService.isDirectory(httpRequest.getPath())) {
                buildNotFoundResponse(httpResponse);
                return responseSerializer.serialize(httpResponse);
            }
            buildOkResponse(httpResponse, httpRequest.getPath());
            return responseSerializer.serialize(httpResponse);
        } catch (RuntimeException e) {
            buildInternalServerErrorResponse(httpResponse);
            return responseSerializer.serialize(httpResponse);
        }

    }

    private void buildInternalServerErrorResponse(HttpResponse httpResponse) {
        httpResponse.setStatusCode(ResponseStatus.INTERNAL_SERVER_ERROR);
        httpResponse.setHeaders(Map.of("Content-Type","text/html; charset=utf-8","Server","Local Server"));
        httpResponse.setHttpVersion("HTTP/1.1");
        httpResponse.setBody("<h1>Внутренняя ошибка сервера</h1>");
    }

    private void buildOkResponse(HttpResponse httpResponse, String fileName) {
        httpResponse.setStatusCode(ResponseStatus.OK);
        httpResponse.setHeaders(getHeaders());
        httpResponse.setHttpVersion(getHttpVersion());
        httpResponse.setBody(fileService.readFile(fileName));
    }

    private void buildNotFoundResponse(HttpResponse httpResponse) {
        httpResponse.setStatusCode(ResponseStatus.NOT_FOUND);
        httpResponse.setHeaders(getHeaders());
        httpResponse.setHttpVersion(getHttpVersion());
        httpResponse.setBody(fileService.readFile("not_found.html"));
    }

    private String getHttpVersion() {
        return responsePropertiesReader.getHttpVersion();
    }

    private Map<String, String> getHeaders() {
        return responsePropertiesReader.getResponseHeaders();
    }

}
