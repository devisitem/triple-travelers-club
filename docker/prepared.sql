CREATE DATABASE IF NOT EXISTS triple_db CHARACTER SET utf8mb3 COLLATE utf8_general_ci;
USE triple_db;


create table travelers_review
(
    id             binary(16) not null
        primary key,
    user_id        binary(16) not null comment '작성자 아이디',
    place_id       binary(16) not null comment '리뷰 장소 아이디',
    review_content varchar(255) not null comment '리뷰 텍스트 내용',
    delete_yn      varchar(255) not null comment '삭제 여부',
    create_time    datetime(6) not null comment '생성 일시'
) comment '리뷰 관리 테이블';

create index travelers_review_place_id_index
    on travelers_review (place_id);

create index travelers_review_user_id_index
    on travelers_review (user_id);



create table common_file
(
    id               binary(16) not null comment '파일 아이디'
        primary key,
    file_size        int                                not null comment '파일 사이즈',
    file_path        varchar(255)                       not null comment '파일 경로 ',
    stored_file_name varchar(50) null comment '저장된 파일명',
    real_file_name   varchar(50)                        not null comment '실제 파일명',
    file_extension   varchar(5)                         not null comment '파일 확장자',
    create_time      datetime default CURRENT_TIMESTAMP not null comment '생성 일시'
) comment '공통 파일 관리 테이블';



create table review_images
(
    id                  binary(16) not null comment '이미지 아이디'
        primary key,
    travelers_review_id binary(16) not null comment '소속된 리뷰 아이디',
    common_file_id      binary(16) not null comment '파일 아이디',
    image_link          varchar(255)                       not null comment '이미지 링크 문자열',
    create_time         datetime default CURRENT_TIMESTAMP not null comment '생성 일시',
    constraint review_images_common_file_id_un
        unique (common_file_id)
) comment '리뷰 이미지 관리 테이블';

create index review_images_travelers_review_id_index
    on review_images (travelers_review_id);

create table triple_place_info
(
    id          binary(16) not null comment '여행 장소 아이디'
        primary key,
    place_name  varchar(200)                       not null comment '트리플 여행지 장소명',
    create_time datetime default CURRENT_TIMESTAMP not null comment '생성 일시'
);

create index triple_place_info_place_name_index
    on triple_place_info (place_name);



create table travelers_mileage_history
(
    id          binary(16) not null comment '마일리지 이력 아이디'
        primary key,
    user_id     binary(1) not null comment '유저 아이디',
    type        int(1) not null comment '마일리지 이력 타입. 1: 적립, 2: 사용',
    description varchar(200)                       not null comment '적립 또는 사용내용',
    create_time datetime default CURRENT_TIMESTAMP not null comment '생성 일시'
) comment '트리플 여행자 마일리지 관리 이력';

create index travelers_mileage_history_user_for_search
    on travelers_mileage_history (user_id, type);