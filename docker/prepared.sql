CREATE DATABASE IF NOT EXISTS triple_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE triple_db;
-- 테이블 정의

create table travelers_review
(
    id             varchar(36)                        not null
        primary key,
    user_id        varchar(36)                        not null comment '작성자 아이디',
    place_id       varchar(36)                        not null comment '리뷰 장소 아이디',
    review_content varchar(255)                       not null comment '리뷰 텍스트 내용',
    create_time    datetime(6) default NOW(6)         not null comment '생성 일시',
    delete_time    datetime                           null comment '삭제일시'
)
    comment '리뷰 관리 테이블';

create index travelers_review_place_id_index
    on travelers_review (place_id);

create index travelers_review_user_id_index
    on travelers_review (user_id);



create table common_file
(
    id               varchar(36)                        not null comment '파일 아이디'
        primary key,
    file_size        int                                not null comment '파일 사이즈',
    file_path        varchar(255)                       not null comment '파일 경로 ',
    stored_file_name varchar(50)                        null comment '저장된 파일명',
    real_file_name   varchar(50)                        not null comment '실제 파일명',
    file_extension   varchar(5)                         not null comment '파일 확장자',
    create_time      datetime(6) default NOW(6)         not null comment '생성 일시'
)
    comment '공통 파일 관리 테이블';



create table review_images
(
    id                  varchar(36)                        not null comment '이미지 아이디'
        primary key,
    travelers_review_id varchar(36)                        not null comment '소속된 리뷰 아이디',
    common_file_id      varchar(36)                        not null comment '파일 아이디',
    image_link          varchar(255)                       not null comment '이미지 링크 문자열',
    create_time         datetime(6) default NOW(6)         not null comment '생성 일시',
    delete_time         datetime                           null comment '삭제 일시
',
    constraint review_images_common_file_id_un
        unique (common_file_id)
)
    comment '리뷰 이미지 관리 테이블';

create index review_images_travelers_review_id_index
    on review_images (travelers_review_id);



create table triple_place_info
(
    id          varchar(36)                        not null comment '여행 장소 아이디'
        primary key,
    place_name  varchar(200)                       not null comment '트리플 여행지 장소명',
    create_time datetime(6) default NOW(6)         not null comment '생성 일시'
);

create index triple_place_info_place_name_index
    on triple_place_info (place_name);



create table travelers_mileage_history
(
    id           varchar(36)                        not null comment '마일리지 이력 아이디'
        primary key,
    user_id      varchar(36)                        not null comment '유저 아이디',
    type         int(1)                             not null comment '마일리지 이력 타입. 1: 적립, 2: 소모',
    mileage      int                                not null comment '적립 또는 소모 마일리지',
    descriptions varchar(200)                       not null comment '적립 또는 사용내용',
    create_time  datetime(6) default NOW(6)         not null comment '생성 일시',
    delete_time  datetime                           null comment '삭제 일시'
)
    comment '트리플 여행자 마일리지 관리 이력';

create index travelers_mileage_history_user_for_search
    on travelers_mileage_history (user_id, type);


create table review_reward_info
(
    id                 varchar(36)                    not null comment '리뷰 보상정보 아이디'
        primary key,
    review_id          varchar(36)                    not null comment '리뷰 아이디',
    mileage_history_id varchar(36)                    not null comment '마일리지 이력 아이디',
    result_type        varchar(50)                    not null comment '처리정보 타입',
    create_time        datetime(6) default   NOW(6)   not null comment '생성 일시'
)
    comment '리뷰 보상정보 관리 테이블';

create index review_reward_info_for_search
    on review_reward_info (review_id, mileage_history_id, result_type);


create table travelers_mileage_info
(
    id          varchar(36)                        not null comment '유저 포인트정보 아이디'
        primary key,
    user_id     varchar(36)                        not null comment '유저 아이디',
    mileage     int                                not null comment '현재 보유 마일리지',
    create_time datetime(6) default    NOW(6)      not null comment '생성일시',
    constraint travelers_mileage_info_user_id_un
        unique (user_id)
);


create table triple_user
(
    id          varchar(36)                        not null comment '유저정보 아이디'
        primary key,
    nickname    varchar(18)                        not null comment '유저 닉네임',
    create_time datetime(6) default    NOW(6)      not null comment '생성 일시'
);



-- 함수

-- 버전 4 UUID 생성
DELIMITER //

CREATE FUNCTION uuid_v4()
    RETURNS CHAR(36)
    NO SQL
BEGIN
    SET @h1 = LPAD(HEX(FLOOR(RAND() * 0xffff)), 4, '0');
    SET @h2 = LPAD(HEX(FLOOR(RAND() * 0xffff)), 4, '0');
    SET @h3 = LPAD(HEX(FLOOR(RAND() * 0xffff)), 4, '0');
    SET @h6 = LPAD(HEX(FLOOR(RAND() * 0xffff)), 4, '0');
    SET @h7 = LPAD(HEX(FLOOR(RAND() * 0xffff)), 4, '0');
    SET @h8 = LPAD(HEX(FLOOR(RAND() * 0xffff)), 4, '0');

    SET @h4 = CONCAT('4', LPAD(HEX(FLOOR(RAND() * 0x0fff)), 3, '0'));

    SET @h5 = CONCAT(HEX(FLOOR(RAND() * 4 + 8)),
                     LPAD(HEX(FLOOR(RAND() * 0x0fff)), 3, '0'));
    RETURN LOWER(CONCAT(
            @h1, @h2, '-', @h3, '-', @h4, '-', @h5, '-', @h6, @h7, @h8
        ));
END
//
DELIMITER ;


-- data setting

-- 장소
insert into triple_place_info (id, place_name)
values ('4706addc-903e-4e58-9286-c7a33576b462', '랜딩관 제주신화월드 호텔앤리조트');
insert into triple_place_info (id, place_name)
values ('8fba508e-e395-4ed1-a0e5-8045d699b030', '메리어트관 제주신화월드 호텔앤리조트');
insert into triple_place_info (id, place_name)
values ('ae4eee3c-9c46-4726-a424-42a87877925e', '제주 에코그린리조트');
insert into triple_place_info (id, place_name)
values ('bc2ce5ed-4922-4ddf-b2a0-bbe1d002dc67', '호텔 리젠트 마린');


-- 리뷰
insert into travelers_review
    (id, user_id, place_id, review_content)
values ('944dba74-e760-4152-8f15-5584f572cab4',
        '99a12bf6-0eeb-4e46-b9a2-c6b4b4c633c3',
        '0afbe506-5835-4f3a-aa42-09f32922afd1',
        '첫번째로 너무 깨끗하고, 보다 일찍 입실하게 해주셨어요!\n조식 뷔페가 진짜 짱이에요! 돈이 하나도 안아까웠습니다.\n하우스키핑에서 청결관리를 너무 잘해줘요.');
insert into travelers_review
    (id, user_id, place_id, review_content)
values ('71906941-b998-456c-9667-6436c844bcb7',
        '8d218e30-1f92-4242-a3f6-f34761b20ea5',
        '0afbe506-5835-4f3a-aa42-09f32922afd1',
        '1빠!');

-- 유저
insert into triple_user
    (id, nickname)
values ('8d218e30-1f92-4242-a3f6-f34761b20ea5', '트리플과함께');

insert into triple_user
    (id, nickname)
values ('99a12bf6-0eeb-4e46-b9a2-c6b4b4c633c3', '트리플49');


-- 유저 마일리정보
INSERT INTO triple_db.travelers_mileage_info (id, user_id, mileage)
VALUES ('24cd946a-77ae-4fb0-955d-992896bf2644', '8d218e30-1f92-4242-a3f6-f34761b20ea5', 0);
INSERT INTO triple_db.travelers_mileage_info (id, user_id, mileage)
VALUES ('4f558b2a-c9d5-4876-a55d-4faa30d60531', '99a12bf6-0eeb-4e46-b9a2-c6b4b4c633c3', 0);


-- 리뷰 이미지

insert into review_images
    (id, travelers_review_id, common_file_id, image_link)
values ('e4d1a64e-a531-46de-88d0-ff0ed70c0bb8',
        '944dba74-e760-4152-8f15-5584f572cab4',
        'e4d1a64e-46de-a531-88d0-ff0ed70c0bb8',
        'https://s3.ap-northeast-2.amazonaws.com/review_images/MDS4M18SM5K1');


