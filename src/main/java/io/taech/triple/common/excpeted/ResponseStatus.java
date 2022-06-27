package io.taech.triple.common.excpeted;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ResponseStatus {

    SUCCESS(200, 0, "SUCCESS"),
    INVALID_EVENT(400, 1, "잘못된 이벤트 입니다."),
    NOT_FOUND_REVIEW_DATA(400, 2, "존재하지 않는 리뷰 정보입니다."),
    ;

    private int status;
    private int code;
    private String message;

    private static final Map<Integer, ResponseStatus> statusMap = Arrays.stream(values()).collect(Collectors.toMap(ResponseStatus::getCode, Function.identity()));

    ResponseStatus(final int status, final int code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public int getStatus() {
        return this.status;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }


}
