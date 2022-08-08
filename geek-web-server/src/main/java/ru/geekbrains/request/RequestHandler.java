package ru.geekbrains.request;

import ru.geekbrains.domain.HttpRequest;

public interface RequestHandler {

    HttpRequest readRequest();

}
