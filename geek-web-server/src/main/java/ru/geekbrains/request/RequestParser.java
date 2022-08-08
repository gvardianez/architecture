package ru.geekbrains.request;

import ru.geekbrains.domain.HttpRequest;

public interface RequestParser {

    HttpRequest parseRequest();

}
