package io.taech.triple.business.events.constant;

public enum MileageUsage {

    WRITE_REVIEW(1, 1, "리뷰작성 적립"),
    ADD_PHOTOS_REVIEW(1, 1, "사진추가 적립"),
    FIRST_BONUS(1, 1, "얼리버드 보너스 적립"),

    REMOVE_PHOTO(2, -1, "사진삭제 회수"),
    REMOVE_REVIEW(2, -1, "리뷰삭제 회수"),
    REMOVE_FIRST_BONUS(2, -1, "얼리버드 보너스 회수")

    ;

    private int type;
    private int result;
    private String descriptions;

    MileageUsage(final int type, final int result, final String descriptions) {
        this.type = type;
        this.result = result;
        this.descriptions = descriptions;
    }

    public int type() {
        return this.type;
    }

    public int mileage() {
        return this.result;
    }

    public String descriptions() {
        return this.descriptions;
    }




}
