package ru.rashgild.model.dto;

public class ResponseModel {

    public ResponseModel() {

    }

    public ResponseModel(String error) {
        this.error = error;
    }

    public ResponseModel(String object, String error, String message) {
        this.object = object;
        this.error = error;
        this.message = message;
    }

    public ResponseModel(String object, String error) {
        this.object = object;
        this.error = error;
        this.message = "pre-send error";
    }

    private String object;
    private String error;
    private String message;

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseModel{" +
                "object='" + object + '\'' +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
