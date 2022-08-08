package ru.geekbrains.response;

import ru.geekbrains.domain.HttpResponse;

public interface ResponseSerializer {

    String serialize(HttpResponse response);

}
