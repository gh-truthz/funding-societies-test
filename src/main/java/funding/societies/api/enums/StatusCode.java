package funding.societies.api.enums;

public enum StatusCode {
    OK(200),
    RESOURCE_CREATED(201),
    NO_CONTENT(204),
    BAD_REQUEST(400),
    RESOURCE_NOT_FOUND(404)
    ;

    private int httpCode;
    StatusCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public int value() {
        return httpCode;
    }
}
