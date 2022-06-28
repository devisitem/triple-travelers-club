package io.taech.triple.business.events.service;

import io.taech.triple.business.events.constant.MileageUsage;
import io.taech.triple.business.events.dto.request.EventDto;
import io.taech.triple.business.events.dto.response.ReviewResponse;
import io.taech.triple.business.events.entity.MileageInfo;
import io.taech.triple.business.events.entity.ReviewImages;
import io.taech.triple.business.events.entity.TravelersReview;
import io.taech.triple.business.events.repository.support.MileageInfoSupport;
import io.taech.triple.business.events.repository.support.ReviewImagesSupport;
import io.taech.triple.business.events.repository.support.TripleReviewSupport;
import io.taech.triple.common.dto.StandardEventDto;
import io.taech.triple.common.dto.StandardResponse;
import io.taech.triple.common.excpeted.EventProcessingException;
import io.taech.triple.common.excpeted.ResponseStatus;
import io.taech.triple.common.excpeted.ValidateException;
import io.taech.triple.common.service.MileageService;
import io.taech.triple.common.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static io.taech.triple.business.events.dto.response.ResultInfo.success;
import static io.taech.triple.business.events.service.ReviewEventService.BEAN_NAME;

@Slf4j
@Service(BEAN_NAME)
@RequiredArgsConstructor
public class ReviewEventService implements EventService {

    public static final String BEAN_NAME = "reviewEventService";

    private final TripleReviewSupport reviewRepository;
    private final MileageInfoSupport mileageRepository;
    private final ReviewImagesSupport imageRepository;


    private final MileageService mileageService;

    @Override
    @Transactional
    public StandardResponse proceedAddEvent(final StandardEventDto standardEvent) throws Throwable {
        final EventDto event = (EventDto) standardEvent;
        final UUID userId = event.getUserId();
        final UUID placeId = event.getPlaceId();
        final UUID reviewId = event.getReviewId();

        final TravelersReview firstReview = reviewRepository.findFirstReview(placeId);
        if(Utils.isNull(firstReview)) {
            log.error("first review cannot be null due published event.");
            throw new EventProcessingException(ResponseStatus.NOT_FOUND_REVIEW_DATA);
        }

        //== 얼리버드 보너스 ==//
        if(firstReview.getId().equals(reviewId)) {
            log.info("It's first review for place so that added early bird bonus.");
            mileageService.manageMileage(userId, MileageUsage.ADD_FIRST_BONUS);
        }

        //== 사진첨부 ==//
        if( ! event.getAttachedPhotoIds().isEmpty()) {
            log.info("review has photos at least one so that added correspond mileages for attach photos.");
            mileageService.manageMileage(userId, MileageUsage.ADD_PHOTOS_REVIEW);
        }

        //== 기본 적립 ==//
        mileageService.manageMileage(userId, MileageUsage.ADD_WRITE_REVIEW);

        final Integer totalMileages = getUserMileageOrThrow(userId);

        return ReviewResponse.builder()
                .result(success())
                .userId(userId.toString())
                .totalMileages(totalMileages)
                .build();
    }

    @Override
    @Transactional
    public StandardResponse proceedModifyEvent(final StandardEventDto standardEvent) throws Throwable {
        final EventDto event = (EventDto) standardEvent;
        final UUID userId = event.getUserId();
        final UUID reviewId = event.getReviewId();

        final List<String> attachedPhotos = event.getAttachedPhotoIds();
        final TravelersReview review = getReviewOrThrow(reviewId, userId);

        //== 기존 삭제된 이미지가 존재하고 수정이벤트의 이미지가 없는경우 (이미지 삭제) ==//
        if(review.hasDeletedImages() && attachedPhotos.isEmpty()) {
            log.info("There are no photos at least one on both modified result and existing images with removed." +
                    " recovering mileage which added at exsiting.");
            mileageService.manageMileage(userId, MileageUsage.REMOVE_PHOTO);
        }
        //== 기존 삭제된 이미지가 존재하지 않고 수정이벤트의 이미지가 존재하는경우 (이미지 추가) ==//
        else if(( ! review.hasDeletedImages()) && ( ! attachedPhotos.isEmpty())) {
            log.info("There are {} photos attached on modified result and no existing images with deleted in this review." +
                    " so that added correspond mileages for attach photos.", attachedPhotos.size());
            mileageService.manageMileage(userId, MileageUsage.ADD_PHOTOS_REVIEW);
        }

        final Integer totalMileages = getUserMileageOrThrow(userId);

        return ReviewResponse.builder()
                .result(success())
                .userId(userId.toString())
                .totalMileages(totalMileages)
                .build();
    }

    @Override
    @Transactional
    public StandardResponse proceedDeleteEvent(final StandardEventDto standardEvent) throws Throwable {
        final EventDto event = (EventDto) standardEvent;
        final UUID userId = event.getUserId();
        final UUID reviewId = event.getReviewId();

        final TravelersReview review = getReviewOrThrow(reviewId, userId);
        final List<String> attachedPhotos = event.getAttachedPhotoIds();

        //== 현재리뷰로 획득한 마일리지중에 얼리버드 보너스가 존재 ==//
        if(review.getEarlyBirdBonusHistory().isPresent())
            mileageService.manageMileage(userId, MileageUsage.REMOVE_FIRST_BONUS);

        //== 사진 미존재 ==//
        if( ! attachedPhotos.isEmpty())
            mileageService.manageMileage(userId, MileageUsage.REMOVE_PHOTO);

        //== 기본 회수 ==//
        mileageService.manageMileage(userId, MileageUsage.REMOVE_REVIEW);

        final Integer totalMileages = getUserMileageOrThrow(userId);

        return ReviewResponse.builder()
                .result(success())
                .userId(userId.toString())
                .totalMileages(totalMileages)
                .build();
    }

    private TravelersReview getReviewOrThrow(final UUID reviewId, final UUID userId) throws Throwable {

        final TravelersReview review = reviewRepository.findReview(reviewId, userId);
        if (Utils.isNull(review)) {
            log.info("not found review with id: \"{}\"", reviewId);
            throw new ValidateException(String.format("invalid review id. there is no review which has id \"{}\"", reviewId));
        }

        log.info("found 1 review with id \"{}\"", review.getId());
        return review;
    }

    private Integer getUserMileageOrThrow(final UUID userId) throws Throwable {
        final MileageInfo info = mileageRepository.findByUserId(userId);
        if (Utils.isNull(info)) {
            log.info("mileage info cannot be null, it has may system error.");
            throw new EventProcessingException(ResponseStatus.NOT_FOUND_MILEAGE_INFO);
        }

        return info.getMileages();
    }
}
