package io.taech.triple.business.events.entity;

import io.taech.triple.business.events.constant.MileageUsage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@DynamicUpdate
@Table(name = "travelers_mileage_history")
public class MileageHistory {

    @Id
    @Column(nullable = false, updatable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable =false, updatable =false, columnDefinition = "VARCHAR(36)")
    private TripleUser user;

    @Column(nullable = false, length = 1)
    private Integer type;

    @Column(nullable = false, length = 11)
    private Integer mileage;

    @Column(nullable = false, length = 200)
    private String descriptions;

    private LocalDateTime createTime;

    private LocalDateTime deleteTime;

    @OneToOne(mappedBy = "history", fetch = FetchType.LAZY)
    private ReviewRewardInfo rewardInfo;

    public static MileageHistory create(final MileageUsage usage, final TripleUser user) {
        final MileageHistory history = new MileageHistory();

        history.id = UUID.randomUUID();
        history.type = usage.type();
        history.mileage = usage.mileage();
        history.descriptions = usage.descriptions();
        history.createTime = LocalDateTime.now();
        history.user = user;

        return history;
    }

    public static Integer sumMileages(final List<MileageHistory> histories) {
        return histories.stream().mapToInt(his ->
                his.mileage).sum();
    }

    public void connectRewardInfo(final ReviewRewardInfo rewardInfo) {
        this.rewardInfo = rewardInfo;
        rewardInfo.connectHistory(this);
    }

    public void connectUser(final TripleUser tripleUser) {
        this.user = tripleUser;
        tripleUser.addHistory(this);
    }
}
