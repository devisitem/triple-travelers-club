package io.taech.triple.business.events.constant;

public enum MileageHistory {

    WRITE_REVIEW(1, "리뷰작성 적립"),
    ADD_PHOTOS_REVIEW(1, "사진추가 적립"),
    FIRST_BONUS(1, "얼리버드 보너스 적립"),

    REMOVE_PHOTO(-1, "사진삭제 회수"),
    REMOVE_REVIEW(-1, "리뷰삭제 회수"),
    REMOVE_FIRST_BONUS(-1, "얼리버드 보너스 회수")

    ;
    private int result;
    private String description;

    MileageHistory(final int result, final String description) {
        this.result = result;
        this.description = description;
    }

    public int mileage() {
        return this.result;
    }

    public String description() {
        return this.description;
    }



}
