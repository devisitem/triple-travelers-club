package io.taech.triple.business.events.entity;

import io.taech.triple.business.events.constant.MileageUsage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(schema = "travelers_mileage_history")
public class MileageHistory {

    @Id
    @Column(nullable = false, length = 36)
    private UUID id;

    @Column(nullable = false, length = 36)
    private UUID userId;

    @Column(nullable = false, length = 1)
    private Integer type;

    @Column(nullable = false, length = 11)
    private Integer mileage;

    @Column(nullable = false, length = 200)
    private String descriptions;

    @Column(nullable = false, length = 1)
    private String deleteYn;

    private LocalDateTime createTime;

    public static MileageHistory create(final UUID userId, final MileageUsage usage) {
        final MileageHistory history = new MileageHistory();

        history.id = UUID.randomUUID();
        history.userId = userId;
        history.type = usage.type();
        history.mileage = usage.mileage();
        history.descriptions = usage.descriptions();
        history.deleteYn = "N";
        history.createTime = LocalDateTime.now();

        return history;
    }

    public static Integer sumMileages(final List<MileageHistory> histories) {
        return histories.stream().mapToInt(his ->
                his.mileage).sum();
    }

}
