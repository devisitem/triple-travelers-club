package io.taech.triple.common.excpeted;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ServiceStatus {

    SUCCESS(200, 0, "SUCCESS"),
    INVALID_EVENT(400, 1, "잘못된 이벤트 입니다."),
    NOT_FOUND_REVIEW_DATA(400, 2, "존재하지 않는 리뷰 정보입니다."),
    NOT_FOUND_MILEAGE_INFO(500, 3, "마일리지 정보가 존재하지않습니다."),
    FAILED_TO_MANAGE_MILEAGES(500, 4, "마일리지 적립 또는 회수에 실패하였습니다."),
    NOT_FOUND_USER_DATA(400, 5, "존재하지 않는 유저입니다."),
    ONLY_ONE_REWARD_AT_PLACE(500, 6, "리뷰는 장소당 한개만 작성할 수 있으며, 보상도 동일합니다."),
    ALREADY_DELETED_REVIEW(500, 7, "이미 삭제된 리뷰는 등록 또는 수정이 불가능합니다."),
    INVALID_REQUEST(400, 8, "잘못된 요청 정보입니다."),
    SYSTEM_ISSUE(500, 9, "요청처리중 에러가 발생하였습니다."),
    INVALID_UUID_FORMAT(400, 10, "유효하지않은 UUID 형식입니다."),
    NOT_SUPPORTED_ACTION(400, 11, "지원되지않는 액션 타입입니다."),
    NOT_FOUND_EVENT_TYPE(400, 12, "존재하지않는 이벤트 타입입니다.");
    ;

    private int status;
    private int code;
    private String message;

    private static final Map<Integer, ServiceStatus> statusMap = Arrays.stream(values()).collect(Collectors.toMap(ServiceStatus::getCode, Function.identity()));

    ServiceStatus(final int status, final int code, final String message) {
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
