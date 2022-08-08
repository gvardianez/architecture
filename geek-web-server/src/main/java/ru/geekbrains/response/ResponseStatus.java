package ru.geekbrains.response;

public enum ResponseStatus {

    OK(200),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500);

    private final int code;

    private ResponseStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
