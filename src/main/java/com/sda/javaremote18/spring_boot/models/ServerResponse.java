package com.sda.javaremote18.spring_boot.models;

public class ServerResponse {
    private int status; // 200, 400, 500...cod de http
    private String message; // mesajul catre client
    private String error; // eroare de "mail folosit deja"
    private Object result; // Object este clasa parinte a tuturor obiectelor, iar cu result trimitem informatii catre client

    public ServerResponse(int status, String message, String error, Object result) {
        this.status = status;
        this.message = message;
        this.error = error;
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
