package io.taech.triple.business.events.service;

import io.taech.triple.business.events.constant.MileageUsage;
import io.taech.triple.business.events.dto.request.EventDto;
import io.taech.triple.business.events.dto.response.ReviewResponse;
import io.taech.triple.business.events.entity.MileageInfo;
import io.taech.triple.business.events.entity.TravelersReview;
import io.taech.triple.business.events.entity.TripleUser;
import io.taech.triple.business.events.repository.UserRepository;
import io.taech.triple.business.events.repository.support.ReviewRewardInfoSupport;
import io.taech.triple.business.events.repository.support.TripleReviewSupport;
import io.taech.triple.common.dto.StandardEventDto;
import io.taech.triple.common.dto.response.StandardResponse;
import io.taech.triple.common.excpeted.EventProcessingException;
import io.taech.triple.common.excpeted.ServiceStatus;
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
    private final UserRepository userRepository;

    private final MileageService mileageService;
    private final ReviewRewardInfoSupport rewardInfoSupport;

    @Override
    @Transactional
    public StandardResponse proceedAddEvent(final StandardEventDto standardEvent) throws Throwable {
        final EventDto event = (EventDto) standardEvent;
        final UUID placeId = event.getPlaceId();

        final TripleUser user = getReviewer(event.getUserId());
        final TravelersReview review = user.getReview(event.getReviewId()).orElseThrow(() -> {
            log.error("Not found review with id: \"{}\"", event.getReviewId());
            return new ValidateException(ServiceStatus.NOT_FOUND_REVIEW_DATA);
        });

        review.ifDeleted(() -> {
            log.error("Already deleted review. Can't proceed event.");
            return new EventProcessingException(ServiceStatus.ALREADY_DELETED_REVIEW);
        });

        //== 동일장소 리뷰 보상불가 ==//
        if(Utils.isNotNull(rewardInfoSupport.findAlreadyRewardedPlace(placeId, event.getUserId()))) {
            log.error("you already got reward for this place: \"{}\"", event.getPlaceId());
            throw new EventProcessingException(ServiceStatus.ONLY_ONE_REWARD_AT_PLACE);
        }

        final TravelersReview firstReview = reviewRepository.findFirstReview(placeId);
        if(Utils.isNull(firstReview)) {
            log.error("first review cannot be null due published event.");
            throw new EventProcessingException(ServiceStatus.NOT_FOUND_REVIEW_DATA);
        }

        //== 얼리버드 보너스 ==//
        if(review.isEqualsId(firstReview.getId())) {
            log.info("It's first review for place so that added early bird bonus.");
            mileageService.manageMileagesWithReview(review, user, MileageUsage.ADD_FIRST_BONUS);
        }

        //== 사진첨부 ==//
        if( ! event.getAttachedPhotoIds().isEmpty()) {
            log.info("review has photos at least one so that added correspond mileages for attach photos.");
            mileageService.manageMileagesWithReview(review, user, MileageUsage.ADD_PHOTOS_REVIEW);
        }

        //== 기본 적립 ==//
        mileageService.manageMileagesWithReview(review, user, MileageUsage.ADD_WRITE_REVIEW);

        //== 마일리지 정산 ==//
        final MileageInfo mileageInfo = user.getMileageInfo();
        mileageInfo.settledMileages();

        return ReviewResponse.builder()
                .result(success())
                .userId(user.getId())
                .totalMileages(mileageInfo.getMileage())
                .build();
    }

    @Override
    @Transactional
    public StandardResponse proceedModifyEvent(final StandardEventDto standardEvent) throws Throwable {
        final EventDto event = (EventDto) standardEvent;
        final List<String> attachedPhotos = event.getAttachedPhotoIds();

        final TripleUser user = getReviewer(event.getUserId());
        final TravelersReview review = user.getReview(event.getReviewId()).orElseThrow(() -> {
            log.error("Not found review with id: \"{}\"", event.getReviewId());
            return new ValidateException(ServiceStatus.NOT_FOUND_REVIEW_DATA);
        });

        review.ifDeleted(() -> {
            log.error("Already deleted review. Can't proceed event.");
            return new EventProcessingException(ServiceStatus.ALREADY_DELETED_REVIEW);
        });

        //== 기존 삭제된 이미지가 존재하고 수정이벤트의 이미지가 없는경우 (이미지 삭제) ==//
        if(review.hasDeletedImages() && attachedPhotos.isEmpty()) {
            log.info("There are no photos at least one on both modified result and existing images with removed." +
                    " recovering mileage which added at exsiting.");
            mileageService.manageMileagesWithReview(review, user, MileageUsage.REMOVE_PHOTO);
        }
        //== 기존 삭제된 이미지가 존재하지 않고 수정이벤트의 이미지가 존재하는경우 (이미지 추가) ==//
        else if(( ! review.hasDeletedImages()) && ( ! attachedPhotos.isEmpty())) {
            log.info("There are {} photos attached on modified result and no existing images with deleted in this review." +
                    " so that added correspond mileages for attach photos.", attachedPhotos.size());
            mileageService.manageMileagesWithReview(review, user, MileageUsage.ADD_PHOTOS_REVIEW);
        }

        //== 마일리지 정산 ==//
        final MileageInfo mileageInfo = user.getMileageInfo();
        mileageInfo.settledMileages();

        return ReviewResponse.builder()
                .result(success())
                .userId(user.getId())
                .totalMileages(mileageInfo.getMileage())
                .build();
    }

    @Override
    @Transactional
    public StandardResponse proceedDeleteEvent(final StandardEventDto standardEvent) throws Throwable {
        final EventDto event = (EventDto) standardEvent;
        final List<String> attachedPhotos = event.getAttachedPhotoIds();

        final TripleUser user = getReviewer(event.getUserId());
        final TravelersReview review = user.getReview(event.getReviewId()).orElseThrow(() -> {
            log.error("Not found review with id: \"{}\"", event.getReviewId());
            return new ValidateException(ServiceStatus.NOT_FOUND_REVIEW_DATA);
        });

        //== 현재리뷰로 획득한 마일리지중에 얼리버드 보너스가 존재 ==//
        if(review.getEarlyBirdBonusHistory().isPresent())
            mileageService.manageMileagesWithReview(review, user, MileageUsage.REMOVE_FIRST_BONUS);

        //== 사진 미존재 ==//
        if( ! attachedPhotos.isEmpty())
            mileageService.manageMileagesWithReview(review, user, MileageUsage.REMOVE_PHOTO);

        //== 기본 회수 ==//
        mileageService.manageMileagesWithReview(review, user, MileageUsage.REMOVE_REVIEW);

        //== 마일리지 정산 ==//
        final MileageInfo mileageInfo = user.getMileageInfo();
        mileageInfo.settledMileages();

        return ReviewResponse.builder()
                .result(success())
                .userId(user.getId())
                .totalMileages(mileageInfo.getMileage())
                .build();
    }

    private TripleUser getReviewer(final UUID userId) {

        return userRepository.findById(userId).orElseThrow(() -> {
            log.error("Not found user with id: \"{}\"", userId);
            return new ValidateException(ServiceStatus.NOT_FOUND_USER_DATA);
        });
    }

}
